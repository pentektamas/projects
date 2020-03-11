#version 410 core

in vec3 normal;
in vec4 fragPosEye;
in vec4 fragPosLightSpace;
in vec2 fragTexCoords;

out vec4 fColor;

//lighting
uniform	mat3 normalMatrix;
uniform mat3 lightDirMatrix;
uniform	vec3 lightColor;
uniform	vec3 lightDir;
uniform sampler2D diffuseTexture;
uniform sampler2D specularTexture;
uniform sampler2D shadowMap;

uniform int isFog;
uniform vec3 lightPosEye;

vec3 ambient;
float ambientStrength = 0.2f;
vec3 diffuse;
vec3 specular;
float specularStrength = 0.5f;
float shininess = 64.0f;

vec3 o;



void computeLightComponents()
{		
	vec3 cameraPosEye = vec3(0.0f);//in eye coordinates, the viewer is situated at the origin
	
	//transform normal
	vec3 normalEye = normalize(normalMatrix * normal);	
	
	//compute light direction
	vec3 lightDirN = normalize(lightDirMatrix * lightDir);	

	//compute view direction 
	vec3 viewDirN = normalize(cameraPosEye - fragPosEye.xyz);
	
	//compute half vector
	vec3 halfVector = normalize(lightDirN + viewDirN);
		
	//compute ambient light
	ambient = ambientStrength * lightColor;
	
	//compute diffuse light
	diffuse = max(dot(normalEye, lightDirN), 0.0f) * lightColor;
	
	//compute specular light
	float specCoeff = pow(max(dot(halfVector, normalEye), 0.0f), shininess);
	specular = specularStrength * specCoeff * lightColor;
}


void computePointLight()
{		
	vec3 cameraPosEye = vec3(0.0f);//in eye coordinates, the viewer is situated at the origin
	
	//transform normal
	vec3 normalEye = normalize(normalMatrix * normal);	
	
	//compute light direction
	vec3 lightDirN = normalize(cameraPosEye - fragPosEye.xyz);	

//	float constant = 1.0f;
//	float linear = 0.045f;
//	float quadratic = 0.0075f; 


	float constant = 1.0f;
	float linear = 0.14f;
	float quadratic = 0.07f; 

	//compute distance to light
	float dist = length(lightPosEye - fragPosEye.xyz);
	//compute attenuation
	float att = 1.0f / (constant + linear * dist + quadratic * (dist * dist));
	
	//compute ambient light
	//ambient = ambient + att * ambientStrength * lightColor;
	//compute diffuse light
	//diffuse = diffuse + att * max(dot(normalEye, lightDirN), 0.0f) * lightColor;
	//specular =specular +  att * specularStrength * specCoeff * lightColor;


	//compute view direction 
	vec3 viewDirN = normalize(cameraPosEye - fragPosEye.xyz);
	
	//compute half vector
	vec3 halfVector = normalize(lightDirN + viewDirN);
		
	//compute ambient light
	ambient = ambient + att * ambientStrength * lightColor;
	
	//compute diffuse light
	diffuse = diffuse + att * max(dot(normalEye, lightDirN), 0.0f) * lightColor;
	
	//compute specular light
	float specCoeff = pow(max(dot(halfVector, normalEye), 0.0f), shininess);
	specular =specular +  att * specularStrength * specCoeff * lightColor;
}



float computeShadow()
{	
	// perform perspective divide
    vec3 normalizedCoords = fragPosLightSpace.xyz / fragPosLightSpace.w;
    if(normalizedCoords.z > 1.0f)
        return 0.0f;
    // Transform to [0,1] range
    normalizedCoords = normalizedCoords * 0.5f + 0.5f;
    // Get closest depth value from light's perspective (using [0,1] range fragPosLight as coords)
    float closestDepth = texture(shadowMap, normalizedCoords.xy).r;    
    // Get depth of current fragment from light's perspective
    float currentDepth = normalizedCoords.z;
    // Check whether current frag pos is in shadow
    //float bias = 0.005f;
    float bias = max(0.05f * (1.0f - dot(normal, lightPosEye)), 0.005f);
    float shadow = currentDepth - bias> closestDepth  ? 1.0f : 0.0f;

    return shadow;	
}

float computeFog() 
{ 
	float fogDensity = 0.007f;
	float fragmentDistance = length(fragPosEye);
	float fogFactor = exp(-pow(fragmentDistance * fogDensity, 2));

	return clamp(fogFactor, 0.0f, 1.0f); }

void main() 
{
	computeLightComponents();
	
	computePointLight();

	float shadow = computeShadow();
	
	//modulate with diffuse map
	ambient *= vec3(texture(diffuseTexture, fragTexCoords));
	diffuse *= vec3(texture(diffuseTexture, fragTexCoords));
	//modulate with specular map
	specular *= vec3(texture(specularTexture, fragTexCoords));
	
	//modulate with shadow
	//vec3 color = min((ambient + (1.0f - shadow)*diffuse) + (1.0f - shadow)*specular, 1.0f);

	//float fogFactor = computeFog(); 
	//vec4 fogColor = vec4(0.5f, 0.5f, 0.5f, 1.0f);
	//fColor = fogColor * (1 - fogFactor) + color * fogFactor;

	/*vec4 colorFromTexture = texture(diffuseTexture, fragTexCoords); 
	if(colorFromTexture.a < 0.1) 
		discard; 
	fColor = colorFromTexture;*/

	vec3 norm = normalize(normal);
	//vec3 result = CalcPointLight(pointLight, norm);
	//vec3 color = result;
    	
	vec3 color = min((ambient + (1.0f - shadow)*diffuse) + (1.0f - shadow)*specular, 1.0f);


	float fogFactor = computeFog(); 
	vec4 fogColor = vec4(0.0f, 0.7f, 0.1f, 1.0f);
	if(isFog == 1) 
	{
		fColor = fogColor * (1 - fogFactor) + vec4(color, 1.0f) * fogFactor;
	}
	else
	{
		fColor = vec4(color, 1.0f);
		//fColor = colorFromTexture;
	}

    	//fColor = vec4(color, 1.0f);
    	//fColor = vec4(o, 1.0f);
}

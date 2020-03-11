import processing.serial.*; 

Serial myPort;     
int y=10,dim;
float realDist;
float rectangleWidth;
float lastAngle=20;
boolean kept=false;
void setup() {     
  size(1000, 1000);
  lastAngle=map(lastAngle,30,170,0,width);
  int lastport = Serial.list().length;     
  String portName = Serial.list()[lastport-1]; 
  myPort = new Serial(this, portName, 9600);       
  myPort.bufferUntil('\n'); 
  background(0); 
} 
 
void draw() {     
  String inString = myPort.readStringUntil('\n');     
  if (inString != null) {         
    String[] list=split(inString, ' ');
    float angle = float(list[0]); 
    float dist = float(list[1]);
    realDist=dist;
    
    if(int(angle)==30){
      y=y+10; 
    }
    dist = map(dist, 3, 400, 0, height);         
    angle = map(angle, 30, 170 , 0,width);
    
    dim=(int)(abs(lastAngle-angle));
    if(kept==false){
      kept=true;
      rectangleWidth=dim;
    }
    if(realDist > 50)
      fill(0, 255, 0); 
    else
      fill(255, 0, 0);
    rect((int)angle,y,rectangleWidth,10);
  } 
}

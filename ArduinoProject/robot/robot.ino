#include <Servo.h>
#include <SoftwareSerial.h>
// Pinii motor 1
#define mpin00 5
#define mpin01 6
// Pinii motor 2
#define mpin10 3
#define mpin11 11
Servo srv;
int angle=-1;
const int trigPin = 9;
const int echoPin = 10;
const int piezoPin = 7;
long duration;
int distance;
SoftwareSerial mySerial(12,2);  //RX, TX

void setup() {
// configurarea pinilor motor ca iesire, initial valoare 0
digitalWrite(mpin00, 0);
digitalWrite(mpin01, 0);
digitalWrite(mpin10, 0);
digitalWrite(mpin11, 0);
pinMode (mpin00, OUTPUT);
pinMode (mpin01, OUTPUT);
pinMode (mpin10, OUTPUT);
pinMode (mpin11, OUTPUT);

// pin LED
pinMode(13, OUTPUT);
pinMode(trigPin, OUTPUT); // Sets the trigPin as an Output
pinMode(echoPin, INPUT); // Sets the echoPin as an Input
pinMode(piezoPin, OUTPUT); // Pin folosit de Buzzer
Serial.begin(9600); // Starts the serial communication
mySerial.begin(9600);
}


// Funcție pentru controlul unui motor
// Intrare: pinii m1 și m2, direcția și viteza
void StartMotor (int m1, int m2, int forward, int speed)
{
  if (speed==0) // oprire
  {
    digitalWrite(m1, 0);
    digitalWrite(m2, 0);
  }
  else
  {
    if (forward)
    {
      digitalWrite(m2, 0);
      analogWrite(m1, speed); // folosire PWM
    }
  else
  {
    digitalWrite(m1, 0);
    analogWrite(m2, speed);
  }
}
}


// Funcție de siguranță
// Execută oprire motoare, urmată de delay
void delayStopped(int ms)
{
  StartMotor (mpin00, mpin01, 0, 0);
  StartMotor (mpin10, mpin11, 0, 0);
  delay(ms);
}

void testServo(int pin){
  srv.attach(pin);
  for(int i=30; i<=170; i=i+10){
    srv.write(i);
    angle=i;
   // Serial.print("Angle: ");
    Serial.print(angle);
    readUltraSonic();
    delay(100);
  }
  delay(400);
  srv.detach();
}

int readUltraSonic(){
  // Clears the trigPin
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);

  // Sets the trigPin on HIGH state for 10 micro seconds
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);

  // Reads the echoPin, returns the sound wave travel time in microseconds
  duration = pulseIn(echoPin, HIGH);
  // Calculating the distance
  distance= duration*0.034/2;
  //Serial.print("Distance: ");
   Serial.print(" ");
   Serial.println(distance);
  if (distance < 40) {
    tone(piezoPin,10000/distance,500);
  }
}

void startMotors(){
  if (mySerial.available()){
    char com=mySerial.read();
    Serial.write(com);
    if( com=='w' || com=='W'){
      StartMotor (mpin00, mpin01, 1, 128);
      StartMotor (mpin10, mpin11, 1, 128);
      delay (500); // Cât timp e motorul pornit
    }

    if( com=='s' || com=='S'){
      StartMotor (mpin00, mpin01, 0, 128);
      StartMotor (mpin10, mpin11, 0, 128);
      delay (500);
    }
    
    if (com=='d' || com=='D'){
      StartMotor (mpin00, mpin01, 1, 128);
      StartMotor (mpin10, mpin11, 0, 128);
      delay (100);
    }

    if( com=='a' || com=='A'){
      StartMotor (mpin00, mpin01, 0, 128);
      StartMotor (mpin10, mpin11, 1, 128);
      delay (100);
    }
      delayStopped(100);
}
}

void loop() {
  
// Pornirea motorului Servo
testServo(8);

//delay(250);
startMotors();

}

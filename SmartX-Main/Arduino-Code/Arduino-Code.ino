//Bluetooth Conniction
#include <SoftwareSerial.h>
SoftwareSerial BT(5, 6); //TX, RX

//smoke
const int gasPin = A0; //GAS sensor output pin to Arduino analog A0 pin
int redLed = 14;
char dataString[50] = {0};
int a =20; 
int b =30;

//motion

int motion_1 = 46;
int light_1 = 14;


//temp
#include <OneWire.h>
#include <DallasTemperature.h>
#define ONE_WIRE_BUS 21
OneWire oneWire(ONE_WIRE_BUS);
DallasTemperature sensors(&oneWire);
const int fanPin =  52;             
int tempMax = 26;  
float tempc = 0;

//lights
int mode = 0; 
//man=1 auto=0
int ByteReceived;
int switcher = char(ByteReceived);
const int Ro = 2;
const int Rt = 3;
const int Rtt = 4;
const int Rmr = 5;
int Ros = 0;
int Rts = 0;
int Rtts = 0; 
int Rmrs = 0;


void setup() {
//smoke  
pinMode(redLed, OUTPUT);
digitalWrite(redLed, LOW);
Serial.println("n");
  
//lights  
//Serial.begin(115200);
BT.begin(9600);
Serial.begin(9600);
pinMode(11, OUTPUT);
pinMode(12, OUTPUT);
pinMode(13, OUTPUT);
pinMode(8, OUTPUT);
pinMode(10, OUTPUT);
pinMode(9, OUTPUT);
pinMode(Ro, INPUT);
pinMode(Rt, INPUT);
pinMode(Rtt, INPUT);
pinMode(Rmr, INPUT);
pinMode(fanPin, OUTPUT);   
sensors.begin();

//motion
  pinMode (motion_1,INPUT);
  pinMode (light_1, OUTPUT);
  
}
  
void loop() { 
  //lights
   ByteReceived = Serial.read();
  if (ByteReceived == '9' or BT.read() == '9') {
      mode=1;
} else if (ByteReceived == '0' or BT.read() == '0') {
      mode=0;
}
  
  if (mode == 1) {
       //waiting room 
    if (char(ByteReceived) == '1' or char(BT.read()) == '1') {
      digitalWrite(11, HIGH);
     // digitalWrite(10, HIGH);
    }
    else if(char(ByteReceived) == '2' or char(BT.read()) == '2') {
      digitalWrite(11, LOW);
    //  digitalWrite(10, LOW);
    }
    //kitchen
    else if(char(ByteReceived) == '3' or char(BT.read()) == '3') {
      digitalWrite(12, HIGH);
    }
    else if(char(ByteReceived) == '4' or char(BT.read()) == '4') {
      digitalWrite(12, LOW);
    }
    //office
    else if(char(ByteReceived) == '5' or char(BT.read()) == '5') {
      digitalWrite(13, HIGH);
    }
    else if(char(ByteReceived) == '6' or char(BT.read()) == '6') {
      digitalWrite(13, LOW);
    }
    //bath room
    else if(char(ByteReceived) == '7' or char(BT.read()) == '7') {
      digitalWrite(8, HIGH);
    }
    else if(char(ByteReceived) == '8' or char(BT.read()) == '8') {
      digitalWrite(8, LOW);
    }
    //employee room
    else if(char(ByteReceived) == 'e' or char(BT.read()) == 'e') {
      digitalWrite(9, HIGH);
    }
    else if(char(ByteReceived) == 'r' or char(BT.read()) == 'r') {
      digitalWrite(9, LOW);
    }
    //meeting room
    else if(char(ByteReceived) == 'm' or char(BT.read()) == 'm') {
      digitalWrite(10, HIGH);
    }
    else if(char(ByteReceived) == 't' or char(BT.read()) == 't') {
      digitalWrite(10, LOW);
    }
    
  }
  
  else if (mode == 0)  {
    Ros = digitalRead(Ro);
    Rts = digitalRead(Rt);
    Rtts = digitalRead(Rtt);
    Rmrs = digitalRead(Rmr);
    if (Ros == LOW ) {
      digitalWrite(12, HIGH);
    }
    else {
      digitalWrite(12, LOW);
    }
    
    if (Rts == LOW ) {
      digitalWrite(11, HIGH);
     // digitalWrite(10, HIGH);
    }
    else {
      digitalWrite(11, LOW);
     // digitalWrite(10, LOW);
    }
    
    if (Rtts == LOW ) {
      digitalWrite(13, HIGH);
    }
    else {
      digitalWrite(13, LOW);
    }
    if (Rmrs == LOW ) {
      digitalWrite(10, HIGH);
    }
    else {
      digitalWrite(10, LOW);
    }
  }
    // Send the command to get temperatures
  sensors.requestTemperatures(); 
  tempc = sensors.getTempCByIndex(0);
  if (tempc > tempMax) {     
    // turn LED on:    
    digitalWrite(fanPin, HIGH);  
  } 
  else {
    // turn LED off:
    digitalWrite(fanPin, LOW); 
  }
  //smoke
     
  if (analogRead(gasPin) > 150)
  {
    
      digitalWrite(redLed, HIGH);
      sprintf(dataString,"%02X",a);
      Serial.println(dataString);
      delay(20000);
      
  }
  
  else
  {
   
    digitalWrite(redLed, LOW);
    sprintf(dataString,"%02X",b);
    Serial.println(dataString);
  }
 delay(1000); 
 
 //motion
   digitalWrite (light_1,LOW);
  delay(1000); //this delay is to let the sensor settle down before taking a reading
  int sensor_1 = digitalRead(motion_1);
  if (sensor_1 == HIGH){
    digitalWrite(light_1,HIGH);
    delay(500);
    digitalWrite(light_1,LOW);
    delay(100);

  }

}

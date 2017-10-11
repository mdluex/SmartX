import serial # For Bluetooth
from nanpy import (ArduinoApi, SerialManager) # For Arduino
import RPi.GPIO as GPIO # For Raspberry Pi
from threading import Thread
from time import sleep, time
import os
from Adafruit_CharLCD import Adafruit_CharLCD
import socket

# Bluetooth Serial Conniction
ser = serial.Serial('/dev/ttyS0', 9600, timeout = 0)

# Arduino Nanpy Conniction
connection = SerialManager()
a = ArduinoApi(connection = connection)

# Python Variables
sysMode = 0
serialRead = 0
check_mode = 1
IP = 0

# GPIO Variables
SMOKE_IN = 19
TRIG = 2 
ECHO = 3

# GPIO Pins
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup(SMOKE_IN, GPIO.IN)
GPIO.setup(TRIG,GPIO.OUT)
GPIO.setup(ECHO,GPIO.IN)

# LCD GPIO Pins
lcd_af = Adafruit_CharLCD(rs=26, en=12, d4=13, d5=6, d6=5, d7=11, cols=16, lines=2) # GPIO.BCM

# Arduino Variables
office = 13
r_office = 4
kitchen = 12
r_kitchen = 2
meeting_room = 3
r_meeting_room = 5
waiting_room = 7
r_waiting_room = 14
employee_room = 9
bath_room = 8
buzzer = 22
fan = 52

# Arduino Pins
a.pinMode(office, a.OUTPUT)
a.pinMode(r_office, a.INPUT)
a.pinMode(kitchen, a.OUTPUT)
a.pinMode(r_kitchen, a.INPUT)
a.pinMode(meeting_room, a.OUTPUT)
a.pinMode(r_meeting_room, a.INPUT)
a.pinMode(waiting_room, a.OUTPUT)
a.pinMode(r_waiting_room, a.INPUT)
a.pinMode(employee_room, a.OUTPUT)
a.pinMode(bath_room, a.OUTPUT)
a.pinMode(buzzer, a.OUTPUT)
a.pinMode(fan, a.OUTPUT)

# IP Address Function
def ip_address():
    return [
             (s.connect(('8.8.8.8', 53)),
              s.getsockname()[0],
              s.close()) for s in
                  [socket.socket(socket.AF_INET, socket.SOCK_DGRAM)]
           ][0][1]

# LCD Function
def LCD(line1, line2, delay):
	lcd_af.clear()
	lcd_af.message(line1 + '\n' + line2)
	sleep(delay)
	lcd_af.clear()
	IP = ip_address()
	lcd_af.message('SmartX' + '\n' + IP)

# Light Resistor Function
def r_room(ldr, room, name):
        if a.digitalRead(ldr) == True:
                a.digitalWrite(room, a.LOW)
                LCD(name, 'is OFF', 2)
        elif a.digitalRead(ldr) == False:
                a.digitalWrite(room, a.HIGH)
                LCD(name, 'is ON', 2)

# Manual Control
def m_room(on, off, room, name):
	if serialRead == on:
		a.digitalWrite(room, a.HIGH)
		LCD(name, 'is ON', 2)
	elif serialRead == off:
		a.digitalWrite(room, a.LOW)
		LCD(name, 'is OFF', 2)

# Mobile Check
def m_check(room, on, off):
	if a.digitalRead(room) == True:
		ser.write(on)
	elif a.digitalRead(room) == False:
		ser.write(off)

# LCD Welcome Screen
IP = ip_address()
LCD('SmartX', IP, 5)

# Lights Thread
def lights():
	while True:

		# Global Variables
		global sysMode
		global serialRead
		global office
		global r_office
		global kitchen
		global r_kitchen
		global meeting_room
		global r_meeting_room
		global waiting_room
		global r_waiting_room
		global employee_room
		global bath_room
		global check_mode

		# Bluetooth Serial Variable
		serialRead = ser.readline()
		serialRead = serialRead.decode().strip()

		# System Mode Check
		if serialRead == "9":
			sysMode = 1
			LCD('SmartX', 'Automatic', 1)
		elif serialRead == "0":
			sysMode = 0
			LCD('SmartX', 'Manual', 1)
		
		# Automatic Mode
		if sysMode == 0:
			r_room(r_office, office, 'Office')
			r_room(r_kitchen, kitchen, 'Kitchen')
			r_room(r_meeting_room, meeting_room, 'Meeting Room')
			r_room(r_waiting_room, waiting_room, 'Waiting Room')
			check_mode = 0
		
		# Manual Mode
		elif sysMode == 1:

			# Check The Light Status
			if check_mode < 50:
				m_check(waiting_room, b"D", b"d")
				m_check(kitchen, b"G", b"g")
				m_check(office, b"V", b"v")
				m_check(bath_room, b"H", b"h")
				m_check(employee_room, b"K", b"k")
				m_check(meeting_room, b"F\n", b"f\n")
				check_mode = check_mode + 1

			# Manual Mode Serial Check
			m_room("1", "2", waiting_room, 'Waiting Room')
			m_room("3", "4", kitchen, 'Kitchen')
			m_room("5", "6", office, 'Office')
			m_room("7", "8", bath_room, 'Bathroom')
			m_room("e", "r", employee_room, 'Employee Room')
			m_room("m", "t", meeting_room, 'Meeting Room')

# Smoke Thread
def smoke():
	while True:	

		# Global Variables
		global buzzer

		# Get Smoke Status
		if GPIO.input(SMOKE_IN) == False: 
			a.digitalWrite(buzzer, a.HIGH)
			LCD('Kitchen', 'Smoke Detected', 2)
			ser.write(b"S")
			sleep(2)
			a.digitalWrite(buzzer, a.LOW)
			LCD('Kitchen', 'No Smoke Now', 2)
			os.system('echo "SmartX detected smoke or gas in your apartment\nPlease be safe" | mail -s "Smoke or gas detected in your apartment" moksha.elghabaty@hotmail.com')
			ser.write(b"s")
			sleep(15)
			os.system('echo "there is no smoke now in your apartment" | mail -s "No smoke or gas now in your apartment" moksha.elghabaty@hotmail.com')   
		else:
			a.digitalWrite(buzzer, a.LOW)  
			sleep(2)

# Temprature Thread
def temp():
	while True:

		# Get The Temprature Vlaue
		tempfile = open("/sys/bus/w1/devices/28-000002f53b3e/w1_slave")
		thetext = tempfile.read()
		tempfile.close()
		tempdata = thetext.split("\n")[1].split(" ")[9]
		temprature = float(tempdata[2:])
		temprature = temprature / 1000
		temprature = int(temprature)
		#print temprature
		if temprature > 30:
			a.digitalWrite(fan, a.HIGH)
			LCD('Control Center', 'Fan ON', 2)
			ser.write(b"Z")
		else:
			a.digitalWrite(fan, a.LOW)
			LCD('Control Center', 'Fan OFF', 2)
			ser.write(b"z")

# Motion Thread
def motion():
	while True:

		# Global Variables
		global buzzer

		# Get Motion Value 
		GPIO.output(TRIG, True)
		sleep(0.00001)
		GPIO.output(TRIG, False)
		start = time()
		while GPIO.input(ECHO)==0:
			start = time()
		while GPIO.input(ECHO)==1:
			stop = time()
		elapsed = stop-start
		distance = (elapsed * 34300)/2
		sleep(0.5)
		int_distance = int(distance)
		if int_distance < 8:
			a.digitalWrite(buzzer, a.HIGH)
			LCD('Lane', 'Motion Detected', 2)
			sleep(1)
			ser.write(b"W")
		else:
			a.digitalWrite(buzzer, a.LOW)
			LCD('Lane', 'No Motion Now', 2)
			sleep(2)
			ser.write(b"w")

# Run The Threads
if __name__ == '__main__':
	Thread(target = lights).start()
	Thread(target = smoke).start()
	Thread(target = temp).start()
	Thread(target = motion).start()
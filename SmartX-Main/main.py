import serial # For Bluetooth
from nanpy import (ArduinoApi, SerialManager) # For Arduino
import RPi.GPIO as GPIO # For Raspberry Pi
from threading import Thread
from time import sleep, time
import os

# Bluetooth Serial Conniction
ser = serial.Serial('/dev/ttyS0', 9600, timeout = 0)

# Arduino Nanpy Conniction
connection = SerialManager()
a = ArduinoApi(connection = connection)

# Python Variables
sysMode = 0
serialRead = 0
check_mode = 1

# GPIO Variables
smoke_in = 35
TRIG = 3 
ECHO = 5

# GPIO Pins
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)
GPIO.setup(smoke_in, GPIO.IN)
GPIO.setup(TRIG,GPIO.OUT)
GPIO.setup(ECHO,GPIO.IN)

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

# Light Resistor Function
def r_room(ldr, room):
        if a.digitalRead(ldr) == True:
                a.digitalWrite(room, a.LOW)
        elif a.digitalRead(ldr) == False:
                a.digitalWrite(room, a.HIGH)

# Manual Control
def m_room(on, off, room):
	if serialRead == on:
		a.digitalWrite(room, a.HIGH)
	elif serialRead == off:
		a.digitalWrite(room, a.LOW)

# Mobile Check
def m_check(room, on, off):
	if a.digitalRead(room) == True:
		ser.write(on)
	elif a.digitalRead(room) == False:
		ser.write(off)

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
		elif serialRead == "0":
			sysMode = 0
		
		# Automatic Mode
		if sysMode == 0:
			r_room(r_office, office)
			r_room(r_kitchen, kitchen)
			r_room(r_meeting_room, meeting_room)
			r_room(r_waiting_room, waiting_room)
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
			m_room("1", "2", waiting_room)
			m_room("3", "4", kitchen)
			m_room("5", "6", office)
			m_room("7", "8", bath_room)
			m_room("e", "r", employee_room)
			m_room("m", "t", meeting_room)

# Smoke Thread
def smoke():
	while True:	

		# Global Variables
		global buzzer

		# Get Smoke Status
		if GPIO.input(35) == False: 
			a.digitalWrite(buzzer, a.HIGH)
			ser.write(b"S")
			sleep(2)
			a.digitalWrite(buzzer, a.LOW)
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
			ser.write(b"Z")
		else:
			a.digitalWrite(fan, a.LOW)
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
			sleep(1)
			ser.write(b"W")
		else:
			a.digitalWrite(buzzer, a.LOW)
			sleep(2)
			ser.write(b"w")

# Run The Threads
if __name__ == '__main__':
	Thread(target = lights).start()
	Thread(target = smoke).start()
	Thread(target = temp).start()
	Thread(target = motion).start()
from nanpy import (ArduinoApi, SerialManager)
from time import sleep

connection = SerialManager()
a = ArduinoApi(connection = connection)

a.pinMode(4, a.OUTPUT)

while True:
	serial = raw_input("Enter :")
	if serial == "1":
		print ("Led is ON")
		a.digitalWrite(4, a.HIGH)
	elif serial == "0":
		print ("Led is OFF")
		a.digitalWrite(4, a.LOW)

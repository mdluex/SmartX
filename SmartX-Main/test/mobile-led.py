import serial
from nanpy import (ArduinoApi, SerialManager)

ser = serial.Serial('/dev/ttyS0',9600)
connection = SerialManager()
a = ArduinoApi(connection = connection)

led = 4
a.pinMode(led, a.OUTPUT)

while True:
	read_serial=ser.readline().decode().strip()
	if str(read_serial) == 'on':
		a.digitalWrite(led, a.HIGH)
		write_serial=ser.write(b'Led is ON\r\n')
		print ("Led is ON")
	elif str(read_serial) == 'off':
		a.digitalWrite(led, a.LOW)
		write_serial=ser.write(b'Led is OFF\r\n')
		print ("Led is OFF")

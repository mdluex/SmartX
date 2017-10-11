import serial
import time

ser = serial.Serial('/dev/ttyS0',9600)
while True:
	read_serial=ser.readline().decode().strip()
	if str(read_serial) == 'mdluex':
		print ("Hello Mdluex")
	elif str(read_serial) == '1':
		print ("Good")
	elif str(read_serial) == '123':
		print ("Woow")
	else:
		print (read_serial)
	#write_serial=ser.write(b'mdluex')
	#time.sleep(1)
	

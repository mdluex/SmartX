import threading
from threading import Thread
from time import sleep

def one():
	num1 = 1
	while True:
		sleep(10)
		num1 = num1 + 1
		print ("Thread1 = ")
		print (num1)
	#return num1

def two():
	num2 = 10
	while True:
		sleep(5)
		num2 = num2 + 10
		print ("Thread2 = ")
		print (num2)
	#return num2

if __name__ == '__main__':
	Thread(target = one).start()
	Thread(target = two).start()

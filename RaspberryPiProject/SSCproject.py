import smbus
import time
import sys
import RPi.GPIO as GPIO

def calculateLux():
    LUX_HIGH_BYTE_REGISTER = 0x03
    LUX_LOW_BYTE_REGISTER = 0x04
    SENSOR_ADDRESS = 0x4a

    lux_HIGH = I2C_bus.read_i2c_block_data(SENSOR_ADDRESS, LUX_HIGH_BYTE_REGISTER)
    exponent = (0xf0 & lux_HIGH[0]) >> 4
    lux_LOW = I2C_bus.read_i2c_block_data(SENSOR_ADDRESS, LUX_LOW_BYTE_REGISTER)
    mantisa_HIGH = 0x0f & lux_HIGH[0]
    mantisa_LOW = 0x0f & lux_LOW[0]
    mantisa_FULL = (mantisa_HIGH << 4) | mantisa_LOW
    lux_final = 2 ** exponent * mantisa_FULL * 0.045
    return lux_final

def calculateDutyCycle(luxValue):
    value = 0
    unit = 1880

    if luxValue >= 0.045 and luxValue <= 188000:
        value = luxValue / unit
    elif luxValue < 0.045:
        value = 0
    elif luxValue > 188000:
        value = 100
    return (100 - int(round(value)))

def turnOffSystemLed():
    GPIO.output(12, GPIO.LOW)
    time.sleep(0.5)
    GPIO.output(12, GPIO.HIGH)
    time.sleep(0.1)
    GPIO.output(12, GPIO.LOW)
    time.sleep(0.2)
    GPIO.output(12, GPIO.HIGH)
    time.sleep(0.1)
    GPIO.output(12, GPIO.LOW)

systemLED = 12
functionalLED = 18
PIRSensorPIN = 16
buttonPIN = 24
I2C_bus = smbus.SMBus(1)
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(functionalLED, GPIO.OUT)
GPIO.setup(systemLED, GPIO.OUT)
GPIO.setup(PIRSensorPIN, GPIO.IN)
pwmLED = GPIO.PWM(functionalLED, 100)
pwmLED.start(0)
GPIO.setup(buttonPIN, GPIO.IN, pull_up_down=GPIO.PUD_UP)
countFlag = 0
turnOFF = 0
turnON = 0

print "Press the button to START the system!"

while not turnOFF:
    PIRSensorInput = GPIO.input(16)
    button = GPIO.input(24)
    if not button:
        countFlag += 1
        time.sleep(0.5)
    if countFlag == 1:
        if turnON == 0:
            print "System started!"
            GPIO.output(systemLED, GPIO.HIGH)
            time.sleep(1)
            turnON = 1
        lux = calculateLux()
        print "Current LUX value: ", lux, "\n"
        time.sleep(0.4)

        if PIRSensorInput == 1:
            value = calculateDutyCycle(lux)
            print "! MOTION DETECTED !"
            GPIO.output(systemLED, GPIO.LOW)
            time.sleep(0.2)
            GPIO.output(systemLED, GPIO.HIGH)
            pwmLED.ChangeDutyCycle(value)
        else:
            pwmLED.ChangeDutyCycle(0)

    if countFlag == 2:
        time.sleep(0.5)
        print "System powered off!"
        GPIO.output(systemLED, GPIO.LOW)
        countFlag = 0
        turnOFF = 1

turnOffSystemLed()

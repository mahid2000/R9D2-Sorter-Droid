package mainProject;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.Button;
public class ColorSorter {
	
	
	public static void main(String[] args) {
		
		BaseRegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.A);
		BaseRegulatedMotor mB = new EV3LargeRegulatedMotor(MotorPort.B);
		BaseRegulatedMotor mW = new NXTRegulatedMotor(MotorPort.C);
		BaseRegulatedMotor mT = new NXTRegulatedMotor(MotorPort.D);

        EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S1);
        SampleProvider rgbLevel = cs.getRGBMode();
        EV3UltrasonicSensor distSense = new EV3UltrasonicSensor(SensorPort.S2);
        SampleProvider us = distSense.getDistanceMode();
		NXTSoundSensor sensor = new NXTSoundSensor(SensorPort.S3);
		SampleProvider sound = sensor.getDBAMode();
        
        Behavior pBlue = new PushBlue(rgbLevel, mB, mT);
        Behavior pRed = new PushRed(rgbLevel, mR, mT);
        Behavior pWhite = new PushWhite(rgbLevel, mW, mT);
        Behavior move = new MoveBelt(mT);
        Behavior hs = new HandSafety(us, mT);
        Behavior clap = new ClapDetection(sound, mT);
        Behavior bat = new LowBattery();
        Behavior exit = new Exit();
        
        
        LCD.drawString("Welcome", 2, 2);
        LCD.drawString("Version: 0.9.1 - beta", 2, 3);
        LCD.drawString("Press enter to", 2, 5);
        LCD.drawString("start", 2, 6);
		Button.ENTER.waitForPress();
        
        Arbitrator ab = new Arbitrator(
        		new Behavior[] {
        				move, pBlue, pRed, pWhite, bat, clap, exit, hs
				});
        ab.go();
        ab.stop();
        
        
        
        
	}

}

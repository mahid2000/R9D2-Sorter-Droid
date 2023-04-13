package mainProject;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class PushWhite implements Behavior{
	
	boolean sup = false;
	SampleProvider rgbLevel;
	float[] level1 = new float[3];
	BaseRegulatedMotor mW;
	BaseRegulatedMotor mT;
	private static final int WHITE_PUSH = 7500;
	
	public PushWhite(SampleProvider rgbLevel, BaseRegulatedMotor mW, BaseRegulatedMotor mT) {
		this.rgbLevel = rgbLevel;
		this.mW = mW;
		this.mT = mT;
	}
	
	public boolean takeControl(){
		rgbLevel.fetchSample(level1, 0);
		
		// white
		if (level1[0] > 0.05 && level1[0] <= 0.075 && level1[1] > 0.05 && level1[1] <= 0.075 && level1[2] > 0.05
				&& level1[2] <= 0.085) {
			return true;
		}
		return false;
	}
	
	public void action() {
		if(!sup) {
			
	                mT.forward();
					LCD.clear();
					LCD.drawString("WHITE", 2, 3);
					mW.setSpeed(180);
					Delay.msDelay(WHITE_PUSH);
					mW.rotate(-60, true);
					Delay.msDelay(500);
					mW.rotate(60, true);
					Delay.msDelay(1000);
				
	
		}
		
	}
	
	public void suppress() {
		sup = true;
	}

}

package mainProject;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class PushBlue implements Behavior {

	boolean sup = false;
	SampleProvider rgbLevel;
	float[] level1 = new float[3];
	BaseRegulatedMotor mB;
	BaseRegulatedMotor mT;
	private static final int BLUE_PUSH = 4800;

	public PushBlue(SampleProvider rgbLevel, BaseRegulatedMotor mB, BaseRegulatedMotor mT) {
		this.rgbLevel = rgbLevel;
		this.mB = mB;
		this.mT = mT;
	}

	public boolean takeControl() {
		rgbLevel.fetchSample(level1, 0);

		// blue
		if (level1[0] > 0.001 && level1[0] <= 0.01 && level1[1] > 0.005 && level1[1] <= 0.02 && level1[2] > 0.04
				&& level1[2] <= 0.05) {
			return true;
		}
		return false;
	}

	public void action() {
		if (!sup) {

				mT.forward();
				LCD.clear();
				LCD.drawString("BLUE", 2, 3);
				mB.setSpeed(-180);
				Delay.msDelay(BLUE_PUSH);
				mB.rotate(-60, true);
				Delay.msDelay(500);
				mB.rotate(60, true);
				Delay.msDelay(1000);

		}

	}

	public void suppress() {
		sup = true;
	}

}

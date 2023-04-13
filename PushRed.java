package mainProject;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class PushRed implements Behavior {

	boolean sup = false;
	SampleProvider rgbLevel;
	float[] level1 = new float[3];
	BaseRegulatedMotor mR;
	BaseRegulatedMotor mT;
	private static final int RED_PUSH = 2000;

	public PushRed(SampleProvider rgbLevel, BaseRegulatedMotor mR, BaseRegulatedMotor mT) {
		this.rgbLevel = rgbLevel;
		this.mR = mR;
		this.mT = mT;
	}

	public boolean takeControl() {
		rgbLevel.fetchSample(level1, 0);

		if (level1[0] > 0.03 && level1[0] <= 0.04 && level1[1] > 0.004 && level1[1] <= 0.015 && level1[2] > 0.005
				&& level1[2] <= 0.02) {
			return true;
		}
		return false;
	}

	public void action() {
		if (!sup) {
			rgbLevel.fetchSample(level1, 0);
            mT.forward();
			LCD.clear();
			LCD.drawString("RED", 2, 3);
			mR.setSpeed(-180);
			Delay.msDelay(RED_PUSH);
			mR.rotate(-60, true);
			Delay.msDelay(500);
			mR.rotate(60, true);
			Delay.msDelay(1000);

		}

	}

	public void suppress() {
		sup = true;
	}

}

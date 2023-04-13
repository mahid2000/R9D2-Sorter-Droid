package mainProject;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class ClapDetection implements Behavior{
	
	    
	    private boolean suppressed; 
	    float[] level = new float[1];
		BaseRegulatedMotor mT;
		SampleProvider sound;
		float clap = (float) 0.4;
		float currentLevel = 0;
	    
	    public ClapDetection(SampleProvider sound, BaseRegulatedMotor mT) {
	    	this.sound = sound;
	    	this.mT = mT;
	    }
	    
	    public boolean takeControl() {
	    	sound.fetchSample(level, 0);

			currentLevel = level[0];
			if (currentLevel > clap) {
				return true;
			}
			return false;
	    }
	    public void suppress() {
	        suppressed = true;
	    }
	    public void action() {
	        if (!suppressed) {
	            mT.stop();
	            LCD.clear();
	            LCD.drawString("CLAP HEARD", 2, 2);
	            Delay.msDelay(3000);
	            System.exit(0);
	    }

	}

}
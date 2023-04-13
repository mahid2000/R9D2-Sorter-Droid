package mainProject;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class HandSafety implements Behavior {

    boolean sup = false;
    float[] distance = new float[1];
    SampleProvider dist;
    BaseRegulatedMotor mT;
    
    public HandSafety(SampleProvider us, BaseRegulatedMotor mT) {
        this.dist = us;
        this.mT = mT;
    }

    public boolean takeControl(){
        dist.fetchSample(distance, 0);
        if(distance[0] < 0.15) {
            return true;
        }
        return false;
    }

    public void action() {
        if(!sup) {
        	LCD.clear();
    		LCD.drawString("HAND DETECTED", 2, 3);
            mT.stop();
            Delay.msDelay(5000);
            System.exit(0);
        }

    }

    public void suppress() {
        sup = true;
    }

}

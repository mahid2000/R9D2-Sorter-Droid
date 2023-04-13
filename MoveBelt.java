package mainProject;

import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;

public class MoveBelt implements Behavior {


        private boolean supressed;
        BaseRegulatedMotor mT;
        
        public MoveBelt(BaseRegulatedMotor mT) {
            this.mT = mT;
        }

        public boolean takeControl() {
              return true;

        }

        public void suppress() {
            supressed = true;
        }

        public void action() {
            while (!supressed) {
            	LCD.clear();
            	LCD.drawString("BELT MOVING", 2, 3);
	            mT.setSpeed(50);
	            mT.forward();
            }
        }


    
}
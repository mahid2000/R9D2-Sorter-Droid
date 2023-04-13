package mainProject;

import lejos.hardware.Battery;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import lejos.hardware.Sound;

public class LowBattery implements Behavior {
	
	boolean sup = false;
    
    Battery bat;
    
	
	public boolean takeControl(){
		if(bat.getVoltage() < 1) {
			return true;
		}
		return false;
	}
	
	public void action() {
		while(!sup) {
			Sound.twoBeeps();
			for(int i = 0; i < 5; i++) {
	            Delay.msDelay(500);
				LCD.clearDisplay();
	            LCD.drawString((String.format("%f", "BATTERY LOW")),3,2);
	            Delay.msDelay(500);
			}
			System.exit(0);
		}
		
	}
	
	public void suppress() {
		sup = true;
	}

}

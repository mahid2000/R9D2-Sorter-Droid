package mainProject;

import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;

public class Exit implements Behavior {
	
    boolean sup = false;
	
	public boolean takeControl(){
		if(Button.ESCAPE.isDown())return true;
		return false;
		
	}
	
	public void action() {
		while(!sup) {
			System.exit(0);
		}
		
	}
	
	public void suppress() {
		sup = true;
	}

}

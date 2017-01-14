package org.usfirst.frc.team4738.enums;

/**
 * @author Ghjf544912
 * Please, actually say the author, to know
 */
public enum ControllerType {
	GP(0), Xbox(10), Attack3(11), X3D(12);
	
	private int buttonCount = 0;
	
	private ControllerType(int buttonCount){
		this.buttonCount = buttonCount;
	}
	
	public int getButtonCount(){
		return buttonCount;
	}
}

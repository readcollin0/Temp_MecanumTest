package org.usfirst.frc.team4738;

import org.usfirst.frc.team4738.enums.DriveType;
import org.usfirst.frc.team4738.wrapper.Axes;

/**
 * Gotta have da 4matting! :)
 * @author Stephen, Collin
 */
public class Constants {
	
	public static final DriveType DRIVE_MODEL = DriveType.MECANUM_PARABOLIC;

	public static final int[]
		DIO_PORT={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25},
		RELAY_PORT={0,1,2,3},
		ANALOG_PORT={0,1,2,3},
		PWM_PORT={0,1,2,3,4,5,6,7,8,9},
		PCM_PORT ={0,1,2,3,4,5,6,7},
		CAN_ID= {0,1,2,3,4,5,6,7,8,9,10},
		DRIVER_STATION_PORT={0,1,2,3,4,5};
	
	public static final double 
		MAX_RAMP_ANGLE = 10, 
		MIN_RAMP_ANGLE = 0,
		MAX_RAMP_SPEED = 2400, //This is in RPM
		DEGREES_PER_MILLISECONDS = 360 * MAX_RAMP_SPEED / 60 / 1000,
		MAX_RAMP_SPEED_DEGREES = DEGREES_PER_MILLISECONDS * MAX_RAMP_ANGLE;
		
	public static final Axes // The order of wheels is the order in the Google Drive folder.
		WHEEL_ONE = new Axes(1, 1), // It is distance left to right, then distance front to back
		WHEEL_TWO = new Axes(1, -1), // Front left, front right, back left, back right
		WHEEL_THREE = new Axes(-1, -1),
		WHEEL_FOUR = new Axes(-1, 1);
	
}

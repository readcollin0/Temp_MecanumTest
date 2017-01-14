package org.usfirst.frc.team4738.wrapper;

import java.util.ArrayList;

import org.usfirst.frc.team4738.Constants;

import edu.wpi.first.wpilibj.VictorSP;

/**
 * @author Everyone
 */
public class Drive {

	private ArrayList<VictorSP> motors = new ArrayList<>();
	
	/** 
	 * This is a constructor you idiot - <3 Jacob
	 * @author Stephen Barrack (who forgot what a constructor was)
	 * @param ports switches between left and right motors for each input
	 */
	public Drive(int... ports){
		for (int port : ports) {
			VictorSP motor = new VictorSP(port);
			motors.add(motor);
		}
	}
	
	/**
	 * @param xAxis x-axis of the joystick
	 * @param yAxis y-axis of the joystick
	 */
	
	public void drive(Axes leftStick, Axes rightStick, double angVel) {
		double[] speeds = Constants.DRIVE_MODEL.getDriveVars(leftStick, rightStick, angVel);
		for (int i = 0; i < motors.size(); i++) {
			motors.get(i).set(speeds[i]);
		}
	}
	
}

package org.usfirst.frc.team4738.robot;

import org.usfirst.frc.team4738.Constants;
import org.usfirst.frc.team4738.interfaces.Gamepad;
import org.usfirst.frc.team4738.wrapper.Timer;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pickup {

	Timer timer;
	double currentPosition;
	
	VictorSP 
		intakeMotor,
		rampMotor;
	
	DigitalInput 
		topSwitch,
		bottomSwitch;
	
	public Pickup(VictorSP intakeMotor, VictorSP rampMotor, DigitalInput topSwitch, DigitalInput bottomSwitch){
		this.intakeMotor = intakeMotor;
		this.rampMotor = rampMotor;
		timer = new Timer();
		this.topSwitch = topSwitch;
		this.bottomSwitch = bottomSwitch;
	}
	
	public void intakeMotor(Gamepad gamepad){
		for (int i = 0; i < gamepad.getButtonCount(); i++) {
			if(gamepad.getButton(i)){
				if(i % 2 == 0){
					intakeMotor.set(1);
					return;
				} else{
					intakeMotor.set(-1);
					return;
				}
			} else{
				intakeMotor.set(0);
			}
		}
	}
	
	//NOTE: I do now know if this is the correct math
	//TODO: Check if this math is correct
	public void setPosition(double position){	
		double setSpeed;
		
		if(Math.abs(position) > 1){
			position = Math.signum(position);
		}
		
		currentPosition += ((rampMotor.getSpeed() * Constants.DEGREES_PER_MILLISECONDS) * (timer.getDeltaTime() + (Math.random() / 100)));
		
		setSpeed = ((position * Constants.MAX_RAMP_ANGLE) - currentPosition) / Constants.MAX_RAMP_ANGLE;
		
		if(SmartDashboard.getBoolean("OverridePosition", true)){
			currentPosition = Math.signum(position);
			setSpeed = position;
			SmartDashboard.putDouble("ramp speed", setSpeed);
			
		}
		
		if(topSwitch.get()){
			currentPosition = Constants.MAX_RAMP_ANGLE;
			if(setSpeed < 0)
				setSpeed = 0;
		} else if(bottomSwitch.get()){
			currentPosition = Constants.MIN_RAMP_ANGLE;	
			if(setSpeed > 0)
				setSpeed = 0;	
		}
		
		SmartDashboard.putDouble("ramp", this.getPosition());
		
		//TODO Delete Me!!!
		//setSpeed = 0;
		rampMotor.set(setSpeed);		
	}	
	
	public double getPosition(){
		if(SmartDashboard.getBoolean("OverridePosition", false)){
			return currentPosition;
		}
		return currentPosition / Constants.MAX_RAMP_ANGLE;
	}
}

package org.usfirst.frc.team4738.robot;

import org.usfirst.frc.team4738.Constants;
import org.usfirst.frc.team4738.enums.ControllerType;
import org.usfirst.frc.team4738.enums.Directions;
import org.usfirst.frc.team4738.enums.XboxButtons;
import org.usfirst.frc.team4738.interfaces.Gamepad;
import org.usfirst.frc.team4738.interfaces.XboxController;
import org.usfirst.frc.team4738.wrapper.Camera;
import org.usfirst.frc.team4738.wrapper.CameraGimbal;
import org.usfirst.frc.team4738.wrapper.Drive;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotControl {
	
	public Drive drive;
	Pickup pickup;
	
	VictorSP 
		intakeMotor,
		rampMotor;
	
	DigitalInput 
		topButton,
		bottomButton;
	
	
	boolean lookingForward = true;
	boolean overrideCam = false;
	
	public RobotControl(){
		drive = new Drive(Constants.PWM_PORT[0], Constants.PWM_PORT[1]);
		intakeMotor = new VictorSP(Constants.PWM_PORT[2]);
		rampMotor = new VictorSP(Constants.PWM_PORT[3]);
		topButton = new DigitalInput(Constants.DIO_PORT[4]);
		bottomButton = new DigitalInput(Constants.DIO_PORT[5]);
		
		pickup = new Pickup(intakeMotor, rampMotor, topButton, bottomButton);
	}
	
	public void updateControl(XboxController xbox, Gamepad gamepad){
		updateDriveControl(xbox);
		updatePickupControl(gamepad);		
		
		SmartDashboard.putBoolean("Top Switch", topButton.get());
		SmartDashboard.putBoolean("Bottom Switch", bottomButton.get());
		}
	
	public void updateDriveControl(XboxController xbox){
		lookingForward = xbox.getToggle(XboxButtons.A);
		
		if(lookingForward){
			drive.drive(xbox.getLeftStick(), xbox.getRightStick(), 0D);
		} else{
			drive.drive(xbox.getLeftStick().invert(), xbox.getRightStick().invert(), 0D);
		}
	}
	
	public void updatePickupControl(Gamepad gamepad){
		pickup.intakeMotor(gamepad);
				
		if(gamepad.getControllerType().equals(ControllerType.Attack3)){
			pickup.setPosition(gamepad.getAxis(2));
		} else if(gamepad.getControllerType().equals(ControllerType.X3D)){
			pickup.setPosition(gamepad.getAxis(3));
		}
	}
	
	public void updateControl(XboxController xbox, Gamepad gamepad, CameraGimbal gimbal, Camera cam){
		updateControl(xbox, gamepad);
		gimbal.setPan(xbox.getRightStick().getX());
		updateCamera(xbox, cam);
	}
	
	public void updateControl(XboxController xbox, Gamepad gamepad, Camera cam){
		updateControl(xbox, gamepad);
		updateCamera(xbox, cam);
	}
	
	public void updateCamera(XboxController xbox, Camera cam){
		SmartDashboard.putBoolean("FrontIndicator", lookingForward);
		SmartDashboard.putBoolean("BackIndicator", !lookingForward);
		if(!overrideCam){
			if(lookingForward && xbox.getButtonDown(XboxButtons.A)){
				cam.changeCamera(0);
			} else if(xbox.getButton(XboxButtons.A)){
				cam.changeCamera(1);	
			}
		}
		
		for(int i = 0; i < cam.camCount(); i++){
			if(cam.camCount() < 4){
				if(xbox.getDPad(Directions.values()[i * 2])){
					overrideCam = true;
					cam.changeCamera(i);
				}
			}else{
				if(xbox.getDPad(Directions.values()[i])){
					try{
						overrideCam = true;
						cam.changeCamera(i);
					} catch(Exception e){
						System.err.println("cam" + i + " does not exist");
					}
				}
			}
		}
	}
	
	public void move(XboxController xbox, Gamepad gamepad, Camera cam){
		updateControl(xbox, gamepad, cam);
	}
	
	public void move(XboxController xbox, Camera cam){
		updateDriveControl(xbox);
		updateCamera(xbox, cam);
	}
}

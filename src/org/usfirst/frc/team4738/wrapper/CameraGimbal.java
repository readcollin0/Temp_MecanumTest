package org.usfirst.frc.team4738.wrapper;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team4738.util.MovingAverage;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Servo;


public class CameraGimbal {
	
	private Servo panServo, tiltServo;
	public AnalogGyro gyro;
	public MovingAverage averageX, averageY;
	
	Timer gyroUpdateScheduler;
	Thread gimbalThread;
	
	boolean gyroStabilizing = false;
	
	public CameraGimbal(int panPort, int tiltPort){
		panServo = new Servo(panPort);		
		tiltServo = new Servo(tiltPort);
		averageX = new MovingAverage(3);
		averageY = new MovingAverage(3);
	}
	
	/**
	 * 
	 * @param panPort
	 * @param tiltPort
	 * @param gyroPort GYRO IS IN THE ANALOG PORT!!!!
	 */
	public CameraGimbal(int panPort, int tiltPort, int gyroPort){
		panServo = new Servo(panPort);		
		tiltServo = new Servo(tiltPort);
		gyro = new AnalogGyro(gyroPort);
		averageX = new MovingAverage(3);
		averageY = new MovingAverage(3);
		
	}
	
	
	public void setPan(double pan){
		panServo.set(averageX.average((pan + 1) / 2));
	}
	
	public void setTilt(double tilt){
		tiltServo.set(averageY.average((tilt + 1) / 2));
	}
	
	public void setPosition(double pan, double tilt){
		this.setTilt(tilt);
		this.setPan(pan);
	}
	
	
	public double getPan(){
		return (panServo.get() * 2) - 1;
	}
	
	public double getTilt(){
		return (tiltServo.get() * 2) - 1;
	}
	
	/**
	 * Run this ONLY if you have a Gyro
	 */
	public void setTiltBasedOnGyro(){
		try{
			setTilt((gyro.getAngle() / 90));
		} catch(NullPointerException e){
			System.err.println("I FRICKEN SAID DON'T RUN THIS IF YOU DON'T HAVE A GYRO YOU IDIOT!!!!!");
		}
	
	}
	
	public void startGyroStabilization(){
		gyroStabilizing = true;
		if(gimbalThread == null){
			initializeGimbalThread();
		}
		gimbalThread.start();
	}
	
	public void initializeGimbalThread(){		  
		gyroStabilizing = true;
		gyroUpdateScheduler = new Timer();
		
		gimbalThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				gyroUpdateScheduler.scheduleAtFixedRate(new TimerTask() {
					
					@Override
					public void run() {
						setTiltBasedOnGyro();
						
					}
				}, 0, 1);
			}
		});
		
		gimbalThread.setName("Gimbal Thread");
	}
	
	public void stopGyroStabilization(){
		try{
			gimbalThread.interrupt();
			gyroStabilizing = false;
		}catch(Exception e){
			System.err.println(e.toString());
		}
	}
}

package org.usfirst.frc.team4738.enums;

import org.usfirst.frc.team4738.Constants;
import org.usfirst.frc.team4738.util.Mathd;
import org.usfirst.frc.team4738.wrapper.Axes;

public enum DriveType {
	
	MECANUM_PARABOLIC {
		@Override
		public double[] getDriveVars(Axes leftStick, Axes rightStick, double angVel) {
			
			double wheel1 = Mathd.curve(leftStick.getY()) - Mathd.curve(leftStick.getX()) + angVel 
					* (Constants.WHEEL_ONE.getX() + Constants.WHEEL_ONE.getY());
			double wheel2 = Mathd.curve(leftStick.getY()) + Mathd.curve(leftStick.getX()) - angVel 
					* (Constants.WHEEL_TWO.getX() + Constants.WHEEL_TWO.getY());
			double wheel3 = Mathd.curve(leftStick.getY()) - Mathd.curve(leftStick.getX()) - angVel 
					* (Constants.WHEEL_THREE.getX() + Constants.WHEEL_THREE.getY());
			double wheel4 = Mathd.curve(leftStick.getY()) + Mathd.curve(leftStick.getX()) + angVel 
					* (Constants.WHEEL_FOUR.getX() + Constants.WHEEL_FOUR.getY());
			
			double[] speeds = {wheel1, wheel2, wheel3, wheel4};
			return speeds;
		}
	},
	MECANUM_LINEAR {
		@Override
		public double[] getDriveVars(Axes leftStick, Axes rightStick, double angVel) {
			double wheel1 = leftStick.getY() - leftStick.getX() + angVel 
					* (Constants.WHEEL_ONE.getX() + Constants.WHEEL_ONE.getY());
			double wheel2 = leftStick.getY() + leftStick.getX() - angVel 
					* (Constants.WHEEL_TWO.getX() + Constants.WHEEL_TWO.getY());
			double wheel3 = leftStick.getY() - leftStick.getX() - angVel 
					* (Constants.WHEEL_THREE.getX() + Constants.WHEEL_THREE.getY());
			double wheel4 = leftStick.getY() + leftStick.getX() + angVel 
					* (Constants.WHEEL_FOUR.getX() + Constants.WHEEL_FOUR.getY());
			
			double[] speeds = {wheel1, wheel2, wheel3, wheel4};
			return speeds;
		}
	},
	ARCADE_LINEAR {
		@Override
		public double[] getDriveVars(Axes leftStick, Axes rightStick, double angVel) {
			double[] speeds = {
					leftStick.getX()+leftStick.getY(),
					leftStick.getX()-leftStick.getY(),
					leftStick.getX()+leftStick.getY(),
					leftStick.getX()-leftStick.getY()
			};
			return speeds;
		}
	},
	ARCADE_PARABOLIC {
		@Override
		public double[] getDriveVars(Axes leftStick, Axes rightStick, double angVel) {
			double[] speeds = {
					Mathd.curve(leftStick.getX()) + Mathd.curve(leftStick.getY()),
					Mathd.curve(leftStick.getX()) - Mathd.curve(leftStick.getY()),
					Mathd.curve(leftStick.getX()) + Mathd.curve(leftStick.getY()),
					Mathd.curve(leftStick.getX()) - Mathd.curve(leftStick.getY())
			};
			
			return speeds;
		}
	},
	TANK_LINEAR {
		@Override
		public double[] getDriveVars(Axes leftStick, Axes rightStick, double angVel) {
			double[] speeds = {
					leftStick.getY(),
					rightStick.getY(),
					leftStick.getY(),
					rightStick.getY()
			};
			return speeds;
		}
	},
	TANK_PARABOLIC {
		@Override
		public double[] getDriveVars(Axes leftStick, Axes rightStick, double angVel) {
			double[] speeds = {
					Mathd.curve(leftStick.getY()),
					Mathd.curve(rightStick.getY()),
					Mathd.curve(leftStick.getY()),
					Mathd.curve(rightStick.getY())
			};
			return speeds;
		}
	};
	
	public abstract double[] getDriveVars(Axes leftStick, Axes rightStick, double angVel);	
}

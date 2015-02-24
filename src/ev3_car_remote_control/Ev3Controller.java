package ev3_car_remote_control;

import java.rmi.RemoteException;

import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;

public class Ev3Controller {
	
	RemoteEV3 ev3 = null;
	RMIRegulatedMotor turningMotor; 
	RMIRegulatedMotor gogoMotorA;
	RMIRegulatedMotor gogoMotorB;
	
	private static final int maxSpeed = 740;
	private static final int initialSpeed = maxSpeed / 2;
	private static final int initialTurningSpeed = 800;
	private static final int gogoMotorAcceleration = 700;
	private static final int turningMotorAcceleration = 1000;
	
	public boolean connected(){
		return ev3 != null;
	}
	
	public void accelerate(boolean forwards){
		if(connected()){
			try {
				if(forwards){
					gogoMotorA.backward();
					gogoMotorB.forward();
				}else{
					gogoMotorA.forward();
					gogoMotorB.backward();
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setMoveSpeed(int speed){
		if(connected()){
			int ev3Speed = (int) (((float)speed * .01) * maxSpeed);
			try {
				gogoMotorA.setSpeed(ev3Speed);
				gogoMotorB.setSpeed(ev3Speed);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stopAccelerate(){
		if(connected()){
			try {
				gogoMotorA.flt(true);
				gogoMotorB.flt(true);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void turn(boolean left){
		if(connected()){
			if(left){
				try {
					turningMotor.forward();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}else{
				try {
					turningMotor.backward();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void stopTurn(){
		try {
			turningMotor.stop(true);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public boolean connectAndSetupMotors(String ip){
		boolean retVal = true;
		
		if(connect(ip)){
			setupMotors();
		}else{
			retVal = false;
		}
		
		return retVal;
	}
	
	public void closeConnection(){
		try {
			if(gogoMotorA != null) gogoMotorA.close();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		try {
			if(gogoMotorB != null) gogoMotorB.close();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		try {
			if(turningMotor != null) turningMotor.close();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private boolean connect(String ip){
		boolean retVal = true;
		
		try{
			ev3 = new RemoteEV3(ip);
		}catch(Exception e){
			e.printStackTrace();
			retVal = false;
		}
		
		return retVal;
	}
	
	private void setupMotors(){
		gogoMotorA = ev3.createRegulatedMotor("A",'L');
		
		gogoMotorB = ev3.createRegulatedMotor("B",'L');
		turningMotor = ev3.createRegulatedMotor("C",'M');
	
		try {
			setMoveSpeed(initialSpeed);
			turningMotor.setSpeed(initialTurningSpeed);
			
			gogoMotorA.setAcceleration(gogoMotorAcceleration);
			gogoMotorB.setAcceleration(gogoMotorAcceleration);
			turningMotor.setAcceleration(turningMotorAcceleration);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}

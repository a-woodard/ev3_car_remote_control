package ev3_car_remote_control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;


public abstract class MovementAction extends AbstractAction{
	protected Ev3Controller ev3;
	protected boolean clockwiseDirection;
	
	protected boolean isCurrentlyPressed = false;
	
	public MovementAction(Ev3Controller ev3, boolean clockwiseDirection){
		this.ev3 = ev3;
		this.clockwiseDirection = clockwiseDirection;
		
		
//		System.out.println("here");
		
	}
	
	public abstract void downAction();	
	public abstract void upAction();
	public void changeSpeed(int newSpeed){};
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getID() == KeyEvent.KEY_PRESSED){
			if(!isCurrentlyPressed){
				isCurrentlyPressed = true;
				downAction();
			}
		}else if(e.getID() == KeyEvent.KEY_RELEASED){
			isCurrentlyPressed = false;
			upAction();
		}
	}
}

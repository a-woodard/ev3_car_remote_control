package ev3_car_remote_control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public  class GogoAction extends MovementAction{

	public GogoAction(Ev3Controller ev3, boolean clockwiseDirection) {
		super(ev3,clockwiseDirection);
	}

	@Override
	public void downAction() {
		ev3.accelerate(clockwiseDirection);
	}

	@Override
	public void upAction() {
		ev3.stopAccelerate();
	}

	@Override
	public void changeSpeed(int newSpeed) {
		ev3.setMoveSpeed(newSpeed);
		if(!isCurrentlyPressed){
			downAction();
		}
	}

}

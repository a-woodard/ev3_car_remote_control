package ev3_car_remote_control;

import java.awt.event.ActionEvent;

public class TurnAction extends MovementAction{

	public TurnAction(Ev3Controller ev3,boolean clockwiseDirection) {
		super(ev3,clockwiseDirection);
	}
	
	@Override
	public void downAction() {
		ev3.turn(clockwiseDirection);
	}

	@Override
	public void upAction() {
		ev3.stopTurn();
	}

}
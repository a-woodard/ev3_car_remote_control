package ev3_car_remote_control;



public  class GogoAction extends MovementAction{

	public GogoAction(Ev3Controller ev3, boolean clockwiseDirection, MotorInfo motorInfo) {
		super(ev3,clockwiseDirection,motorInfo);
	}

	@Override
	public void downAction() {
		ev3.accelerate(motorInfo.port, clockwiseDirection);
	}

	@Override
	public void upAction() {
		ev3.stopAccelerate(motorInfo.port);
	}

	@Override
	public void changeSpeed(int newSpeed) {
		ev3.setMoveSpeed(motorInfo.port, newSpeed);
		if(isCurrentlyPressed){
			downAction();
		}
	}

}

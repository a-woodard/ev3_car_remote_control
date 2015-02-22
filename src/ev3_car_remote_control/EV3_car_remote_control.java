package ev3_car_remote_control;

import javax.swing.JFrame;

public class EV3_car_remote_control {

	public static void main(String[] args) {
		String ip = "";
		
		try {
	        ip = args[0];
	    } catch (Exception e) {
	    }
		
		JFrame guiFrame = new GuiFrame(ip);
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setLocationRelativeTo(null);
		guiFrame.setVisible(true);
		
	}

}

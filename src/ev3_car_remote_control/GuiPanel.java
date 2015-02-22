package ev3_car_remote_control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class GuiPanel extends JPanel {
	
	private JButton fowardButton;
	private JButton leftButton;
	private JButton rightButton;
	private JButton backwardButton;
	private JButton btnConnect;
	private JSlider powerSlider;
	private JTextArea ipTextBox;
	
	public Ev3Controller ev3;
	
	public GuiPanel(String ip) {
		
		btnConnect = new JButton("Connect");
		
		powerSlider = new JSlider();
		
		ipTextBox = new JTextArea();
		fowardButton = new JButton("^");
		leftButton = new JButton("<");
		rightButton = new JButton(">");
		backwardButton = new JButton("v");
		
		
		SetupPanel(ip);
	}
	
	public GuiPanel getOuter() {
	    return GuiPanel.this;
	}
	
	private class connectButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			GuiFrame outerFrame = (GuiFrame) SwingUtilities.getWindowAncestor(getOuter());
			
			try {
				outerFrame.ev3Controller.connectAndSetupMotors(ipTextBox.getText());
			} catch (Exception e1) {
				btnConnect.setBackground(Color.red);
				e1.printStackTrace();
			}
			
			if(outerFrame.ev3Controller.connected()){
				btnConnect.setBackground(Color.green);
			}
		}
	}
	
	private class SliderChangeListener implements ChangeListener{
		long timeLastEventFired = System.currentTimeMillis();
		long timeBetweenCalls = 200;
		GuiFrame outerFrame;
		
		@Override
		public void stateChanged(ChangeEvent e) {
			if(outerFrame == null){
				outerFrame = (GuiFrame) SwingUtilities.getWindowAncestor(getOuter());
			}
			
			long currentTime = System.currentTimeMillis();

			if(currentTime - timeLastEventFired > timeBetweenCalls){
				timeLastEventFired = currentTime;
				
				if(outerFrame.ev3Controller.connected()){
					outerFrame.ev3Controller.setMoveSpeed(powerSlider.getValue());
				}
			}
		}
	}
	
	private void SetupPanel(String ip){
		ipTextBox.setText(ip);
		
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		
		add(leftButton);
		add(fowardButton);
		add(rightButton);
		add(backwardButton);
		add(btnConnect);
		add(ipTextBox);
		add(powerSlider);
		
		leftButton.setBounds(10, 151, 43, 23);
		fowardButton.setBounds(63, 117, 43, 23);
		backwardButton.setBounds(63, 151, 43, 23);
		rightButton.setBounds(116, 151, 43, 23);
		btnConnect.setBounds(92, 41, 89, 23);
		ipTextBox.setBounds(10, 11, 171, 22);
		powerSlider.setBounds(191, 11, 29, 163);
		
		powerSlider.setOrientation(SwingConstants.VERTICAL);
		
		btnConnect.addActionListener(new connectButtonListener());
		powerSlider.addChangeListener(new SliderChangeListener());
	}
}

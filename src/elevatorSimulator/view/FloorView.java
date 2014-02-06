package elevatorSimulator.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import elevatorSimulator.controller.FloorController;
import elevatorSimulator.model.FloorListModel;
import elevatorSimulator.model.FloorModel;
import elevatorSimulator.model.ModelEvent;
/*
 * Displays an up and down button
 */
public class FloorView extends JFrameView{
	
	public static final String UP = "Up";
	public static final String DOWN = "Down";

	JTextField doorsState = new JTextField();
	JLabel doorsStateLabel = new JLabel("Doors: ");
	
	private JButton jDwnButton;
	private JButton jUpButton;
	
	public FloorView(FloorModel model, FloorController controller) {
		super(model, controller);
		
		Handler handler = new Handler();
		
		this.setTitle("Floor " + ((FloorModel)getModel()).getFloorId());
		this.setSize(200, 150);
		
		JPanel buttonPanel = new JPanel();
		JPanel statePanel = new JPanel();
		
		buttonPanel.setLayout(new GridLayout(3, 1 , 5, 5));
		statePanel.setLayout(new GridLayout(1,1,5,5));
		
		if(!((FloorModel)getModel()).isTopFloor()){//((FloorListModel)getModel()).getNumOfFloors()){
			jUpButton = new JButton(UP);
			jUpButton.setEnabled(!((FloorModel)getModel()).isUpPressed());
			jUpButton.addActionListener(handler);
			buttonPanel.add(jUpButton);
		}
		
		if(!((FloorModel)getModel()).isBottomFloor()){
			jDwnButton = new JButton(DOWN);	
			jDwnButton.setEnabled(!((FloorModel)getModel()).isDownPressed());
			jDwnButton.addActionListener(handler);
			buttonPanel.add(jDwnButton);
		}
		
		doorsStateLabel.setLabelFor(doorsState);
		statePanel.add(doorsStateLabel);
		
		doorsState.setEditable(false);
		doorsState.setText(((FloorModel)getModel()).getDoors().getDoorState());
		doorsState.setHorizontalAlignment(JFormattedTextField.RIGHT);
		statePanel.add(doorsState);
	
		this.getContentPane().add(buttonPanel, BorderLayout.NORTH);
		this.getContentPane().add(statePanel, BorderLayout.SOUTH);
		//pack();
		
		
	}
	
	public JButton getDownButton(){
		return jDwnButton;
	}
	
	public JButton getUpButton(){
		return jUpButton;
	}

	@Override
	public void modelChanged(ModelEvent event) {
		doorsState.setText(((FloorModel)getModel()).getDoors().getDoorState());
		if(jUpButton != null)
			jUpButton.setEnabled(!((FloorModel)getModel()).isUpPressed());
		if(jDwnButton != null)
			jDwnButton.setEnabled(!((FloorModel)getModel()).isDownPressed());
	}
	class Handler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			((FloorController)getController()).operation(e.getActionCommand());
		}
	}
	

}

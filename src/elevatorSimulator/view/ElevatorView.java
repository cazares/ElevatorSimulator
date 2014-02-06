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

import elevatorSimulator.controller.Controller;
import elevatorSimulator.controller.ElevatorController;
import elevatorSimulator.model.ElevatorModel;
import elevatorSimulator.model.Model;
import elevatorSimulator.model.ModelEvent;

/*
 * Displays buttons to goto all floors, open/close doors buttons and states of the elevator (speed, direction, destination etc?)
 */
public class ElevatorView extends JFrameView{
	
	public static final String OPEN = "Open";
	public static final String CLOSE = "Close";
	
	private JTextField elevHeight = new JTextField();
	private JLabel elevHeightLabel = new JLabel("Elevator Height: ");
	
	private JTextField elevSpeed = new JTextField();
	private JLabel elevSpeedLabel = new JLabel("Elevator Speed: ");
	
	private JTextField elevCurFloor = new JTextField();
	private JLabel elevCurFloorLabel = new JLabel("Elevator Floor: ");
	
	private JTextField stateOfDoors = new JTextField();
	private JLabel stateOfDoorsLabel = new JLabel("Doors: ");
	
	private JButton floorButtons[];
	private int numOfFloors;
	

	public ElevatorView(Model model, Controller controller) {
		super(model, controller);
		
		numOfFloors = ((ElevatorModel)getModel()).getFloorList().getNumOfFloors();
		Handler handler = new Handler();
		JPanel elevButtons = new JPanel();
		elevButtons.setLayout(new GridLayout(numOfFloors + 2/2, numOfFloors + 2/2, 5, 5));
		JPanel elevStates = new JPanel();
		elevStates.setLayout(new GridLayout(4, 2, 5, 5));
		
		//Allocating floor number buttons and open close buttons
		floorButtons = new JButton[numOfFloors];
		for(int i = 0; i < numOfFloors; i++){
			floorButtons[i] = new JButton((i + 1) + "");
			floorButtons[i].addActionListener(handler);
			elevButtons.add(floorButtons[i]);
		}
		
		JButton buttonOpen = new JButton(OPEN);
		buttonOpen.addActionListener(handler);
		elevButtons.add(buttonOpen);
		
		JButton buttonClose = new JButton(CLOSE);
		buttonClose.addActionListener(handler);
		elevButtons.add(buttonClose);
		
		
		//Frame properties
		this.setTitle("Elevator View");
		this.setSize(500, 500);
		
		
		//Setting format of textfields and labels
		elevHeightLabel.setLabelFor(elevHeight);
		elevStates.add(elevHeightLabel);
		
		elevHeight.setEditable(false);
		elevHeight.setHorizontalAlignment(JFormattedTextField.RIGHT);
		elevHeight.setText(((ElevatorModel)getModel()).getCurHeight() + "");
		elevStates.add(elevHeight);
		
		elevSpeedLabel.setLabelFor(elevSpeed);
		elevStates.add(elevSpeedLabel);
		
		elevSpeed.setEditable(false);
		elevSpeed.setHorizontalAlignment(JFormattedTextField.RIGHT);
		elevSpeed.setText(((ElevatorModel)getModel()).getEngine().getSpeed() + "");
		elevStates.add(elevSpeed);
		
		
		elevCurFloorLabel.setLabelFor(elevCurFloor);
		elevStates.add(elevCurFloorLabel);
		
		elevCurFloor.setEditable(false);
		elevCurFloor.setHorizontalAlignment(JFormattedTextField.RIGHT);
		elevCurFloor.setText(((ElevatorModel)getModel()).getCurFloor() + "");
		elevStates.add(elevCurFloor);
		
		stateOfDoorsLabel.setLabelFor(stateOfDoors);
		elevStates.add(stateOfDoorsLabel);
		
		stateOfDoors.setEditable(false);
		stateOfDoors.setHorizontalAlignment(JFormattedTextField.RIGHT);
		stateOfDoors.setText((((ElevatorModel)getModel()).getElevDoors().getDoorState()));
		elevStates.add(stateOfDoors);
		
		
		this.getContentPane().add(elevButtons, BorderLayout.NORTH);
		this.getContentPane().add(elevStates, BorderLayout.SOUTH);
		
		pack();
	}

	@Override
	public void modelChanged(ModelEvent event) {
		stateOfDoors.setText((((ElevatorModel)getModel()).getElevDoors().getDoorState()));
		elevSpeed.setText(((ElevatorModel)getModel()).getEngine().getSpeed() + "");
		elevCurFloor.setText(((ElevatorModel)getModel()).getCurFloor() + "");
		elevHeight.setText(((ElevatorModel)getModel()).getCurHeight() + "");
		
		for(int i = 0; i < numOfFloors; i++)
			floorButtons[i].setEnabled(!((ElevatorModel)getModel()).getFloorButton(i));
		
	}
	class Handler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			((ElevatorController)getController()).operation(e.getActionCommand());
		}
	}

	//Add checkers index bounds
	public JButton getFloorButton(int index){
		return floorButtons[index];
	}
}

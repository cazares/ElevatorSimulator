package elevatorSimulator.controller;


import elevatorSimulator.model.DoorsModel;
import elevatorSimulator.model.ElevatorModel;
import elevatorSimulator.model.EngineModel.Direction;
import elevatorSimulator.model.FloorListModel;
import elevatorSimulator.simulator.DoorsSimulator;
import elevatorSimulator.view.ElevatorView;
import elevatorSimulator.view.FloorListView;
import elevatorSimulator.view.FloorView;
import elevatorSimulator.view.JFrameView;


public class ElevatorController extends AbstractController{
	
	Thread doors;

	public ElevatorController(FloorListModel floors){	
		setModel(new ElevatorModel(floors)); 
		setView(new ElevatorView((ElevatorModel)getModel(), 
			this));
		((JFrameView)getView()).setVisible(true);
		
		doors = new Thread(new DoorsSimulator((ElevatorModel)getModel()));
		doors.start();
	}
	
	public void operation(String option){
			if(option == ((ElevatorView)getView()).OPEN && ((ElevatorModel)getModel()).getElevDoors().getDoorState() != DoorsModel.OPEN){
				((ElevatorModel)getModel()).getElevDoors().setDoorsState(DoorsModel.OPENING, ((ElevatorModel)getModel()));
				((ElevatorModel)getModel()).getFloorList().getFloor(((ElevatorModel)getModel()).getCurFloor() - 1).getDoors().setDoorsState(DoorsModel.OPENING, 
					((ElevatorModel)getModel()).getFloorList().getFloor(((ElevatorModel)getModel()).getCurFloor() - 1));
			}
			else if (option == ((ElevatorView)getView()).CLOSE && ((ElevatorModel)getModel()).getElevDoors().getDoorState() != DoorsModel.CLOSED){
				((ElevatorModel)getModel()).getElevDoors().setDoorsState(DoorsModel.CLOSING, ((ElevatorModel)getModel()));
				((ElevatorModel)getModel()).getFloorList().getFloor(((ElevatorModel)getModel()).getCurFloor() - 1).getDoors().setDoorsState(DoorsModel.CLOSING, 
					((ElevatorModel)getModel()).getFloorList().getFloor(((ElevatorModel)getModel()).getCurFloor() - 1));
			}
			else if (option != ((ElevatorView)getView()).OPEN && option != ((ElevatorView)getView()).CLOSE){
				((ElevatorView)getView()).getFloorButton(Integer.parseInt(option) - 1).setEnabled(false);
				((ElevatorModel)getModel()).setFloorButton(Integer.parseInt(option) - 1, true);
			}
	}
}
			


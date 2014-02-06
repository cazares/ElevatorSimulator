package elevatorSimulator.controller;


import elevatorSimulator.model.ElevatorModel;
import elevatorSimulator.model.FloorModel;
import elevatorSimulator.view.FloorView;
import elevatorSimulator.view.JFrameView;

/*
 * Handles state changes invoked from FloorView (FloorView displays up and down buttons)
 */
public class FloorController extends AbstractController{
	
	public FloorController(FloorModel model){	
		setModel(model); 
		setView(new FloorView(model, 
			this));
		((JFrameView)getView()).setVisible(true);
	}
	
	public void operation(String option){
		if(option == FloorView.DOWN){
			((ElevatorModel)getModel()).insertDownRequest(((FloorModel)getModel()).getFloorId());
			((FloorModel)getModel()).setDown(true);
			((FloorView)getView()).getDownButton().setEnabled(false);
		}else if(option == FloorView.UP){
			((ElevatorModel)getModel()).insertUpRequest(((FloorModel)getModel()).getFloorId());
			((FloorModel)getModel()).setUp(true);
			((FloorView)getView()).getUpButton().setEnabled(false);
	
		}
	}
}

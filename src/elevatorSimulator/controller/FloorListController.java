package elevatorSimulator.controller;

import elevatorSimulator.model.FloorListModel;
import elevatorSimulator.view.FloorListView;
import elevatorSimulator.view.JFrameView;



/**
 * 
 * @author Jason Herzog
 *Class FloorListController handles communication between the view and controller 
 *of FloorList and creates new ModelViews based on the selection of the dropdown menu in view
 */
public class FloorListController extends AbstractController{
	public FloorListController(int numFloors){	
		setModel(new FloorListModel(numFloors)); //A 5 floor building 
		setView(new FloorListView((FloorListModel)getModel(), 
			this));
		((JFrameView)getView()).setVisible(true);
		
		new ElevatorController((FloorListModel)getModel());
	}

	public void operation(String option, int floorIndex){
		if(option == FloorListView.OPENVIEW){
			//passing floor model at floorIndex in the floorlist from FloorListModel to create a new controller for that model
			new FloorController(((FloorListModel)getModel()).getFloor(floorIndex));
		}
		else if (option == FloorListView.EXIT)
			System.exit(1);
	}
}

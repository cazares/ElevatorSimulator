package elevatorSimulator.model;

import java.util.ArrayList;

import elevatorSimulator.model.EngineModel.Direction;


public class ElevatorModel extends AbstractModel{
	private FloorListModel listOfFloors;
	private boolean floorButtons[];
	private DoorsModel elevatorDoor;
	private boolean closePressed;
	private boolean openPressed;
	private EngineModel engine;
	private double destHeight;
	private double pitStopHeight;
	private double currentHeight;
	private int curFloor;
	private ArrayList upRequests;
	private ArrayList downRequests;
	
	
	//HEY HEY
	// so it updates right 9:50pm
	public ElevatorModel(FloorListModel floors){
		elevatorDoor = new DoorsModel();
		listOfFloors = floors;
		floorButtons = new boolean[listOfFloors.getNumOfFloors()];
		this.engine = new EngineModel(); 
		currentHeight = pitStopHeight = 0.0;
		curFloor = 1;
		upRequests = new ArrayList(listOfFloors.getNumOfFloors());
		downRequests = new ArrayList(listOfFloors.getNumOfFloors());
	}
	
	public void insertUpRequest(int floorNumber){
		upRequests.add(floorNumber);
	}
	
	public void insertDownRequest(int floorNumber){
		downRequests.add(floorNumber);
	}
	

	public DoorsModel getElevDoors(){
		return elevatorDoor;
	}
	
	public void setFloorDest(int floorNumber){
		destHeight = listOfFloors.getFloor(floorNumber).getFloorHeight();
	}
	
	public int getCurFloor(){
		return curFloor;
	}
	
	public FloorListModel getFloorList(){
		return listOfFloors;
	}
	
	public double getCurHeight(){
		return currentHeight;
	}
	
	public int peekUp(){
		int nextFloor = curFloor;
		for(int i = 0; i < upRequests.size(); i++){
			if((Integer) upRequests.get(i) > curFloor && (Integer) upRequests.get(i) < nextFloor){
				pitStopHeight = listOfFloors.getFloor((Integer) upRequests.get(i)).getFloorHeight();
				nextFloor = i + 1;
			}
			
		}
		return nextFloor;
	}
	
	public int peekDown(){
		int nextFloor = curFloor;
		for(int i = 0; i < downRequests.size(); i++){
			if((Integer) downRequests.get(i) < curFloor && (Integer) downRequests.get(i) > nextFloor){
				pitStopHeight = listOfFloors.getFloor((Integer) downRequests.get(i)).getFloorHeight();
				nextFloor = i + 1;
			}
		}
		return nextFloor;
	}
	
	public void nextMove(){
		boolean anyButtonPressed;
		
		
		if(engine.getDirection() == EngineModel.Direction.DOWN)
			peekDown();
		else if(engine.getDirection() == EngineModel.Direction.UP)
			peekUp();
		else if()
			
		
		while(pitStopHeight != currentHeight){
			//engine.setDirection(EngineModel.Direction.)
			engine.moveMax();
			if(engine.getHeight)
		}
			
	}
	
	public EngineModel getEngine() { return this.engine; }
	
	public void setFloorButton(int index, boolean pressed){
		floorButtons[index] = pressed;
		ModelEvent me = new ModelEvent(this, 1, "Elevator button changed", pressed);
		notifyChanged(me);
	}
		
	public boolean getFloorButton(int index){
		return floorButtons[index];
	}

}

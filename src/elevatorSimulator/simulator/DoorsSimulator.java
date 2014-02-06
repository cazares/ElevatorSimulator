package elevatorSimulator.simulator;

import elevatorSimulator.model.DoorsModel;
import elevatorSimulator.model.ElevatorModel;
import elevatorSimulator.model.EngineModel;
import elevatorSimulator.model.EngineModel.Direction;

public class DoorsSimulator implements Runnable{
	
	public static final long TIME_OPEN_CLOSE = 3000;
	public static final long TIME_STAY_OPEN = 5000;
	private long startTime;
	DoorsModel doorsFloor;
	DoorsModel doorsElev;
	ElevatorModel elevator;
	
	
	public DoorsSimulator(ElevatorModel elev){
		this.elevator = elev;
		this.doorsFloor = elev.getFloorList().getFloor(elev.getCurFloor() - 1).getDoors();
		this.doorsElev = elev.getElevDoors();
	}

	@Override
	public void run() {
		startTime = System.currentTimeMillis();
			while(true){
				if(doorsElev.getDoorState() == DoorsModel.OPENING && (System.currentTimeMillis() - startTime) > TIME_OPEN_CLOSE
						&& elevator.getEngine().getDirection() == Direction.STOPPED){
					doorsElev.setDoorsState(DoorsModel.OPEN, elevator);
					doorsFloor.setDoorsState(DoorsModel.OPEN, elevator.getFloorList().getFloor(elevator.getCurFloor() - 1));
					startTime = System.currentTimeMillis();
				}
				else if(doorsElev.getDoorState() == DoorsModel.OPEN && (System.currentTimeMillis() - startTime) > TIME_STAY_OPEN
						&& elevator.getEngine().getDirection() == Direction.STOPPED){
					doorsElev.setDoorsState(DoorsModel.CLOSING, elevator);
					doorsFloor.setDoorsState(DoorsModel.CLOSING, elevator.getFloorList().getFloor(elevator.getCurFloor() - 1));
					startTime = System.currentTimeMillis();
				} 
				else if(doorsElev.getDoorState() == DoorsModel.CLOSING && (System.currentTimeMillis() - startTime) > TIME_OPEN_CLOSE
						&& elevator.getEngine().getDirection() == Direction.STOPPED){ 
					doorsElev.setDoorsState(DoorsModel.CLOSED, elevator);
					doorsFloor.setDoorsState(DoorsModel.CLOSED, elevator.getFloorList().getFloor(elevator.getCurFloor() - 1));
					startTime = System.currentTimeMillis();
				} else if (doorsElev.getDoorState() == DoorsModel.CLOSED){
					startTime = System.currentTimeMillis();
				}
			}
		
	}
}



package elevatorSimulator.model;



/**
 * @author Jason Herzog
 *FloorListModel contains an array of FloorModels and methods to access them.
 */
public class FloorListModel extends AbstractModel{
	
	private FloorModel floorList[];
	public static final int floorDistance = 30;
	private int numOfFloors;
	
	public FloorListModel(int numberOfFloors){
		this.numOfFloors = numberOfFloors;
		floorList = new FloorModel[numOfFloors];
		for(int i = 0; i < numOfFloors; i++)
			floorList[i] = new FloorModel(i + 1, (i + 1) * floorDistance, (i == 0), (i == (numOfFloors - 1))); //id = i + 1 because no floor at 0. There is a floor every 30 ft
	}
	
	/**
	 * @param index Index of the desired floor in the array.
	 * @return returns the floor in the floorList array at floorList[index]
	 */
	public FloorModel getFloor(int index){
		return floorList[index];
	}
	
	/**
	 * @return returns number of floors in the array.
	 */
	public int getNumOfFloors(){
		return numOfFloors;
	}
	
}

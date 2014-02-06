package elevatorSimulator.model;


/**
 * 
 * @author Jason Herzog
 *
 *FloorModel class contains the states of the floor such as Height, Floor number and 
 *whether or not up/down buttons are pressed
 */
public class FloorModel extends AbstractModel{
	private boolean up;
	private boolean down;
	
	private boolean isTop;
	private boolean isBottom;
	
	private int id; //What floor number this floor is
	private double floorHeight; //position in feet. Height from ground
	private DoorsModel doors;
	
	public FloorModel(int floorNumber, double floorPos, boolean isBottom, boolean isTop){
		this.id = floorNumber;
		this.floorHeight = floorPos;
		this.isBottom = isBottom;
		this.isTop = isTop;
		doors = new DoorsModel();
		up = false;
		down = false;
	}
	
	/**
	 * @return returns the state of the up boolean member variable
	 */
	public boolean isUpPressed(){
		return up;
	}
	
	/**
	 * @return returns the state of the down boolean member variable
	 */
	public boolean isDownPressed(){
		return down;
	}
	
	/**
	 * @param isPressed new value for the up boolean member variable to be set to
	 */
	public void setUp(boolean isPressed){
		up = isPressed;
		ModelEvent me = new ModelEvent(this, 1, "Floor button changed",up);
		notifyChanged(me);
	}
	
	/**
	 * @param isPressed new value for the down boolean member variable to be set to
	 */
	public void setDown(boolean isPressed){
		down = isPressed;
		ModelEvent me = new ModelEvent(this, 1, "Floor button changed", down);
		notifyChanged(me);
	}
	
	/**
	 * @return returns the floor's height (ie, coordinate in building)
	 */
	public double getFloorHeight(){
		return floorHeight;
	}
	
	/**
	 * 
	 * @return returns the floor's id
	 */
	public int getFloorId(){
		return id;
	}
	
	/**
	 * 
	 * @return returns reference to FloorModel's instance of DoorsModel
	 */
	public DoorsModel getDoors(){
		return doors;
	}
	
	public boolean isTopFloor(){
		return isTop;
	}
	
	public boolean isBottomFloor(){
		return isBottom;
	}
}

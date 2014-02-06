package elevatorSimulator.model;
/*
 * Contains speed of engine for the elevator (Also direction?) and methods to maniuplate state
 * Does not extend AbstractModel because does not have controller/view?
 */
public class EngineModel extends AbstractModel{
	
	public enum Direction {UP,DOWN,STOPPED};
	public enum State {ACCEL, DECEL, MAINTAIN};
	
	private ElevatorModel elevator;
	private Double curSpeed;	//In ft/s
	private Double maxSpeed;	//In ft/s
	private Double slowSpeed;	//In ft/s
	private Double targetSpeed; //In ft/s
	private Direction curDirection;
	private State curState;
	final Double acceleration = new Double(6.0/12.0); //In ft/s^2
	private double height;
	
	public EngineModel(){
		//this.elevator = elevator;
		this.curSpeed = new Double(0.0);
		this.maxSpeed = new Double(1.0);
		this.slowSpeed = new Double(1./12.);
		this.curDirection = Direction.STOPPED;
		this.curState = State.MAINTAIN;
		this.height = 0.0;
	}
	
	public EngineModel (ElevatorModel elevator, Double maxSpeed, Double slowSpeed){
		this.elevator = elevator;
		this.curSpeed = new Double(0.0);
		this.maxSpeed = maxSpeed;
		this.slowSpeed = slowSpeed;
		this.curDirection = Direction.STOPPED;
		this.curState = State.MAINTAIN;
	}
	
	synchronized public void setDirection(Direction direction){
		this.curDirection = direction;
		ModelEvent me = new ModelEvent(this, 0, "Direction Changed.", this.curDirection);
		notifyChanged(me);
	}
	
	synchronized public void setSpeed(Double speed){
		if(this.curState == State.ACCEL && speed > this.targetSpeed ||
		   this.curState == State.DECEL && speed < this.targetSpeed)
		{
			speed = this.targetSpeed;
		}
		this.curSpeed = speed;
		
		if(this.curSpeed < this.targetSpeed)
		{
			setState(State.ACCEL);
		}else if(this.curSpeed > this.targetSpeed){
			setState(State.DECEL);
		}else{
			if(this.targetSpeed == 0.0)
			{
				this.curSpeed = 0.0;
			}
			setState(State.MAINTAIN);
		}
		
		if(this.curSpeed == 0.0){
			setState(State.MAINTAIN);
			setDirection(Direction.STOPPED);
		}
		
		ModelEvent me = new ModelEvent(this, 1, "Speed Changed", this.curSpeed);
		notifyChanged(me);
	}
	
	synchronized private void setState(State state)
	{
		this.curState = state;
		ModelEvent me = new ModelEvent(this, 2, "State Changed.", this.curState);
		notifyChanged(me);
	}
	
	public Double getSpeed() {return this.curSpeed; }
	
	public Double getAcceleration() { return this.acceleration; }
	
	public State getState() { return this.curState; }
	
	public Direction getDirection() { return this.curDirection; }
	
	public ElevatorModel getElevator() { return this.elevator; }
	
	synchronized public void moveMax(){
		this.targetSpeed = this.maxSpeed;
		setState(State.ACCEL);
		notifyAll();
	}
	
	synchronized public void moveSlow(){
		this.targetSpeed = this.slowSpeed;
		if(this.targetSpeed < this.curSpeed)
		{
			setState(State.DECEL);
		}else if(this.targetSpeed > this.curSpeed){
			setState(State.ACCEL);
		}
		notifyAll();
	}
	
	public void stop(){
		this.targetSpeed = 0.0;
		this.setState(State.DECEL);
	}
	
	public double getHeight(){
		return height;
	}
	
	public void setHeight(double height){
		this.height = height;
	}
	
	synchronized public void waitForCommand()
	   throws InterruptedException
	{
			wait();
	}
	
	//Methods for slowing down, speeding up, stopping and get methods for states

}

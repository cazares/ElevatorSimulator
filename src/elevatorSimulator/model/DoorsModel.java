package elevatorSimulator.model;

/**
 * @author Jason Herzog
 */
public class DoorsModel{
	public String doorState;
	public static final String CLOSED = "Closed";
	public static final String OPEN = "Open";
	public static final String CLOSING = "Closing";
	public static final String OPENING = "Opening";
	
	
	public DoorsModel(){
		doorState = CLOSED;
	}
	
	public synchronized void setDoorsState(String state, Model model){
		this.doorState = state;
		ModelEvent me = new ModelEvent(this, 1, "State Change", state);
		model.notifyChanged(me);
		notifyAll();
	}

	public synchronized String getDoorState(){
		return doorState;
	}
	
	public synchronized void waitWhileAction(){
		System.out.println("In waitWhileAction");
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

package elevatorSimulator.model;
import java.awt.event.ActionEvent;

public class ModelEvent extends ActionEvent {
	Object data;
	public ModelEvent(Object obj, int id, String message, Object data){
		super(obj, id, message);
		this.data = data;
	}
	
	public Object getData(){return this.data;}
}

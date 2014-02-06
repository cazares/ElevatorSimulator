package elevatorSimulator.view;
import elevatorSimulator.model.Model;
import elevatorSimulator.controller.Controller;

public interface View {
	Controller getController();
	void setController(Controller controller);
	Model getModel();
	void setModel(Model model);
}

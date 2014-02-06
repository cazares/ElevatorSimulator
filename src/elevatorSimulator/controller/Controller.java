package elevatorSimulator.controller;
import elevatorSimulator.model.Model;
import elevatorSimulator.view.View;

public interface Controller {
	void setModel(Model model);
	Model getModel();
	View getView();
	void setView(View view);
}

package elevatorSimulator.controller;

import elevatorSimulator.model.EngineModel;
import elevatorSimulator.model.EngineModel.Direction;
import elevatorSimulator.simulator.EngineSimulator;
import elevatorSimulator.view.EngineTestView;
import elevatorSimulator.view.JFrameView;


public class EngineTestController extends AbstractController implements Runnable{

	private Boolean run;
	private Thread thread;
	private EngineSimulator engineSim;
	public Double height = new Double(0.0);
	
	public EngineTestController(){
		setModel(new EngineModel());
		setView(new EngineTestView(((EngineModel)this.getModel()), this));
		((JFrameView)getView()).setVisible(true);
		
		this.engineSim = new EngineSimulator(((EngineModel)this.getModel()), ((EngineTestView)this.getView()).HtBox);
		this.thread = new Thread(this);
		this.thread.setDaemon(true);
		
		engineSim.start();
		
	}
	
	public void operation(String option){
		EngineModel model = ((EngineModel)this.getModel());
		if(option == "UP"){
			model.setDirection(Direction.UP);
		}else if(option == "DOWN"){
			model.setDirection(Direction.DOWN);
		}else if(option == "MOVEMAX"){
			model.moveMax();
		}else if(option == "MOVESLOW"){
			model.moveSlow();
		}else if(option == "STOP"){
			model.stop();
		}
	}
	
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new EngineTestController();
	}
}


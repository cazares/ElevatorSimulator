package elevatorSimulator.simulator;

import javax.swing.JTextField;

import elevatorSimulator.model.EngineModel;
import elevatorSimulator.model.EngineModel.Direction;

public class EngineSimulator implements Runnable{

	EngineModel model;
	JTextField tracker;
	Thread thread = new Thread(this);
	Double height = new Double(0.0);
	
	public EngineSimulator(EngineModel model, JTextField tracker){
		this.model = model;
		this.tracker = tracker;
		this.thread.setDaemon(true);
		
	}


	public void run()
	{
		this.height = 0.0;
		long beginTime;
		long endTime=0; 
		double duration;
		try{
			while(true)
			{
				if(model.getDirection() == Direction.STOPPED)
				{
					model.waitForCommand();
					endTime=0;
				}else{
					beginTime = System.currentTimeMillis();
					if(endTime == 0)
						endTime = beginTime-1;
					duration = ((double)beginTime-(double)endTime)/1000.0;
					
					//ACCEL DELEC
					switch(model.getState())
					{
					case ACCEL:
					{
						model.setSpeed(model.getSpeed() + model.getAcceleration()*duration);
						break;
					}
					case DECEL:
					{
						model.setSpeed(model.getSpeed() - model.getAcceleration()*duration);
						break;
					}
					case MAINTAIN:
					{
						break;
					}
					default:
					{
						System.out.println("Unexpected State!");
					}
					}
					
					switch(model.getDirection())
					{
					case UP:
					{
						model.setHeight(model.getHeight() + model.getSpeed()*duration);
						tracker.setText(this.height.toString());
						break;
					}
					case DOWN:
					{
						model.setHeight(model.getHeight() - model.getSpeed()*duration);
						this.tracker.setText(this.height.toString());
						break;
					}
					default:
					{
						System.out.println("Unexpected Direction!");
					}
					}
					endTime = beginTime;
				}
			}
		}catch(InterruptedException e){
			
		}
	}
	
	public void start(){
		this.thread.start();
	}
		
}

package elevatorSimulator.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import elevatorSimulator.controller.EngineTestController;
import elevatorSimulator.model.EngineModel;
import elevatorSimulator.model.ModelEvent;

/*
 * displays interface to open views of a specified floor from a drop down menu
 */

public class EngineTestView extends JFrameView{
	public JLabel DirLbl;
	public JLabel SteLbl;
	public JTextField SpBox;
	public JTextField HtBox;	

	public EngineTestView(EngineModel model, EngineTestController controller) {
		super(model, controller);
		
		//setting up string of floor names for the dropdown menu
		JPanel buttonPanel = new JPanel();
		
		this.DirLbl = new JLabel(model.getDirection().toString());
		buttonPanel.add(this.DirLbl);
		
		this.SteLbl = new JLabel(model.getState().toString());
		buttonPanel.add(this.SteLbl);
		
		JLabel SpLbl = new JLabel("Current Speed");
		buttonPanel.add(SpLbl);
		SpBox = new JTextField();
		SpBox.setText(model.getSpeed().toString());
		buttonPanel.add(SpBox);
		
		JLabel htLbl = new JLabel("Height");
		buttonPanel.add(htLbl);
		HtBox = new JTextField();
		HtBox.setText(controller.height.toString());
		buttonPanel.add(HtBox);
		
		Handler handler = new Handler();
	    setTitle("Engine Tester");
	    setSize(300, 150);
	    
	    JButton jButtonUp = new JButton("UP");
	    jButtonUp.addActionListener(handler);
	    buttonPanel.add(jButtonUp);
	    
	    JButton jButtonDn = new JButton("DOWN");
	    jButtonDn.addActionListener(handler);
	    buttonPanel.add(jButtonDn);
	    
	    JButton jButtonMvMx = new JButton("MOVEMAX");
	    jButtonMvMx.addActionListener(handler);
	    buttonPanel.add(jButtonMvMx);
	    
	    JButton jButtonMvSL = new JButton("MOVESLOW");
	    jButtonMvSL.addActionListener(handler);
	    buttonPanel.add(jButtonMvSL);
	    
	    JButton jButtonStp = new JButton("STOP");
	    jButtonStp.addActionListener(handler);
	    buttonPanel.add(jButtonStp);
	    
	    buttonPanel.setLayout(new GridLayout(11, 1, 1, 1));
	    this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	    
	    pack();
	    
		
	}

	@Override
	public void modelChanged(ModelEvent event) {
		switch(event.getID())
		{
		case 0:
		{
			this.DirLbl.setText(event.getData().toString());
			break;
		}
		case 1:
		{
			this.SpBox.setText(event.getData().toString());
			break;
		}
		case 2:
		{
			this.SteLbl.setText(event.getData().toString());
			break;
		}
	}
		
	}
	class Handler implements ActionListener{
		public void actionPerformed(ActionEvent e){
	       ((EngineTestController)getController()).operation(e.getActionCommand());
		}
	}
	

}

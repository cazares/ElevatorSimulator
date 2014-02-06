package elevatorSimulator.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import elevatorSimulator.controller.FloorListController;
import elevatorSimulator.model.FloorListModel;
import elevatorSimulator.model.ModelEvent;

/*
 * displays interface to open views of a specified floor from a drop down menu
 */

public class FloorListView extends JFrameView{
	
	public static final String EXIT= "Exit";
	public static final String OPENVIEW = "Open Floor View";
	
	private String[] floorNames;
	private JComboBox floorSelect;

	public FloorListView(FloorListModel model, FloorListController controller) {
		super(model, controller);
		
		//setting up string of floor names for the dropdown menu
		floorNames = new String[((FloorListModel)getModel()).getNumOfFloors()];
		for(int i = 0; i < ((FloorListModel)getModel()).getNumOfFloors(); i++)
			floorNames[i] = new String("Floor " + ((FloorListModel)getModel()).getFloor(i).getFloorId());
		floorSelect = new JComboBox(floorNames);
		
		Handler handler = new Handler();
		this.getContentPane().add(floorSelect, BorderLayout.NORTH);
	    setTitle("Floor Selection");
	    setSize(300, 150);
	    
	    JPanel buttonPanel = new JPanel();
	    
	    JButton jButtonOpen = new JButton(OPENVIEW);
	    jButtonOpen.addActionListener(handler);
	    buttonPanel.add(jButtonOpen);
	    
	    JButton jButtonExit = new JButton(EXIT);
	    jButtonExit.addActionListener(handler);
	    buttonPanel.add(jButtonExit);
	    
	    buttonPanel.setLayout(new GridLayout(1, 2, 1, 1));
	    this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	    
	    //pack();
	    
		
	}

	@Override
	public void modelChanged(ModelEvent event) {
		// TODO Auto-generated method stub
		
	}
	class Handler implements ActionListener{
		public void actionPerformed(ActionEvent e){
	       ((FloorListController)getController()).operation(e.getActionCommand(), floorSelect.getSelectedIndex());
		}
	}
	

}

package graphos.commands;

import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;
import graphos.model.DiagramSelectionModel;
import graphos.model.elements.DiagramDevice;
import graphos.model.workspace.DiagramModel;

import java.util.ArrayList;
import java.util.Iterator;

public class AddScaleElementCommand extends AbstractCommand {

	DiagramModel model;
	DiagramDevice element = null;
	DiagramSelectionModel selection = new DiagramSelectionModel();
	ArrayList<DiagramDevice> elements = new ArrayList<DiagramDevice>();
	private double oldScale;
	private double newScale;
	
	public AddScaleElementCommand(DiagramModel model, DiagramSelectionModel selection, double newScale, double oldScale) {
		this.model = model;
		for(int i=0; i< selection.getSelectionList().size(); i++)
			elements.add((DiagramDevice) selection.getSelectionList().get(i));
		this.selection = selection;
		
		this.newScale=newScale;
		this.oldScale=oldScale;
	}

	@SuppressWarnings("unchecked")
	public void doCommand() {		
		// TODO Auto-generated method stub
		
		selection.removeAllFromSelectionList();
		//selection.addToSelectionList((ArrayList<DiagramDevice>) elements.clone());
		Iterator<DiagramDevice> it = elements.iterator();
		while (it.hasNext()) {
			DiagramDevice element = it.next();
			element.setScale(newScale);	
		}
		((DiagramGUI)(MainFrame.getInstance().getRightPane().getSelectedFrame())).updatePerformed(null);		
	
	}

	@SuppressWarnings("unchecked")
	public void undoCommand() {		
		// TODO Auto-generated method stub
		
		selection.removeAllFromSelectionList();
		//selection.addToSelectionList((ArrayList<DiagramDevice>) elements.clone());
		Iterator<DiagramDevice> it = elements.iterator();
		while (it.hasNext()) {
			DiagramDevice element = it.next();
			element.setScale(oldScale);	
		}
		((DiagramGUI)(MainFrame.getInstance().getRightPane().getSelectedFrame())).updatePerformed(null);
	}
	


}

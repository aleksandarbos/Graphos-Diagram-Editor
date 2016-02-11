package graphos.commands;

import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;
import graphos.model.DiagramSelectionModel;
import graphos.model.elements.DiagramDevice;
import graphos.model.elements.DiagramElement;
import graphos.model.workspace.DiagramModel;

import java.util.ArrayList;
import java.util.Iterator;

public class AddRotateRightCommand extends AbstractCommand {

	private DiagramModel model;
	
	private DiagramSelectionModel selection = new DiagramSelectionModel();
	
	private ArrayList<DiagramElement> elements = new ArrayList<DiagramElement>();
	
	public AddRotateRightCommand(DiagramModel model, DiagramSelectionModel selection) {
		this.model = model;
		for(int i=0;i<selection.getSelectionList().size();i++)
			elements.add((DiagramDevice) selection.getSelectionList().get(i));
		this.selection = selection;
	}

	@SuppressWarnings("unchecked")
	public void doCommand() {
		// TODO Auto-generated method stub
		selection.removeAllFromSelectionList();
		selection.addToSelectionList((ArrayList<DiagramElement>) elements.clone());
		Iterator<DiagramElement> it = elements.iterator();
		while (it.hasNext()) {
			DiagramDevice element = (DiagramDevice) it.next();
			element.setRotation(element.getRotation()+Math.PI/2);
		}
		((DiagramGUI)(MainFrame.getInstance().getRightPane().getSelectedFrame())).updatePerformed(null);		
	}

	@SuppressWarnings("unchecked")
	public void undoCommand() {
		// TODO Auto-generated method stub
		selection.removeAllFromSelectionList();
		selection.addToSelectionList((ArrayList<DiagramElement>) elements.clone());
		Iterator<DiagramElement> it = elements.iterator();
		while (it.hasNext()) {
			DiagramDevice element = (DiagramDevice) it.next();
			element.setRotation(element.getRotation()-Math.PI/2);
		}
		//selection.clearSelectionList();
		((DiagramGUI)(MainFrame.getInstance().getRightPane().getSelectedFrame())).updatePerformed(null);		
	}

	
	


	
	
}

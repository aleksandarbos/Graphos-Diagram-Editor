package graphos.commands;

import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;
import graphos.model.DiagramSelectionModel;
import graphos.model.elements.DiagramDevice;
import graphos.model.elements.DiagramElement;
import graphos.model.workspace.DiagramModel;

import java.util.ArrayList;
import java.util.Iterator;

public class AddDeleteElementCommand extends AbstractCommand {

	private DiagramModel model;
	private DiagramSelectionModel selection = new DiagramSelectionModel();
	private ArrayList<DiagramElement> elements = new ArrayList<DiagramElement>();
	
	public AddDeleteElementCommand(DiagramModel model, DiagramSelectionModel selection) {
		this.model = model;
		for(int i=0;i < selection.getSelectionList().size();i++)
			elements.add(selection.getSelectionList().get(i));		
		this.selection = selection;
	}
	
	@SuppressWarnings("unchecked")
	public void doCommand() {
		// TODO Auto-generated method stub
		
		selection.removeAllFromSelectionList();
		//selection.addToSelectionList((ArrayList<DiagramElement>) elements.clone());
		Iterator<DiagramElement> it = elements.iterator();
		while (it.hasNext()) {
			DiagramElement element = it.next();
			model.getDiagramDevices().remove(element);
		}
		it = elements.iterator();
		while (it.hasNext()) {
			DiagramElement element = it.next();
			selection.removeFromSelectionList(element);
		}
	}

	@SuppressWarnings("unchecked")
	public void undoCommand() {
		// TODO Auto-generated method stub
		
		selection.removeAllFromSelectionList();
		selection.addToSelectionList((ArrayList<DiagramElement>) elements.clone());
		Iterator<DiagramElement> it = elements.iterator();
		while (it.hasNext()) {
			DiagramElement element = it.next();
			model.getDiagramDevices().add((DiagramDevice) element);
			//selection.addToSelectionList(element);
		}((DiagramGUI)(MainFrame.getInstance().getRightPane().getSelectedFrame())).updatePerformed(null);
	}

	


}

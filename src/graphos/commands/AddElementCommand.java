package graphos.commands;

import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;
import graphos.model.DiagramSelectionModel;
import graphos.model.elements.CircleElement;
import graphos.model.elements.DiagramDevice;
import graphos.model.elements.PolygonElement;
import graphos.model.elements.RectangleElement;
import graphos.model.elements.TriangleElement;
import graphos.model.workspace.DiagramModel;
import graphos.model.workspace.Project;

import java.awt.Point;

public class AddElementCommand extends AbstractCommand {
	private DiagramModel model;
	private Point lastPosition;
	private DiagramDevice element = null;
	private DiagramSelectionModel selection;
	private int elementType;
	
	public AddElementCommand(DiagramModel model, DiagramSelectionModel selection,Point lastPosition,int elementType) {
		this.model = model;
		this.lastPosition = lastPosition;
		this.selection = selection;
		this.elementType=elementType;
	}
	
	public void doCommand() {
		if (element==null) {
			if (elementType==DiagramGUI.TRIANGLE) {
				element=TriangleElement.createDefault(lastPosition,model.getElementsCount());
			} else if (elementType==DiagramGUI.RECTANGLE) {
				element=RectangleElement.createDefault(lastPosition,model.getElementsCount());
			} else if (elementType==DiagramGUI.HEXAGON) {
				element=PolygonElement.createDefault(lastPosition,model.getElementsCount());
			} else if (elementType==DiagramGUI.CIRCLE) {
				element=CircleElement.createDefault(lastPosition,model.getElementsCount());
			}
		}
		model.getDiagramDevices().add(element);
		selection.removeAllFromSelectionList();
		//selection.addToSelectionList(element);
		((Project)MainFrame.getInstance().getTreePane().getCurrentProject()).updatePerformed(null);
	}

	public void undoCommand() {
		selection.removeFromSelectionList(element);
		model.getDiagramDevices().remove(element);
	}

}

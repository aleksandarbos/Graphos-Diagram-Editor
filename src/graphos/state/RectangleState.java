package graphos.state;

import graphos.commands.AddElementCommand;
import graphos.gui.workspace.DiagramGUI;
import graphos.model.elements.DiagramDevice;
import graphos.model.elements.RectangleElement;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class RectangleState extends State {
	private DiagramGUI diagram;
	
	public RectangleState(DiagramGUI diagram) {
		this.diagram = diagram;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		Point position = e.getPoint();
		diagram.transformToUserSpace(position);

		if (e.getButton()==MouseEvent.BUTTON1){
			if (diagram.getDiagram().getModel().getDeviceAtPosition(position)==-1){
				diagram.getCommandManager().addCommand(
						new AddElementCommand(diagram.getDiagram().getModel(),
											  diagram.getDiagram().getDiagramSelectionModel(),
											  position,
											  DiagramGUI.RECTANGLE));
			}
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("RECTANGLE STATE RELEASED");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

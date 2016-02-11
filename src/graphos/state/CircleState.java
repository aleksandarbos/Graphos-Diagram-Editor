package graphos.state;

import graphos.commands.AddElementCommand;
import graphos.gui.workspace.DiagramGUI;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class CircleState extends State {
	
	private DiagramGUI diagram;
	
	public CircleState(DiagramGUI diagram) {
		this.diagram = diagram;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Point position = e.getPoint();
		diagram.transformToUserSpace(position);
		
		if (e.getButton()==MouseEvent.BUTTON1){
			if (diagram.getDiagram().getModel().getDeviceAtPosition(position)==-1){
				diagram.getCommandManager().addCommand(
						new AddElementCommand(diagram.getDiagram().getModel(),
											  diagram.getDiagram().getDiagramSelectionModel(),
											  position,
											  DiagramGUI.CIRCLE));
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
		System.out.println("CIRCLE STATE RELEASE");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

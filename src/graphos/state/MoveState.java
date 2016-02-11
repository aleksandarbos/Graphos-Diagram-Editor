package graphos.state;

import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;
import graphos.model.elements.DiagramDevice;
import graphos.model.elements.DiagramElement;
import graphos.model.workspace.Project;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;

public class MoveState extends State {

	private DiagramGUI diagram;
	private double x = 0;
	private double y = 0;

	public MoveState(DiagramGUI diagGUI) {
		this.diagram = diagGUI;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("MOVE STATE DRAAAAG");
		
		diagram.getFramework().setCursor(
				Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		Point2D lastPosition = e.getPoint();
		diagram.transformToUserSpace(lastPosition);
		// odredjujemo x pomeraj
		double xx = diagram.getLastPosition().getX() - lastPosition.getX();
		// odredjujemo y pomeraj
		double yy = diagram.getLastPosition().getY() - lastPosition.getY();
		Iterator<DiagramElement> it = diagram.getDiagram()
				.getDiagramSelectionModel().getSelectionListIterator();
		while (it.hasNext()) {
			//System.out.println("trazim selektovane jajare1");
			DiagramElement element = it.next();
			if (element instanceof DiagramDevice) {
				DiagramDevice device = (DiagramDevice) element;
				//System.out.println("KOLUMBIJAAAAAAA");
				Point2D newPosition = (Point2D) device.getPosition().clone();
				newPosition.setLocation(newPosition.getX() - xx,
						newPosition.getY() - yy);
				// postavljamo novu poziciju elementa
				device.setPosition(newPosition);
				//System.out.println("Eleasdasdment: " + device.getName() + "poz: " + device.getPosition());
				// ”pomeren” je element, sada “pomeranje” svih ulaza i izlaza:

			}
			diagram.setLastPosition(lastPosition);
			diagram.updatePerformed(null);
			if(diagram.getDiagram().getDiagramSelectionModel().getSelectionList().size() == 1)
				MainFrame.getInstance().getStatusBar().setElementLabel(element);
			else
				MainFrame.getInstance().getStatusBar().setElementLabel("");
			((Project)MainFrame.getInstance().getTreePane().getCurrentProject()).updatePerformed(null);

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		/*
		 * diagram.getStateManager().addCommand( new
		 * MoveDeviceCommand(diagram.getDiagram().getModel(), med
		 * .getDiagram().getSelectionModel(), x, y));
		 */
		diagram.getFramework().setCursor(
				Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		x = 0;
		y = 0;
		Point2D lastPosition = e.getPoint();
		diagram.transformToUserSpace(lastPosition);
		diagram.setLastPosition(lastPosition);
		diagram.startSelectState();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("wee");
	}

}

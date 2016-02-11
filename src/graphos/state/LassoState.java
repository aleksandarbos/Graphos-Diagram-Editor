package graphos.state;

import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;
import graphos.model.elements.DiagramElement;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class LassoState extends State {

	Rectangle2D rect = new Rectangle2D.Double();

	private DiagramGUI diagram;

	public LassoState(DiagramGUI diagram) {
		this.diagram = diagram;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		// prvi deo metode formira pravougaonik koji predstavlja laso
		Point2D mousePos = e.getPoint();
		diagram.transformToUserSpace(mousePos);
		if (!e.isControlDown()) {
			diagram.getDiagram().getDiagramSelectionModel()
					.removeAllFromSelectionList();
			//System.out.println("REMOVEEEEEE LASOOOOOOOOOOOO");
		}
		double width = mousePos.getX() - diagram.getLastPosition().getX();
		double height = mousePos.getY() - diagram.getLastPosition().getY();
		if ((width < 0) && (height < 0)) {
			rect.setRect(mousePos.getX(), mousePos.getY(), Math.abs(width),
					Math.abs(height));
		} else if ((width < 0) && (height >= 0)) {
			rect.setRect(mousePos.getX(), diagram.getLastPosition().getY(),
					Math.abs(width), Math.abs(height));
		} else if ((width > 0) && (height < 0)) {
			rect.setRect(diagram.getLastPosition().getX(), mousePos.getY(),
					Math.abs(width), Math.abs(height));
		} else {
			rect.setRect(diagram.getLastPosition().getX(), diagram
					.getLastPosition().getY(), Math.abs(width), Math
					.abs(height));
		}
		diagram.setSelectionRectangle(rect);
		// poziv metode koja selektuje elemente koji se nalaze u formiranom
		// pravougaoniku
		diagram.getDiagram()
				.getDiagramSelectionModel()
				.selectElements(rect,
						diagram.getDiagram().getModel().getDiagramDevices());
		diagram.repaint();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
			
		diagram.setSelectionRectangle(new Rectangle2D.Double(0,0,0,0));
		diagram.repaint();
		diagram.startSelectState();
		
		try {
			DiagramElement element = diagram.getDiagram().getDiagramSelectionModel().getSelectionList().get(0);
			
			if((diagram.getDiagram().getDiagramSelectionModel().getSelectionList().size() == 1) && element != null) {
				//ako je 1 selektovan, podesi status bar
				MainFrame.getInstance().getStatusBar().setElementLabel(element);
			} else {
				//resetuj status bar
				MainFrame.getInstance().getStatusBar().setElementLabel("");
			}
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println("Nije nista zahvatio lasom");
		}
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

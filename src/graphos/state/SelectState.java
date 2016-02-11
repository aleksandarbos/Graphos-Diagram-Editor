package graphos.state;

import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;
import graphos.gui.workspace.DiagramGUI.Handle;
import graphos.model.elements.DiagramElement;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class SelectState extends State {
	private DiagramGUI diagram;

	// indeks elementa koji je selektovan
	private int elementInMotion = -1;
	private Handle handleInMotion = null;

	public SelectState(DiagramGUI diagram) {
		this.diagram = diagram;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Point position = e.getPoint();

		diagram.transformToUserSpace(position);
		// System.out.println("SELECT STATE: " + diagram.getP);
		// -------------- PAINT PART -----------------
		if (diagram.getDiagram().getModel().getDeviceAtPosition(position) != -1) { // ako je nabo nesto

			if (e.getClickCount() == 2 && !e.isConsumed()) {
				e.consume();
				int devicePosition = diagram.getDiagram().getModel()
						.getDeviceAtPosition(position);
				DiagramElement diagElement = diagram.getDiagram().getModel()
						.getDiagramDevices().get(devicePosition);

				MainFrame.getInstance().showElementWin(diagElement);

			}
		}
		
		

		// -------------- SELECTION PART ---------------

		// if ctrl btn is not hold
		/*if (!e.isControlDown()) {
			diagram.getDiagram().getDiagramSelectionModel()
					.removeAllFromSelectionList();
			System.out.println("DESELEKCIJA ELEMENATA!");
		}*/

		if (e.getButton()==MouseEvent.BUTTON1){
			handleInMotion = diagram.getDeviceAndHandleForPoint(position);
			if(handleInMotion == null){
				if(!e.isControlDown()){
					if(diagram.getDiagram().getDiagramSelectionModel().getSelectionListSize() < 2)
						diagram.getDiagram().getDiagramSelectionModel().removeAllFromSelectionList();
					//System.out.println("SELECT REMOVEEEEEEEEEE");
				}		
				elementInMotion = diagram.getDiagram().getModel().getDeviceAtPosition(position);
				if(elementInMotion != -1){
					//pogodjen je element, ukoliko je selektovan treba ga ukloniti iz liste,
					//ako nije treba ga dodati u listu
					DiagramElement element=diagram.getDiagram().getModel().getElementAt(elementInMotion);
					
					if (diagram.getDiagram().getDiagramSelectionModel().isElementSelected(element)){
						
						//diagram.getDiagram().getDiagramSelectionModel().removeFromSelectionList(element);
					}else{
						diagram.getDiagram().getDiagramSelectionModel().addToSelectionList(element);
						MainFrame.getInstance().getStatusBar().setElementLabel(element);
					}	
					
				}else{
					//nije pogodjen nijedan element
					diagram.getDiagram().getDiagramSelectionModel().removeAllFromSelectionList();
					MainFrame.getInstance().getStatusBar().setElementLabel("");
				}
			}else{
			}
			
		}
		
		//System.out.println("SELECT STATE CLICK");

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		Point position = e.getPoint();
		diagram.transformToUserSpace(position);
		handleInMotion = diagram.getDeviceAndHandleForPoint(position);
		if (handleInMotion != null) {
			diagram.startResizeState();
			return;
		} else {
			// nije selektovan handle, da li je selektovan element
			elementInMotion = diagram.getDiagram().getModel().getDeviceAtPosition(position);
			if (elementInMotion != -1) {
				// selektovan je element ili grupa elemenata
				// preci u MoveState
					//System.out.println("PRELAZIM UUUUU MOVEEEEEEEEEEE STATEEEEEEEEEEEE");
					diagram.startMoveState();
				return;
			} else
				// nije pogodjen element, prelazimo u Laso stanje
				diagram.startLassoState();
		}
		
		/*//if(e.getButton() == MouseEvent.BUTTON1){
			
			//vrši se povlačenje sa levim tasterom miša
			//provera da li je selektovan handle elementa, tada se radi resize elementa
			Point position = e.getPoint();
			diagram.transformToUserSpace(position);
			handleInMotion = diagram.getDeviceAndHandleForPoint(position);
			if(handleInMotion != null){
				diagram.startResizeState();
			}else{
				//nije selektovan handle, da li je selektovan element
				elementInMotion = diagram.getDiagram().getModel().getDeviceAtPosition(position);
				if(elementInMotion != -1){
					//selektovan je element ili grupa elemenata
					//preci u MoveState
					diagram.startMoveState();
					return;
					
				}else	//nije pogodjen element, prelazimo u Laso stanje
					diagram.startLassoState();	
				}
		//}*/

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//diagram.getDiagram().getDiagramSelectionModel().removeAllFromSelectionList();
		System.out.println("SELECT STATE RELEASE");

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point2D position = e.getPoint();
		diagram.transformToUserSpace(position);
		diagram.setMouseCursor(position);
		//MainFrame.getInstance().getStatusBar().setPosition(e.getPoint().toString());
		// MainFrame.getInstance().getRightPane()..setPosition(e.getPoint().toString());
	}

}

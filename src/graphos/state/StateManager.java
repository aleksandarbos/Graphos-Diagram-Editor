package graphos.state;

import java.awt.Cursor;
import java.io.Serializable;

import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;

public class StateManager implements Serializable {

	private State currentState;
	
	private SelectState selectState;
	private RectangleState rectangleState;
	//private LineState lineState;
	private CircleState circleState;
	private TriangleState triangleState;
	private PolygonState polygonState;
	//private EllipseState ellipseState;
	private LassoState lassoState;
	private MoveState moveState;
	private ResizeState resizeState;
	
	public StateManager(DiagramGUI diagram) {
		rectangleState = new RectangleState(diagram);
		//lineState = new LineState(diagram);
		circleState = new CircleState(diagram);
		triangleState = new TriangleState(diagram);
		polygonState = new PolygonState(diagram);
		//ellipseState = new EllipseState(diagram);
		selectState = new SelectState(diagram);
		lassoState = new LassoState(diagram);
		moveState = new MoveState(diagram);
		rectangleState = new RectangleState(diagram);
		resizeState = new ResizeState(diagram);
		
		currentState = selectState;		//initial state
		//MainFrame.getInstance().getPalette().
		
	}


	public void setSelectState() {
		this.currentState = selectState;
		MainFrame.getInstance().getStatusBar().setStateLabel("SELECT");
		((DiagramGUI)MainFrame.getInstance().getRightPane().getSelectedFrame()).getFramework().setCursor(
				Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	public void setRectangleState() {
		this.currentState = rectangleState;
		MainFrame.getInstance().getStatusBar().setStateLabel("ADD ELEMENT");
	}

	/*public void setLineState() {
		this.currentState = lineState;
		MainFrame.getInstance().getStatusBar().setStateLabel("ADD ELEMENT");
	}*/

	public void setCircleState() {
		this.currentState = circleState;
		MainFrame.getInstance().getStatusBar().setStateLabel("ADD ELEMENT");
	}

	public void setTriangleState() {
		this.currentState = triangleState;
		MainFrame.getInstance().getStatusBar().setStateLabel("ADD ELEMENT");
	}

	public void setPolygonState() {
		this.currentState = polygonState;
		MainFrame.getInstance().getStatusBar().setStateLabel("ADD ELEMENT");
	}

	/*public void setEllipseState() {
		this.currentState = ellipseState;
		MainFrame.getInstance().getStatusBar().setStateLabel("ADD ELEMENT");
	}
	
	public void setLinkState() {
		this.currentState = lineState;
		MainFrame.getInstance().getStatusBar().setStateLabel("ADD ELEMENT");
	}*/

	public State getCurrentState() {
		return currentState;
	}


	public void setLassoState() {
		this.currentState = lassoState;
		MainFrame.getInstance().getStatusBar().setStateLabel("LASSO");
		System.out.println("LASSO STATE");
	}
	
	
	public void setMoveState() {
		this.currentState = moveState;
		MainFrame.getInstance().getStatusBar().setStateLabel("MOVE");
	}

	public void setResizeState() {
		this.currentState = resizeState;
		MainFrame.getInstance().getStatusBar().setStateLabel("RESIZE");
	}

	
}

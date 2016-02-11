package graphos.gui.workspace;

import graphos.commands.CommandManager;
import graphos.controller.DesktopController;
import graphos.event.UpdateEvent;
import graphos.event.UpdateListener;
import graphos.gui.MainFrame;
import graphos.gui.painters.ElementPainter;
import graphos.model.elements.DiagramDevice;
import graphos.model.elements.DiagramElement;
import graphos.model.workspace.Diagram;
import graphos.state.StateManager;

import java.awt.Adjustable;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

@SuppressWarnings("serial")
public class DiagramGUI extends JInternalFrame implements UpdateListener, AdjustmentListener {

	public static int openFrameCount = 0;

	public enum Handle {
		North, South, East, West, NorthEast, NorthWest, SouthEast, SouthWest
	}
	
	public static final int TRIANGLE=1;
	public static final int RECTANGLE=2;
	public static final int HEXAGON=3;
	public static final int CIRCLE=4;
	
	private CommandManager commandManager=new CommandManager();
	

	static final int handleSize = 10;
	static final int xOffset = 30, yOffset = 30;

	private Diagram diagram;

	private Framework framework = new Framework(); // radna povrsina za Diagarm
	private JScrollBar sBarVertical;
	private JScrollBar sBarHorizontal;

	private StateManager stateManager = new StateManager(this);

	private Point2D lastPosition;
	private Rectangle2D selectionRectangle = new Rectangle2D.Double();

	// podrska za affineTransform
	double translateX = 0;
	double translateY = 0;
	double scaling = 1;

	final static double translateFactor = 10;
	final static double scalingFactor = 1.2;

	private int hScrollValue = 1500;
	private int vScrollValue = 1500;

	private AffineTransform transformation = new AffineTransform();

	public DiagramGUI() {

		super("", true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable
		++openFrameCount;
		this.setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
		this.setIconifiable(true);
		this.setClosable(true);

		this.setSize(400, 400);

		framework = new Framework();
		
		
		sBarHorizontal=new JScrollBar(JScrollBar.HORIZONTAL, hScrollValue, 20, 0, 3000);
		sBarVertical=new JScrollBar(JScrollBar.VERTICAL, vScrollValue, 20, 0, 3000);

		sBarHorizontal.addAdjustmentListener(this);
		sBarVertical.addAdjustmentListener(this);
		
		this.add(sBarHorizontal,BorderLayout.SOUTH);
		this.add(sBarVertical,BorderLayout.EAST);

		this.add(framework, BorderLayout.CENTER);
		
		this.setVisible(true);
		
		MainFrame.getInstance().getDiagramsGUI().add(this);
		this.addInternalFrameListener(new DesktopController());
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// mouse listeners
		
		DiagramController mouseListener = new DiagramController();
		framework.addMouseListener(mouseListener);
		framework.addMouseMotionListener(mouseListener);
		framework.addMouseWheelListener(mouseListener);

	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
		this.setName(diagram.getName());
		this.diagram.getModel().addUpdateListener(this);
		this.diagram.getDiagramSelectionModel().addUpdateListener(this);
		setTitle(diagram.getName());
	}

	public Diagram getDiagram() {
		return diagram;
	}

	public boolean equals(DiagramGUI diag) {
		return (this.diagram.getName() == diag.diagram.getName());
	}

	public DiagramGUI getDiagramPart() {
		return this;
	}

	public void resetFrameCounter() {
		openFrameCount = 0;
	}

	int x = 0;

	// interna klasa, povrsina za crtanje
	public class Framework extends JPanel {
		protected void paintComponent(Graphics graph) {
			super.paintComponent(graph);

			setBackground(Color.WHITE);

			setSize(hScrollValue, vScrollValue); // potrebno za
													// affineTansformacije

			Graphics2D g2D = (Graphics2D) graph;
			/*g2D.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.9f));*/

			// smooth prikaz
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			
			g2D.transform(transformation);

			// prolaz kroz listu objekata untar jednog Framework-a
			Iterator<DiagramDevice> it = diagram.getModel().getDeviceIterator();
			int i = 0;
			while (it.hasNext()) {
				DiagramDevice diagDevice = (DiagramDevice) it.next();
				ElementPainter elementPainter = diagDevice.getElementPainter();
				elementPainter.paint(g2D, diagDevice);
			}

			// paintovanje selekcije - - OKVIR SA HANDLE-OVIMA
			paintSelectionHandles(g2D);

			// SELEKCIONI PRAVOUGAONIK

			// iscrtavanje pravougaonika za lasso
			g2D.setColor(Color.BLACK);
			g2D.setStroke(new BasicStroke((float) 1, BasicStroke.CAP_SQUARE,
					BasicStroke.JOIN_BEVEL, 1.5f, new float[] { (float) 3,
							(float) 6 }, 0));
			g2D.draw(selectionRectangle);
			//System.out.println("Izvršena paintComponent metoda view-a");
		}
	}

	public void updatePerformed(UpdateEvent e) {
		repaint();
		
		//System.out.println("Update!");
	}

	// -------------- STATE MANAGER ---------------------

	public void startSelectState() {
		System.out.println("Started SELECT STATE!");
		stateManager.setSelectState();
		
		// stateManager.setSelectState();
		// diagram.getDiagramSelectionModel().removeAllFromSelectionList();
	}

	public void startCircleState() {
		System.out.println("Started CIRCLE STATE!");
		stateManager.setCircleState();
		diagram.getDiagramSelectionModel().removeAllFromSelectionList();
	}
/*
	public void startLineState() {
		System.out.println("Started LINE STATE!");
		stateManager.setLineState();
		diagram.getDiagramSelectionModel().removeAllFromSelectionList();
	}*/

	public void startTriangleState() {
		System.out.println("Started TRIANGLE STATE!");
		stateManager.setTriangleState();
		diagram.getDiagramSelectionModel().removeAllFromSelectionList();
	}

	public void startPolygonState() {
		System.out.println("Started POLYGON STATE!");
		stateManager.setPolygonState();
		diagram.getDiagramSelectionModel().removeAllFromSelectionList();
	}

	public void startRectangleState() {
		System.out.println("Started RECTANGLE STATE!");
		stateManager.setRectangleState();
		diagram.getDiagramSelectionModel().removeAllFromSelectionList();
	}

	public void startMoveState() {
		System.out.println("Started RECTANGLE STATE!");
		stateManager.setMoveState();
		//diagram.getDiagramSelectionModel().removeAllFromSelectionList();
	}
	
	/*public void startEllipseState() {
		System.out.println("Started ELLIPSE STATE!");
		stateManager.setEllipseState();
		diagram.getDiagramSelectionModel().removeAllFromSelectionList();
	}*/

	public void startLassoState() {
		System.out.println("Started LASSO STATE!");
		stateManager.setLassoState();
		// diagram.getDiagramSelectionModel().removeAllFromSelectionList();
	}

	public void startResizeState() {
		System.out.println("Started RESIZE STATE!");
		stateManager.setResizeState();
		// diagram.getDiagramSelectionModel().removeAllFromSelectionList();
	}
	
	public StateManager getStateManager() {
		return stateManager;
	}

	// ------------------ STATE - DIAGARM_GUI ACTION HANDLER ---------------

	private class DiagramController extends MouseAdapter implements
			MouseMotionListener {

		public void mousePressed(MouseEvent e) {
			lastPosition=e.getPoint();
			transformToUserSpace(lastPosition);
			getStateManager().getCurrentState().mousePressed(e);
			// System.out.println("Podesen last position: " +
			// " , lastPosition: " + lastPosition.getX() + " " +
			// lastPosition.getY());
		}

		public void mouseReleased(MouseEvent e) {
			getStateManager().getCurrentState().mouseReleased(e);
			// System.out.println("Vrela JAJA RELEASED!");
		}

		public void mouseDragged(MouseEvent e) {
			getStateManager().getCurrentState().mouseDragged(e);
		}

		public void mouseMoved(MouseEvent e) {
			//System.out.println(getStateManager().getCurrentState());
			getStateManager().getCurrentState().mouseMoved(e);
			// System.out.println("MouseMoved!");
		}

	 	public void mouseWheelMoved(MouseWheelEvent e) {
			//System.out.println("WHEEL");
			if ((e.getModifiers() & MouseWheelEvent.CTRL_MASK) != 0) {
				// Ako pritisnut Ctrl
				// Radimo zoom u tački (diskretni zoom)
				// Prvo je potrebno da odredimo novo skaliranje
				double newScaling = scaling;
				if (((MouseWheelEvent) e).getWheelRotation() > 0)
					newScaling *= (double) ((MouseWheelEvent) e)
							.getWheelRotation() * scalingFactor;
				else
					newScaling /= -(double) ((MouseWheelEvent) e)
							.getWheelRotation() * scalingFactor;
				// Zatim je potrebno da skaliranje održimo u intervalu [0.2, 5]
				if (newScaling < 0.2)
					newScaling = 0.2;
				if (newScaling > 5)
					newScaling = 5;

				Point2D oldPosition = e.getPoint();
				transformToUserSpace(oldPosition);
				scaling = newScaling;
				setupTransformation();
				Point2D newPosition = e.getPoint();
				transformToUserSpace(newPosition);
				translateX += newPosition.getX() - oldPosition.getX();
				translateY += newPosition.getY() - oldPosition.getY();
				setupTransformation();
			} else if ((e.getModifiers() & MouseWheelEvent.SHIFT_MASK) != 0) {
				// Ako je pritisnut Shift, transliranje po X osi
				translateX += (double) ((MouseWheelEvent) e).getWheelRotation()
						* translateFactor / scaling;
			} else {
				// u ostalim slučajevima vršimo skrolovanje po Y osi
				translateY += (double) ((MouseWheelEvent) e).getWheelRotation()
						* translateFactor / scaling;
			}
			// ova metoda postavlja novo skaliranje i transliranje u objekat
			// transformacije
			setupTransformation();
			repaint();
		}

	}

	// ------------------ SELECTION PAINTER ----------------

	private void paintSelectionHandles(Graphics2D g2) {
		
		Iterator<DiagramElement> it = diagram.getDiagramSelectionModel()
				.getSelectionListIterator();
		while (it.hasNext()) {
			DiagramElement element = it.next();
			if (element instanceof DiagramDevice) {
				DiagramDevice device = (DiagramDevice) element;
				// Iscrtavanje pravougaonika sa isprekidanom linijom
				g2.setStroke(new BasicStroke((float) 1.2, BasicStroke.CAP_SQUARE,
						BasicStroke.JOIN_BEVEL, 1f, new float[] { 3f, 6f }, 0));
				g2.setPaint(Color.BLACK);
				
				g2.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, 0.7f));
				
				//diagram.getAllowsChildren()
				
				
				/*g2.drawRect((int) device.getPosition().getX(), (int) device
						.getPosition().getY(), (int) device.getSize()
						.getWidth(), (int) device.getSize().getHeight());*/

				g2.drawRect((int)device.getRotatedPosition().getX(),
						(int)device.getRotatedPosition().getY(),
						(int)device.getRotatedSize().getWidth(),
						(int)device.getRotatedSize().getHeight());
				
				
				/*System.out.println(element.getName() + " , velicina: "
						+ device.getSize().height + ", "
						+ device.getSize().width);*/
				for (Handle e : Handle.values()) {
					paintSelectionHandle(g2, getHandlePoint(device.getRotatedPosition(),device.getRotatedSize(),e));
				}
				// Iscrtavanje hendlova
				/*for (Handle e : Handle.values()) {
					paintSelectionHandle(
							g2,
							getHandlePoint(device.getPosition(),
									device.getSize(), e));
				}*/

				//g2.rotate(240);
			}

		}
	}

	private void paintSelectionHandle(Graphics2D g2, Point2D position) {
		double size = handleSize;
		g2.fill(new Rectangle2D.Double(position.getX() - size / 2, position
				.getY() - size / 2, size, size));
	}

	// ---------- END SELECTION PAINTER END ---------------

	public Handle getDeviceAndHandleForPoint(Point2D point) {
		DiagramElement element;

		Iterator<DiagramElement> it = diagram.getDiagramSelectionModel()
				.getSelectionListIterator();
		while (it.hasNext()) {
			element = it.next();
			return getHandleForPoint(element, point);
		}
		return null;
	}

	private Handle getHandleForPoint(DiagramElement element, Point2D point) {
		for (Handle h : Handle.values()) {
			if (isPointInHandle(element, point, h)) {
				return h;
			}
		}
		return null;
	}

	private boolean isPointInHandle(DiagramElement element, Point2D point,
			Handle handle) {
		if (element instanceof DiagramDevice) {
			DiagramDevice device = (DiagramDevice) element;
			
			Point2D handleCenter = getHandlePoint(device.getRotatedPosition(), device.getRotatedSize(), handle);
			
			return ((Math.abs(point.getX() - handleCenter.getX()) <= (double) handleSize / 2) && (Math
					.abs(point.getY() - handleCenter.getY()) <= (double) handleSize / 2));
		} else
			return false;
	}

	private Point2D getHandlePoint(Point2D topLeft, Dimension2D size,
			Handle handlePosition) {

		double x = 0, y = 0;

		// Odredjivanje y koordinate

		// Ako su gornji hendlovi
		if (handlePosition == Handle.NorthWest
				|| handlePosition == Handle.North
				|| handlePosition == Handle.NorthEast) {
			y = topLeft.getY();
		}
		// Ako su centralni po y osi
		if (handlePosition == Handle.East || handlePosition == Handle.West) {
			y = topLeft.getY() + size.getHeight() / 2;
		}
		// Ako su donji
		if (handlePosition == Handle.SouthWest
				|| handlePosition == Handle.South
				|| handlePosition == Handle.SouthEast) {
			y = topLeft.getY() + size.getHeight();
		}

		// Određivanje x koordinate

		// Ako su levi
		if (handlePosition == Handle.NorthWest || handlePosition == Handle.West
				|| handlePosition == Handle.SouthWest) {
			x = topLeft.getX();
		}
		// ako su centralni po x osi
		if (handlePosition == Handle.North || handlePosition == Handle.South) {
			x = topLeft.getX() + size.getWidth() / 2;
		}
		// ako su desni
		if (handlePosition == Handle.NorthEast || handlePosition == Handle.East
				|| handlePosition == Handle.SouthEast) {
			x = topLeft.getX() + size.getWidth();
		}

		return new Point2D.Double(x, y);

	}

	public Point2D getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Point2D lastPosition) {
		this.lastPosition = lastPosition;
	}

	public Rectangle2D getSelectionRectangle() {
		return selectionRectangle;
	}

	public void setSelectionRectangle(Rectangle2D selectionRectangle) {
		this.selectionRectangle = selectionRectangle;
	}

	public void setMouseCursor(Point2D point) {

		Handle handle = getDeviceAndHandleForPoint(point);

		if (handle != null) {
			Cursor cursor = null;

			switch (handle) {
			case North:
				cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
				break;
			case South:
				cursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
				break;
			case East:
				cursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
				break;
			case West:
				cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
				break;
			case SouthEast:
				cursor = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
				break;
			case NorthWest:
				cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
				break;
			case SouthWest:
				cursor = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
				break;
			case NorthEast:
				cursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
				break;
			}
			framework.setCursor(cursor);
		} else
			framework.setCursor(Cursor.getDefaultCursor());
	}

	public void transformToUserSpace(Point2D deviceSpace) {
		try {
			transformation.inverseTransform(deviceSpace, deviceSpace);
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setupTransformation() {
		// reset 1-1
		transformation.setToIdentity();
		// Zumiranje
		transformation.scale(scaling, scaling);
		// Skrolovanje
		transformation.translate(translateX, translateY);

	}

	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getAdjustable().getOrientation()==Adjustable.HORIZONTAL){
			//translateX
			
			translateX -=  arg0.getValue()-hScrollValue;
			hScrollValue  = arg0.getValue();
			setupTransformation();
			repaint();
		}
		else {
			//<translate Y
			translateY -=  arg0.getValue()-vScrollValue;
			vScrollValue  = arg0.getValue();
			setupTransformation();
			repaint();
		}
	}

	
	public void zoomIn(){
		double newScaling = scaling;
		
		newScaling *= scalingFactor;
		
		if(newScaling < 0.2)
			newScaling = 0.2;
		if(newScaling > 5)
			newScaling = 5;

		
		Point2D oldPosition = new Point2D.Double(getWidth()/2,getHeight()/2); //sredina ekrana
		transformToUserSpace(oldPosition);
		
		scaling = newScaling;
		setupTransformation();
		
		Point2D newPosition = new Point2D.Double(getWidth()/2,getHeight()/2); //sredina ekrana
		transformToUserSpace(newPosition);
		
		translateX +=  newPosition.getX() - oldPosition.getX();
		translateY += newPosition.getY() - oldPosition.getY();
		
		sBarVertical.setVisibleAmount((int) (20/scaling));
		sBarHorizontal.setVisibleAmount((int) (20/scaling));
		
		setupTransformation();		
	}
	
	public void zoomOut(){
		double newScaling = scaling;
		
		newScaling /= scalingFactor;
		
		if(newScaling < 0.2)
			newScaling = 0.2;
		if(newScaling > 5)
			newScaling = 5;
		
		
		Point2D oldPosition = new Point2D.Double(getWidth()/2,getHeight()/2); //sredina ekrana logicka
		transformToUserSpace(oldPosition);
		
		scaling = newScaling;
		setupTransformation();
		
		Point2D newPosition = new Point2D.Double(getWidth()/2,getHeight()/2); //sredina ekrana logicka
		transformToUserSpace(newPosition);
		
		translateX +=  newPosition.getX() - oldPosition.getX();
		translateY += newPosition.getY() - oldPosition.getY();
		
		sBarVertical.setVisibleAmount((int) (20/scaling));
		sBarHorizontal.setVisibleAmount((int) (20/scaling));
		
		setupTransformation();		
	}

	public Framework getFramework() {
		return framework;
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}	
	
	
	
}
	


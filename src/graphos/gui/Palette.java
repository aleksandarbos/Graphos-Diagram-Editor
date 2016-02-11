package graphos.gui;

import graphos.abs.actions.BtnCircle;
import graphos.abs.actions.BtnCursor;
import graphos.abs.actions.BtnDrawEclipse;
import graphos.abs.actions.BtnDrawLine;
import graphos.abs.actions.BtnPolygon;
import graphos.abs.actions.BtnRectangle;
import graphos.abs.actions.BtnTriangle;

import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import com.sun.corba.se.impl.interceptors.PICurrent;

@SuppressWarnings("serial")
public class Palette extends JToolBar {
	
	private JToggleButton piCursor = new JToggleButton(new BtnCursor("", "src/images/ico/cursor.png", 0, "control alt P "));
	private JToggleButton piLine = new JToggleButton(new BtnDrawLine("", "src/images/ico/line.png", 0, "control alt L "));
	private JToggleButton piRectangle = new JToggleButton(new BtnRectangle("", "src/images/ico/rectangle.png", 0, "control alt R "));
	private JToggleButton piTriangle = new JToggleButton(new BtnTriangle("", "src/images/ico/triangle.png", 0, "control alt T "));
	private JToggleButton piCircle = new JToggleButton(new BtnCircle("", "src/images/ico/circle.png", 0, "control alt C "));
	private JToggleButton piPolygon = new JToggleButton(new BtnPolygon("", "src/images/ico/polygon.png", 0, "control alt P "));
	private JToggleButton piEclipse = new JToggleButton(new BtnDrawEclipse("", "src/images/ico/eclipse.png", 0, "control alt E "));
	private ButtonGroup btnGroup = new ButtonGroup();
	
	public Palette() {
		super("Palette", SwingConstants.VERTICAL);
		initPaletteGUI();
		
		
	}

	public void initPaletteGUI() {
		
		
		
		btnGroup.add(piCursor);
		//btnGroup.add(piLine);
		btnGroup.add(piRectangle);
		btnGroup.add(piTriangle);
		btnGroup.add(piCircle);
		btnGroup.add(piPolygon);
		//btnGroup.add(piEclipse);
		
		piCursor.setSelected(true);
		
		piCursor.setToolTipText("Cursor");
		piRectangle.setToolTipText("Rectangle");
		piLine.setToolTipText("Line");
		piTriangle.setToolTipText("Triangle");
		piCircle.setToolTipText("Circle");
		piPolygon.setToolTipText("Hexagon");
		piEclipse.setToolTipText("Eclipse");
		
		add(piCursor);
		//add(piLine);
		add(piRectangle);
		add(piTriangle);
		add(piCircle);
		add(piPolygon);
		//add(piEclipse);
		
		//ogranicenje
		setFloatable(true);
		
		setPreferredSize(new Dimension(40, 200));
		
		
	}
	
	public void setCursorSelected() {
		btnGroup.clearSelection();
		piCursor.setSelected(true);
		System.out.println("Ociscena selekcija, CURSOR!");
	}
	
}

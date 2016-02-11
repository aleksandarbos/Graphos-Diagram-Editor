package graphos.gui;

import graphos.model.elements.CircleElement;
import graphos.model.elements.DiagramDevice;
import graphos.model.elements.DiagramElement;
import graphos.model.elements.PolygonElement;
import graphos.model.elements.RectangleElement;
import graphos.model.elements.TriangleElement;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class StatusBar extends JPanel {
	private JLabel label1 = new JLabel(" ");
	private JLabel label2 = new JLabel("SELECT");
	private JLabel label3 = new JLabel("");
	private JLabel label4 = new JLabel("");
	private JLabel label5 = new JLabel("");
	
	public StatusBar() {
		super();
		initPanelGUI();
	}

	private void initPanelGUI() {
		setLayout(new GridLayout(1,5));
		
		//JLabel label1 = new JLabel("Label 1");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setBorder(getBorder());
		label1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		//JLabel label2 = new JLabel("Label 2");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		//JLabel label3 = new JLabel("Label 3");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		//JLabel label4 = new JLabel("Label 4");
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		//JLabel label5 = new JLabel("Label 5");
		label5.setHorizontalAlignment(SwingConstants.CENTER);
		label5.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		add(label2);
		add(label1);
		add(label3);
		add(label4);
		add(label5);
		
		
	}
	
	public void setElementLabel(DiagramElement diagEl) {
		if(diagEl instanceof RectangleElement)
			label1.setText("RECTANGLE");
		else if(diagEl instanceof CircleElement) {
			label1.setText("CIRCLE");
		} else if(diagEl instanceof TriangleElement) {
			label1.setText("TRIANGLE");
		} else if(diagEl instanceof PolygonElement) {
			label1.setText("POLYGON");
		} else {
			label1.setText("");
		}
		
		label3.setText(diagEl.getName());
		label4.setText("x: " + ((DiagramDevice)diagEl).getPosition().x + " , y: " + ((DiagramDevice)diagEl).getPosition().y);
		label5.setText("width: " + ((DiagramDevice) diagEl).getSize().width + " , height: " + ((DiagramDevice) diagEl).getSize().height);
	}
	
	public void setElementLabel(String name) {
		label1.setText(name);
		//label2.setText(name);
		label3.setText(name);
		label4.setText(name);
		label5.setText(name);
	}

	public void setStateLabel(String stateName) {
		label2.setText(stateName);
	}

	public void setElementNameLabel(String name) {
		// TODO Auto-generated method stub
		label3.setText(name);
	}

	public void setElementPositionLabel(Point position) {
		// TODO Auto-generated method stub
		label4.setText("x: " + position.x + " , y: " + position.y);
	}
	public void setElementPositionLabel(String name) {
		// TODO Auto-generated method stub
		label4.setText(name);
	}	
	
	public void setElementDimensionLabel(Dimension dim) {
		label5.setText("width: " + dim.width + " , height: " + dim.height);
	}
	
	public void setElementDimensionLabel(String name) {
		label5.setText(name);
	}
}

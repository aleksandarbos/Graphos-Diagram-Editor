package graphos.gui.painters;

import graphos.model.elements.DiagramElement;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;


public abstract class ElementPainter implements Serializable {

	protected Shape shape;
	
	protected DiagramElement element;
	
	public ElementPainter(DiagramElement element) {
		this.element=element;
	}
	
	
	public abstract void paint(Graphics2D g2D, DiagramElement diagEl);

	public abstract boolean isElementAt(Point position);

	public Shape getShape() {
		return shape;
	}
	
}

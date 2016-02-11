package graphos.gui.painters;

import java.awt.geom.Ellipse2D;

import graphos.model.elements.CircleElement;
import graphos.model.elements.DiagramElement;

public class CirclePainter extends DevicePainter {

	public CirclePainter(DiagramElement diagEl) {
		super(diagEl);
		CircleElement circleElement = (CircleElement) diagEl;
		
		shape = new Ellipse2D.Float(0, 0, circleElement.getSize().width, circleElement.getSize().height);
		
	}

}

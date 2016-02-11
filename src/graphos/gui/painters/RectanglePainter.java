package graphos.gui.painters;

import graphos.model.elements.DiagramElement;
import graphos.model.elements.RectangleElement;

import java.awt.geom.Rectangle2D;

public class RectanglePainter extends DevicePainter {

	public RectanglePainter(DiagramElement diagEl) {
		super(diagEl);
		RectangleElement rectangle = (RectangleElement) diagEl;
		
		shape = new Rectangle2D.Float(
				0,
				0,
				(int)rectangle.getSize().getWidth(),
				(int)rectangle.getSize().getHeight());
		
		/*shape = new Rectangle2D.Float((int)device.getRotatedPosition().getX(),
				(int)device.getRotatedPosition().getY(),
				(int)device.getRotatedSize().getWidth(),
				(int)device.getRotatedSize().getHeight());*/
	}

}

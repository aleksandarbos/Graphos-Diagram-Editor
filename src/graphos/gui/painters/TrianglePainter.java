package graphos.gui.painters;

import java.awt.geom.GeneralPath;

import graphos.model.elements.DiagramElement;
import graphos.model.elements.TriangleElement;

public class TrianglePainter extends DevicePainter {

	public TrianglePainter(DiagramElement diagEl) {
		super(diagEl);
		
		TriangleElement triangleElement = (TriangleElement) diagEl;
		
		GeneralPath trianglePath = new GeneralPath();
		trianglePath.moveTo(0, 0 +  triangleElement.getSize().height);
		trianglePath.lineTo(0 + triangleElement.getSize().width/2, 0 );
		trianglePath.lineTo(0 +  triangleElement.getSize().width, 0 +  triangleElement.getSize().height);
		trianglePath.closePath();
		shape = trianglePath;
	}

}

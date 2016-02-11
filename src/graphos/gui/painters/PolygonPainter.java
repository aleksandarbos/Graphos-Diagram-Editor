package graphos.gui.painters;

import graphos.model.elements.DiagramElement;
import graphos.model.elements.PolygonElement;

import java.awt.geom.GeneralPath;

public class PolygonPainter extends DevicePainter {
	
	public PolygonPainter(DiagramElement diagEl) {
		super(diagEl);


		PolygonElement triangleElement = (PolygonElement) diagEl;
		
		GeneralPath polygonPath = new GeneralPath();
		
		polygonPath.moveTo(12.5, 0);
	    polygonPath.lineTo(37.5, 0);
	    polygonPath.lineTo(52.5, 25);
	    polygonPath.lineTo(37.5, 50);
	    polygonPath.lineTo(12.5, 50);
	    polygonPath.lineTo(-2.5, 25);
	    polygonPath.lineTo(12.5, 0);
	    polygonPath.moveTo(17.5, 5);
	    polygonPath.lineTo(25, 10);
	    polygonPath.lineTo(17.5, 10);
	    polygonPath.lineTo(17.5, 5);
	    polygonPath.closePath();
		
		shape = polygonPath;
		
		/*int xPoly[] = {150, 250, 325, 375, 450, 275, 100};
        int yPoly[] = {150, 100, 125, 225, 250, 375, 300};*/
	}

}

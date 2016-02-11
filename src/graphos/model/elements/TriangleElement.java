package graphos.model.elements;

import graphos.gui.painters.TrianglePainter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Point2D;

public class TriangleElement extends DiagramDevice {

	public TriangleElement(Stroke stroke, Paint paint, Dimension size, Point position, Color strokeColor) {
		super(stroke, paint, size, position, strokeColor);
		elementPainter = new TrianglePainter(this);
	}

	public static DiagramDevice createDefault(Point2D pos, int elemNo){
		Point2D position = (Point2D) pos.clone();
		
        Paint fill = Color.decode("#00EC8E");
        
        TriangleElement triangleElement = new TriangleElement(
	    		                      	  		  new BasicStroke(2f),
	    		                      			  fill,
	    		                      			  new Dimension(50,50), 
	    		                      			  (Point)position,
	    		                      			  Color.BLACK);
        triangleElement.setName("Triangle" + elemNo);
		return triangleElement;
	}

}

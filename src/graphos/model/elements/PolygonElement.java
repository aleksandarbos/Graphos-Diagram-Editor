package graphos.model.elements;

import graphos.gui.painters.PolygonPainter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Point2D;

public class PolygonElement extends DiagramDevice {

	public PolygonElement(Stroke stroke, Paint paint, Dimension size, Point position, Color strokeColor) {
		super(stroke, paint, size, position, strokeColor);
		elementPainter = new PolygonPainter(this);
	}

	
	public static DiagramDevice createDefault(Point2D pos, int elemNo){
		Point2D position = (Point2D) pos.clone();
		
        Paint fill = Color.decode("#FF4B31");
        PolygonElement polygonElement = new PolygonElement(
	    		                      	  		  new BasicStroke(2f),
	    		                      			  fill,
	    		                      			  new Dimension(50, 50), 
	    		                      			  (Point)position,
	    		                      			  Color.BLACK);
        polygonElement.setName("Hexagon" + elemNo);
		return polygonElement;
	}	
	
}

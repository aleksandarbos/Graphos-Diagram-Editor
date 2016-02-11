package graphos.model.elements;

import graphos.gui.painters.RectanglePainter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Point2D;

public class RectangleElement extends DiagramDevice {
	
	public RectangleElement(Stroke stroke, Paint paint, Dimension size, Point position, Color strokeColor) {
		
		super(stroke, paint, size, position, strokeColor);
		elementPainter = new RectanglePainter(this);
	}

	
	public static DiagramDevice createDefault(Point2D pos, int elemNo){
		Point2D position = (Point2D) pos.clone();
		
        Paint fill = Color.decode("#FFBADC");
        RectangleElement rectangleElement = new RectangleElement(
	    		                      	  		  new BasicStroke(2f),
	    		                      			  fill,
	    		                      			  new Dimension(80,40), 
	    		                      			  (Point)position,
	    		                      			  Color.BLACK);
        rectangleElement.setName("Rectangle" + elemNo);
		return rectangleElement;
	}	

	
}

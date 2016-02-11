package graphos.model.elements;

import graphos.gui.painters.CirclePainter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Point2D;

public class CircleElement extends DiagramDevice {

	public CircleElement(Stroke stroke, Paint paint, Dimension size, Point position, Color strokeColor) {
		super(stroke, paint, size, position, strokeColor);
		elementPainter = new CirclePainter(this);
	}
	
	public static DiagramDevice createDefault(Point2D pos, int elemNo){
		Point2D position = (Point2D) pos.clone();
        Paint fill = Color.decode("#00C3C6");
        CircleElement circleElement = new CircleElement(
	    		                      	  		  new BasicStroke(2f),
	    		                      			  fill,
	    		                      			  new Dimension(70,70), 
	    		                      			  (Point)position,
	    		                      			  Color.BLACK);
        circleElement.setName("Circle" + elemNo);
		return circleElement;
	}
	
}

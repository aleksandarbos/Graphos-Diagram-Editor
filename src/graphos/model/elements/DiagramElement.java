package graphos.model.elements;

import graphos.gui.painters.ElementPainter;
import graphos.serialization.SerializableStrokeAdapter;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.io.Serializable;

public abstract class DiagramElement implements Serializable {


	protected Paint paint;
	protected SerializableStrokeAdapter stroke;
	protected Color strokeColor;
	
	protected String name;
	protected String description;
	
    protected ElementPainter elementPainter;
    
    //private Point position;
	
	public DiagramElement(Stroke stroke, Paint paint, Color strokeColor){
		setStroke(stroke);
		this.paint = paint;
		this.strokeColor = strokeColor;
	}

    
	public ElementPainter getElementPainter() {
		return elementPainter;
	}

	public Paint getPaint() {
		return paint;
	}
	public void setPaint(Paint paint) {
		this.paint = paint;
	}
	public Stroke getStroke() {
		return stroke;
	}
	
	public void setStroke(Stroke stroke) {
		this.stroke = new SerializableStrokeAdapter(stroke);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setElementPainter(ElementPainter elementPainter) {
		this.elementPainter = elementPainter;
	}

	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}
	
	
	
}

package graphos.model.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Point2D;

public abstract class DiagramDevice extends DiagramElement {
	
	protected Dimension size;
	protected Point position;
	protected double scale;
	protected double rotation;	
	
	protected transient Point rotated_point;
	

	public DiagramDevice(Stroke stroke, Paint paint, Dimension size, Point position, Color strokeColor) {
		super(stroke, paint, strokeColor);
		this.size = size;
		this.position = position;
		this.scale = 1;			//inicijalno stanje 1-1 razmera
		this.rotation = 0;		//0' rotacija
		
		System.out.println("Element: " + size + "poz: " + position);
		// TODO Auto-generated constructor stub
	}
	
	public Dimension getSize() {
		Dimension tempSize = new Dimension();
		tempSize.setSize(size.getWidth()*getScale(),
		size.getHeight()*getScale());
		return tempSize;
	}
	
	public void setSize(Dimension size) {
		this.size = size;
	}
	public Point getPosition() {
		return position;
	}
	public void setPosition(Point position) {
		this.position = position;
	}
	public void setPosition(Point2D position) {
		this.position.x = (int)position.getX();
		this.position.y = (int)position.getY();
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {		//resetuj rotaciju
		this.rotation=rotation;
		if (this.rotation==2*Math.PI || this.rotation==-2*Math.PI)
			this.rotation=0;
	}
	
	public Dimension getInitSize(){
		return size;
	}
	
	public Point getRotatedPosition() {
		if (getRotation()==0 || getRotation()==Math.PI || getRotation()==-Math.PI)		//ako je vodoravno
			return position;
		else {
			Point tempPosition = new Point((int)(getInitPosition().getX()+getSize().getWidth()/2-getSize().getHeight()/2),
				     (int)(getInitPosition().getY()+getSize().getHeight()/2-getSize().getWidth()/2));
			return tempPosition;
		}
	}
	
	public Dimension getRotatedSize() {
		if (getRotation()==0 || getRotation()==Math.PI || getRotation()==-Math.PI)
			return getSize();
		else {
			Dimension tempDimension = new Dimension();
			tempDimension.setSize((int)(getSize().getHeight()),(int)(getSize().getWidth()));
			return tempDimension;			
		}
	}
	
	public Point getInitPosition() {
		return position;
	}
	
}

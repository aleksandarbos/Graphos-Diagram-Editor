package graphos.gui.painters;

import graphos.model.elements.CircleElement;
import graphos.model.elements.DiagramDevice;
import graphos.model.elements.DiagramElement;
import graphos.model.elements.PolygonElement;
import graphos.model.elements.RectangleElement;
import graphos.model.elements.TriangleElement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class DevicePainter extends ElementPainter {
	
	
	public DevicePainter(DiagramElement device) {
		super(device);
	}

	@Override
	public void paint(Graphics2D g2D, DiagramElement diagEl) {
		//iscrtaj ono sto poseduje svaki od elemenata diagrama
		
		AffineTransform oldTranform = g2D.getTransform();
		//uzimamo device kome painter pripada
		DiagramDevice device = (DiagramDevice) element;

		g2D.translate(device.getPosition().getX(), device.getPosition().getY());
		g2D.rotate(device.getRotation(), device.getSize().getWidth()/2, device.getSize().getHeight()/2);
		g2D.scale(device.getScale(), device.getScale());
		// ------------------------------- DEVICE **FILL & CAPTION** SECTON -------------------------------------
		
		g2D.setPaint(element.getStrokeColor());
		g2D.setStroke(element.getStroke());
		g2D.draw(getShape());

		g2D.setPaint(element.getPaint());
		g2D.fill(getShape());	
		g2D.setPaint(Color.BLACK);
		g2D.setFont(new Font("arial",Font.ITALIC,13));
		g2D.drawString(element.getName(), 0, 0);		//bice translirano svakako
		g2D.setTransform(oldTranform);
		
		// ------------------------------- DEVICE **FILL & CAPTION** SECTON -------------------------------------

	}

	@Override
	public boolean isElementAt( Point pos){										//ocrtava rectangle oko objekta i proverava da li taj pravougaonik sadrzi shape
		DiagramDevice device = (DiagramDevice) element;
		Rectangle2D rect=new Rectangle2D.Double();
		rect.setRect(device.getRotatedPosition().getX(), device.getRotatedPosition().getY(),
				device.getRotatedSize().getWidth(), device.getRotatedSize().getHeight());
		return rect.contains(pos);
	}


	
}

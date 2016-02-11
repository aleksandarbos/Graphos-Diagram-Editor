package graphos.state;

import java.awt.event.MouseEvent;
import java.io.Serializable;

public abstract class State implements Serializable {

	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseDragged(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	public abstract void mouseMoved(MouseEvent e);

}
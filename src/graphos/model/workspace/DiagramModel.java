package graphos.model.workspace;


import graphos.event.UpdateEvent;
import graphos.event.UpdateListener;
import graphos.gui.painters.DevicePainter;
import graphos.model.elements.DiagramDevice;
import graphos.model.elements.DiagramElement;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.event.EventListenerList;
import javax.swing.text.Position;

public class DiagramModel implements Serializable {
	
	private static int count=0;
	private String name;
	
	@SuppressWarnings("unchecked")
	protected ArrayList<DiagramDevice> diagramDevices = new ArrayList<DiagramDevice>();
	
	transient  EventListenerList listenerList = new EventListenerList();
	transient UpdateEvent updateEvent = null;
	 
	 
	private Object readResolve(){
		listenerList = new EventListenerList();	
		return this;
	}	

	
	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		DiagramModel.count = count;
		
	}

	public int getDeviceAtPosition(Point point) {
		for(int i=getElementsCount()-1;i>=0;i--){
			DiagramElement device = diagramDevices.get(i);
			if(device.getElementPainter().isElementAt(point)){
				System.out.println("Element broj: " + i);
				return i;
			}
		}
		return -1;
	}	
	
	public DiagramElement getElementAt(int i){
		return diagramDevices.get(i);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<DiagramDevice> getDiagramDevices() {
		return diagramDevices;
	}
	
	public String toString() {
		return name;
	}	
	public int getElementsCount(){
		return diagramDevices.size();
	}
	
	public void removeDiagramDevice(DiagramElement diagEl) {
		diagramDevices.remove(diagEl);
	}
	
	public Iterator<DiagramDevice> getDeviceIterator() {
		return diagramDevices.iterator();
	}

	public void addDiagramDevices(DiagramDevice diagEl) {
		diagramDevices.add(diagEl);
		fireUpdatePerformed();								//update grafiku!
		
	}
	
	public void addUpdateListener(UpdateListener l) {
		listenerList.add(UpdateListener.class, l);
	}
	
	public void removeFooListener(UpdateListener l) {
		listenerList.remove(UpdateListener.class, l);
	}
	
	protected void fireUpdatePerformed() {
		Object[] listeners = listenerList.getListenerList();
		for (int i = listeners.length-2; i>=0; i-=2) {
			if (listeners[i]==UpdateListener.class) {
				if (updateEvent == null)
					updateEvent = new UpdateEvent(this);
				((UpdateListener)listeners[i+1]).updatePerformed(updateEvent);
			}
		}
	}
}

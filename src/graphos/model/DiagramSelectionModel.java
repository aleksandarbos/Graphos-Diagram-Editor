package graphos.model;

import graphos.event.UpdateEvent;
import graphos.event.UpdateListener;
import graphos.model.elements.DiagramDevice;
import graphos.model.elements.DiagramElement;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultSingleSelectionModel;
import javax.swing.event.EventListenerList;

public class DiagramSelectionModel extends DefaultSingleSelectionModel {

	private ArrayList<DiagramElement> selectedElements = new ArrayList<DiagramElement>();
	
	transient  EventListenerList listenerList = new EventListenerList();
	//transient
	UpdateEvent updateEvent = null;
	
	private void rebuildListenerList() {
		//init nesnimljen objekat za projekte
		listenerList = new EventListenerList();
	}
	
	public void addToSelectionList(DiagramElement diagEl) {
		selectedElements.add(diagEl);
		System.out.println(diagEl.getName() + " DODAT U SELEKCIJUUUUUUUUUUUUUU");
		fireUpdatePerformed();
	}
	
	public void addToSelectionList(ArrayList<DiagramElement> selectedElementsList) {
		selectedElements.addAll(selectedElementsList);
		fireUpdatePerformed();
	}
	
	public void removeFromSelectionList(DiagramElement diagEl) {
		selectedElements.remove(diagEl);
		System.out.println(diagEl.getName() + " OBRISAN IZ SELEKCIJEEEEEEEEE");
		fireUpdatePerformed();
	}
	
	public void removeAllFromSelectionList() {
		System.out.println("BRISEM SVE IZ LISTEEEEE SELEKCIJA");
		selectedElements.clear();
		fireUpdatePerformed();
	}
	
	public int getSelectionListSize() {
		return selectedElements.size();
	}
	
	public DiagramElement getSelectedDiagramAt(int index) {
		return selectedElements.get(index);
	}
	
	public int getIndexByObject(DiagramElement diagEl) {
		return selectedElements.indexOf(diagEl);
	}
	
	
	public ArrayList<DiagramElement> getSelectionList() {
		return selectedElements;
	}
	
	public Iterator<DiagramElement> getSelectionListIterator() {
		return selectedElements.iterator();
	}
	public boolean isElementSelected(DiagramElement diagEl) {
		return selectedElements.contains(diagEl);
	}
	
	// provera intersekcije novokreiranog pravougaonika, i liste elemenata dijagrama
	
	public void selectElements(Rectangle2D rec,ArrayList<DiagramDevice> elements){
		Iterator<DiagramDevice> it = elements.iterator();
		while(it.hasNext()){
			DiagramElement element=it.next();
			if (element instanceof DiagramDevice){
				DiagramDevice device=(DiagramDevice)element;
				if(rec.intersects(device.getRotatedPosition().getX(), device.getRotatedPosition().getY(),
						device.getRotatedSize().getWidth(), device.getRotatedSize().getHeight())){
					if(!isElementSelected(device))
						addToSelectionList(device);
						//System.out.println("dodat el");
				}
			}
		}
	}
	
	 public void addUpdateListener(UpdateListener l) {
		 rebuildListenerList();
		 listenerList.add(UpdateListener.class, l);
	 }

	 public void removeUpdateListener(UpdateListener l) {
	     listenerList.remove(UpdateListener.class, l);
	 }
	 
	 /**
		 * Javljamo svim listenerima da se događaj desio 
		 */
		public void fireUpdatePerformed() {
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

package graphos.controller;

import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;
import graphos.model.workspace.Diagram;

import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.tree.TreePath;

public class DesktopController implements InternalFrameListener {

	public void internalFrameActivated(InternalFrameEvent arg0) {		
		Diagram dia = ((DiagramGUI) arg0.getInternalFrame()).getDiagram();
		Object[] pom = new Object[] {(dia.getParent().getParent()) , (dia.getParent()) , dia};
		TreePath path = new TreePath(pom);
		MainFrame.getInstance().getTreePane().setSelectionPath(path);
	}

	public void internalFrameClosed(InternalFrameEvent arg0) {
		DiagramGUI dia = ((DiagramGUI) arg0.getInternalFrame()).getDiagramPart();
		MainFrame.getInstance().getDiagramsGUI().remove(dia);
		if (MainFrame.getInstance().getDiagramsGUI().size()==0)
			dia.resetFrameCounter();
		MainFrame.getInstance().getRightPane().selectFrame(true);	
	}

	public void internalFrameClosing(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void internalFrameIconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void internalFrameOpened(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

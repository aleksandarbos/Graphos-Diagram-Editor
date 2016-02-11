package graphos.controller;

import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;
import graphos.model.workspace.Diagram;
import graphos.model.workspace.Project;

import java.beans.PropertyVetoException;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;



public class TreeController implements TreeSelectionListener {

	public void valueChanged(TreeSelectionEvent e) {
		TreePath path = e.getPath();
		for(int i=0; i<path.getPathCount(); i++) {
			if(path.getPathComponent(i) instanceof Diagram){
				Diagram d=(Diagram)path.getPathComponent(i);
								
				for (int j = 0; j < MainFrame.getInstance().getDiagramsGUI().size(); j++) {
					if (MainFrame.getInstance().getDiagramsGUI().get(j).getName().equals(((Diagram)MainFrame.getInstance().
					getTreePane().getLastSelectedPathComponent()).getName())) {
						try {
							MainFrame.getInstance().getDiagramsGUI().get(j).setSelected(true);
						} catch (PropertyVetoException e1) {
							e1.printStackTrace();
						}
						break;
					}
				}
				break;
			}
		}
	}
	
	public void doubleClick() {
		//iz stabla izvuci ime modela dijagrama
		//i prosledis ga u kreiranje DiagramGUI-a
		Object selectedDiagPath=MainFrame.getInstance().getTreePane().getLastSelectedPathComponent();
		if (!(selectedDiagPath instanceof Diagram))
			return;
		Diagram diagram=(Diagram) selectedDiagPath;
		
		int i;
		for (i = 0; i < MainFrame.getInstance().getDiagramsGUI().size(); i++)
			if (MainFrame.getInstance().getDiagramsGUI().get(i).getName().equals(((Diagram)MainFrame.getInstance().
					getTreePane().getLastSelectedPathComponent()).getName()))
				return;
		
		Project project=(Project) diagram.getParent();
		for (i = 0; i < project.getDiagramList().size();i++) {
			if (project.getDiagramList().get(i).equals(diagram)) {
				break;
			}
		}
		Diagram newDiag = project.getDiagramList().get(i);
		DiagramGUI newDiagView = new DiagramGUI();
		newDiagView.setDiagram(newDiag);
		MainFrame.getInstance().getRightPane().add(newDiagView);
		try {
			newDiagView.setSelected(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
	}
}


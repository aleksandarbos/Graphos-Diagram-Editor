package graphos.abs.actions;


import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;
import graphos.model.workspace.Diagram;
import graphos.model.workspace.Project;

import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class BtnNewDiagram extends AbstractAction {


	public BtnNewDiagram(String title, String iconPath, int mnemonic, String accelerator) {
		super(title, new ImageIcon(iconPath));
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
        putValue(SHORT_DESCRIPTION, title ); 
	}

	

public void actionPerformed(ActionEvent e) {
	Object proj=MainFrame.getInstance().getTreePane().getLastSelectedPathComponent();
	if (proj instanceof Project) {
		Diagram newDiag = new Diagram("New Diagram");
		((Project)proj).addDiagram(newDiag);
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTreePane());
		
		DiagramGUI newDiagView = new DiagramGUI();
		newDiagView.setDiagram(newDiag);
		MainFrame.getInstance().getRightPane().add(newDiagView);
		MainFrame.getInstance().getPalette().setCursorSelected();
		//MainFrame.getInstance().getStatusBar().setElementLabel("SELECT");
		//System.out.println("cursor selected!");
		try {
			newDiagView.setSelected(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
	}
	else
		if (proj instanceof Diagram) {
			Project p1 = (Project) ((Diagram) proj).getParent();
			
			Diagram newDiag = new Diagram("New Diagram");
			((Project)p1).addDiagram(newDiag);
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTreePane());
			
			DiagramGUI newDiagView = new DiagramGUI();
			newDiagView.setDiagram(newDiag);
			MainFrame.getInstance().getRightPane().add(newDiagView);
			MainFrame.getInstance().getPalette().setCursorSelected();
			((Project)MainFrame.getInstance().getTreePane().getCurrentProject()).updatePerformed(null);

			//MainFrame.getInstance().getStatusBar().setStateLabel("SELECT");
			//System.out.println("cursor selected!");
			try {
				newDiagView.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
}
}

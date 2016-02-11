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
@SuppressWarnings("serial")
public class BtnNewProject extends AbstractAction {
	
	public BtnNewProject(String title, String iconPath, int mnemonic, String accelerator) {
		super(title, new ImageIcon(iconPath));
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
        putValue(SHORT_DESCRIPTION, title ); 
	}

	public void actionPerformed(ActionEvent arg0) {
		Project proj = new Project();
		MainFrame.getInstance().getTreePane().addProject(proj);
		Diagram newDiag=new Diagram(" ");
		proj.addDiagram(newDiag);
		
		DiagramGUI view=new DiagramGUI();
		view.setDiagram(newDiag);
		MainFrame.getInstance().getRightPane().add(view);
		MainFrame.getInstance().getPalette().setCursorSelected();
		MainFrame.getInstance().getStatusBar().setStateLabel("SELECT");
	
		try {
			view.setSelected(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }


}

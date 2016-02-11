package graphos.abs.actions;

import graphos.gui.MainFrame;
import graphos.model.workspace.Diagram;
import graphos.model.workspace.Project;
import graphos.model.workspace.Workspace;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class BtnRemoveProj extends AbstractAction  {
	public BtnRemoveProj(String title, String iconPath, int mnemonic, String accelerator) {
		super(title, new ImageIcon(iconPath));
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
        putValue(SHORT_DESCRIPTION, title ); 
	}
	public void actionPerformed(ActionEvent e) {
		Object proj=MainFrame.getInstance().getTreePane().getLastSelectedPathComponent();
		if (proj==null || proj instanceof Workspace)
			return;
		Object w[]=MainFrame.getInstance().getTreePane().getAnchorSelectionPath().getPath();
		Object workspace = w[0];
		if (proj instanceof Project) {
			int k =((Project) proj).getDiagramCount();
			for (int ii=0;ii<k;ii++) {
				for (int i = 0; i < MainFrame.getInstance().getDiagramsGUI().size(); i++) {
					if (MainFrame.getInstance().getDiagramsGUI().get(i).getName().equals(((Project) proj).getDiagram(0).getName())) {
						MainFrame.getInstance().getDiagramsGUI().get(i).dispose();
						break;
					}
				}
				((Project) proj).removeDiagram(0);
			}			
			((Workspace)workspace).removeProject((Project)proj);
							
		}
		else 
			if (proj instanceof Diagram) {
				Project p1 = (Project) ((Diagram) proj).getParent();
				
				int k =((Project) p1).getDiagramCount();
				for (int ii=0;ii<k;ii++) {
					for (int i = 0; i < MainFrame.getInstance().getDiagramsGUI().size();i++) {
						if (MainFrame.getInstance().getDiagramsGUI().get(i).getName().equals(((Project) p1).getDiagram(0).getName())) {
							MainFrame.getInstance().getDiagramsGUI().get(i).dispose();
							break;
						}
					}
					p1.removeDiagram(0);
				}
				((Workspace)workspace).removeProject(p1);
			}
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTreePane());
	}
}

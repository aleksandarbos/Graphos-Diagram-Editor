package graphos.abs.actions;

import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;
import graphos.model.workspace.Diagram;
import graphos.model.workspace.Project;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class BtnRemoveDiag extends AbstractAction  {
	public BtnRemoveDiag(String title, String iconPath, int mnemonic, String accelerator) {
		super(title, new ImageIcon(iconPath));
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
        putValue(SHORT_DESCRIPTION, title ); 
	}
	public void actionPerformed(ActionEvent e) {
		Object diag=MainFrame.getInstance().getTreePane().getLastSelectedPathComponent();
		if (diag==null || !(diag instanceof Diagram))
			return;
		Project project = (Project) ((Diagram) diag).getParent();
		for (int i = 0; i < MainFrame.getInstance().getDiagramsGUI().size(); i++) {
			if (MainFrame.getInstance().getDiagramsGUI().get(i).getName().equals(((Diagram)MainFrame.getInstance().
					getTreePane().getLastSelectedPathComponent()).getName())) {
				MainFrame.getInstance().getDiagramsGUI().get(i).dispose();
				DiagramGUI.openFrameCount--;
				break;
			}
		}
		int idx = ((Project)project).getDiagramIndex((Diagram)diag);
		((Project)project).removeDiagram(idx);
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTreePane());
		((Project)MainFrame.getInstance().getTreePane().getCurrentProject()).updatePerformed(null);

    }
}

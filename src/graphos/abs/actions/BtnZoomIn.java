package graphos.abs.actions;

import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class BtnZoomIn extends AbstractAction{

	public BtnZoomIn(String title, String iconPath, int mnemonic, String accelerator) {
		super(title, new ImageIcon(iconPath));
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
        putValue(SHORT_DESCRIPTION, title ); 
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		((DiagramGUI)MainFrame.getInstance().getRightPane().getSelectedFrame()).zoomIn();
	}

}

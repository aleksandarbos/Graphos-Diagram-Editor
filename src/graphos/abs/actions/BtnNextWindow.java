package graphos.abs.actions;

import graphos.gui.MainFrame;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class BtnNextWindow extends AbstractAction {

	public BtnNextWindow(String title, String iconPath, int mnemonic, String accelerator) {
		super(title, new ImageIcon(iconPath));
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
        putValue(SHORT_DESCRIPTION, title ); 
	}

	public void actionPerformed(ActionEvent arg0) {
		if (MainFrame.getInstance().getDiagramsGUI().size()==0)
			return;
		MainFrame.getInstance().getRightPane().selectFrame(false);
		while (!MainFrame.getInstance().getRightPane().getSelectedFrame().getMostRecentFocusOwner().isVisible()) {
			MainFrame.getInstance().getRightPane().selectFrame(false);
		}	
		
	}

}

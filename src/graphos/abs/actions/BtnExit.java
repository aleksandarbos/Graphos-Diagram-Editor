package graphos.abs.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class BtnExit extends AbstractAction {

	public BtnExit(String title, String iconPath, int mnemonic, String accelerator) {
		super(title, new ImageIcon(iconPath));
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
        putValue(SHORT_DESCRIPTION, title + " the creator :)"); 
	}

	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
		
	}

}

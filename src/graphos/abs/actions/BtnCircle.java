package graphos.abs.actions;

import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class BtnCircle extends AbstractAction {

	public BtnCircle(String title, String iconPath, int mnemonic, String accelerator) {
		super(title, new ImageIcon(iconPath));
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
        putValue(SHORT_DESCRIPTION, title ); 
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			((DiagramGUI)MainFrame.getInstance().getRightPane().getSelectedFrame()).startCircleState();
		} catch (Exception e) {
			System.out.println("ALERT: Pokusaj menjanja stanja dok nema aktivnih dijagrama.");
		}
		//MainFrame.getInstance().getStatusBar().setElementLabel("CIRCLE");
	}

}

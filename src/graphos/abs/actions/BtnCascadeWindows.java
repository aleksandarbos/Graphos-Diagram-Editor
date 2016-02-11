package graphos.abs.actions;

import graphos.gui.MainFrame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class BtnCascadeWindows extends AbstractAction {

	public BtnCascadeWindows(String title, String iconPath, int mnemonic, String accelerator) {
		super(title, new ImageIcon(iconPath));
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
        putValue(SHORT_DESCRIPTION, title ); 
	}

	public void actionPerformed(ActionEvent arg0) {
		Dimension pane = MainFrame.getInstance().getRightPane().getSize(); 
		
		JInternalFrame[] list = new JInternalFrame[MainFrame.getInstance().getRightPane().getAllFrames().length];
		
			int xmove = list.length != 0 ? ((pane.width-400) / list.length): 1;
			int ymove = list.length != 0 ? ((pane.height-400) / list.length): 1;
			System.out.println("Dimenzije pane : "+ pane.height + ", " +pane.width);
		
		for (int i=0;i<list.length;i++) {
			MainFrame.getInstance().getRightPane().getAllFrames()[i].setLocation(xmove*i,ymove*i);
			MainFrame.getInstance().getRightPane().getAllFrames()[i].setSize(400,400);
			try {
				MainFrame.getInstance().getRightPane().getAllFrames()[i].setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}

package graphos.abs.actions;

import graphos.gui.MainFrame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class BtnTileWindowsHorz extends AbstractAction {

	public BtnTileWindowsHorz(String title, String iconPath, int mnemonic, String accelerator) {
		super(title, new ImageIcon(iconPath));
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
        putValue(SHORT_DESCRIPTION, title ); 
	}

	public void actionPerformed(ActionEvent arg0) {
		Dimension pane = MainFrame.getInstance().getRightPane().getSize(); 
		int nwidth = pane.width;
		int height = pane.height;
		
		JInternalFrame[] list = new JInternalFrame[MainFrame.getInstance().getRightPane().getAllFrames().length];
		int nheight = (height / list.length); 
		int res = height - list.length * nheight;
		
		for (int i=0;i<list.length-1;i++) {
			MainFrame.getInstance().getRightPane().getAllFrames()[i].setLocation(0,nheight*i);
			MainFrame.getInstance().getRightPane().getAllFrames()[i].setSize(nwidth,nheight);
		}
		MainFrame.getInstance().getRightPane().getAllFrames()[list.length-1].setLocation(0,nheight*(list.length-1));
		MainFrame.getInstance().getRightPane().getAllFrames()[list.length-1].setSize(nwidth,res+nheight);
		
	}

}

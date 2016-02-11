package graphos.abs.actions;

import graphos.gui.MainFrame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class BtnTileWindowsVer extends AbstractAction {

	public BtnTileWindowsVer(String title, String iconPath, int mnemonic, String accelerator) {
		super(title, new ImageIcon(iconPath));
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
        putValue(SHORT_DESCRIPTION, title ); 
	}

	public void actionPerformed(ActionEvent arg0) {
		Dimension pane = MainFrame.getInstance().getRightPane().getSize(); 
		int width = pane.width;
		int nheight = pane.height;
		
		JInternalFrame[] list = new JInternalFrame[MainFrame.getInstance().getRightPane().getAllFrames().length];
		int nwidth = (width / list.length); 
		int res = width - list.length * nwidth;
		
		for (int i=0;i<list.length-1;i++) {
			MainFrame.getInstance().getRightPane().getAllFrames()[i].setLocation(nwidth*i,0);
			MainFrame.getInstance().getRightPane().getAllFrames()[i].setSize(nwidth,nheight);
		}
		MainFrame.getInstance().getRightPane().getAllFrames()[list.length-1].setLocation(nwidth*(list.length-1),0);
		MainFrame.getInstance().getRightPane().getAllFrames()[list.length-1].setSize(res+nwidth,nheight);
		
	}

}

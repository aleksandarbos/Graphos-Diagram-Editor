package graphos.abs.actions;

import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;
import graphos.model.workspace.Project;
import graphos.serialization.DiagramFileFilter;

import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class BtnOpen extends AbstractAction {

	public BtnOpen(String title, String iconPath, int mnemonic,
			String accelerator) {
		super(title, new ImageIcon(iconPath));
		putValue(MNEMONIC_KEY, mnemonic);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
		putValue(SHORT_DESCRIPTION, title);
	}

	public void actionPerformed(ActionEvent arg0) {

		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new DiagramFileFilter());
		if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			// kreiramo ObjectInputStream na izabrani fajl sa .gpf ekstenzijom
			ObjectInputStream os = null;
			try {
				os = new ObjectInputStream(new FileInputStream(
						jfc.getSelectedFile()));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Project p = null;
			try {
				// poziv metode readObject() koja vrši dubinsku deserijalizaciju
				try {
					p = (Project) os.readObject();
					//System.out.println("IME UCITANOG OBJEKTA JE: " + p.getName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			MainFrame.getInstance().setProjectOpening(true); //zbog imena u tree
			System.out.println("MAINNN: " + MainFrame.getInstance().isProjectOpening());
			
			//MainFrame.getInstance().getWorkspaceModel().addProject(p);
			MainFrame.getInstance().getTreePane().addProject(p);
			
			
			// za sve dijagrame u projektu kreiramo jedan DiagramGUI i dodajemo
			// ih u
			// aplikaciju
			for (int i = 0; i < p.getDiagramCount(); i++) {
				DiagramGUI view = new DiagramGUI();
				p.getDiagram(i).getModel().addUpdateListener(p);
				view.setDiagram(p.getDiagram(i));
				MainFrame.getInstance().getRightPane().add(view);
				MainFrame.getInstance().getDiagramsGUI().add(view);
				
				Iterator<DiagramGUI> it = MainFrame.getInstance().getDiagramsGUI().iterator();
				while(it.hasNext()) {
					if(it.next().equals(view))
						try {
							it.next().setSelected(true);
						} catch (PropertyVetoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTreePane());
				//System.out.println(view);
			}
			
		}
	}

}

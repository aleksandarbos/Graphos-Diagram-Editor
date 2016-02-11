package graphos.abs.actions;

import graphos.gui.MainFrame;
import graphos.model.workspace.Project;
import graphos.serialization.DiagramFileFilter;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class BtnSave extends AbstractAction {
	public BtnSave(String title, String iconPath, int mnemonic,
			String accelerator) {
		super(title, new ImageIcon(iconPath));
		putValue(MNEMONIC_KEY, mnemonic);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
		putValue(SHORT_DESCRIPTION, title);
	}

	public void actionPerformed(ActionEvent arg0) {

		JFileChooser jfc = new JFileChooser();
		
		jfc.setFileFilter(new DiagramFileFilter());
		jfc.setSelectedFile(new File(".gpf"));
		

		Project project=MainFrame.getInstance().getTreePane().getCurrentProject();
		File projectFile=project.getProjectFile();
		
		if (!project.isChanged()){
			return;
		}
		
		if (project.getProjectFile()==null){
		         if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
		                   projectFile=jfc.getSelectedFile();           	 
		        	 
		         }else{
		        	return; 
		         }
		         
		}     
		         
	    ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream(projectFile));
			os.writeObject(project);
			project.setProjectFile(projectFile);
			project.setChanged(false);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
}

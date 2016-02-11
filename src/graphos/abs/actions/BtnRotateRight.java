package graphos.abs.actions;

import graphos.commands.AddRotateRightCommand;
import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;
import graphos.model.DiagramSelectionModel;
import graphos.model.elements.DiagramElement;
import graphos.model.workspace.DiagramModel;
import graphos.model.workspace.Project;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class BtnRotateRight extends AbstractAction {

	public BtnRotateRight(String title, String iconPath, int mnemonic, String accelerator) {
		super(title, new ImageIcon(iconPath));
        putValue(MNEMONIC_KEY, mnemonic);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
        putValue(SHORT_DESCRIPTION, title ); 
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		DiagramSelectionModel selectionModel = ((DiagramGUI)MainFrame.getInstance().getRightPane().getSelectedFrame()).getDiagram().getDiagramSelectionModel();
		DiagramModel diagramModel = ((DiagramGUI)MainFrame.getInstance().getRightPane().getSelectedFrame()).getDiagram().getModel();
		
			
		//System.out.println(device + "rotation: " + device.getRotation());
		
		((DiagramGUI)MainFrame.getInstance().getRightPane().getSelectedFrame()).getCommandManager().addCommand(
					new AddRotateRightCommand(diagramModel, selectionModel));
		
		//((DiagramGUI) (MainFrame.getInstance().getRightPane().getSelectedFrame())).updatePerformed(null);
		
		((Project)MainFrame.getInstance().getTreePane().getCurrentProject()).updatePerformed(null);

	}
	

}

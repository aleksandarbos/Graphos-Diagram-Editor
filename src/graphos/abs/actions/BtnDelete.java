package graphos.abs.actions;

import graphos.commands.AddDeleteElementCommand;
import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;
import graphos.model.DiagramSelectionModel;
import graphos.model.workspace.DiagramModel;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class BtnDelete extends AbstractAction {

	public BtnDelete(String title, String iconPath, int mnemonic,
			String accelerator) {
		super(title, new ImageIcon(iconPath));
		putValue(MNEMONIC_KEY, mnemonic);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
		putValue(SHORT_DESCRIPTION, title);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		DiagramSelectionModel selectionModel = ((DiagramGUI)MainFrame.getInstance().getRightPane().getSelectedFrame()).getDiagram().getDiagramSelectionModel();
		DiagramModel diagramModel = ((DiagramGUI)MainFrame.getInstance().getRightPane().getSelectedFrame()).getDiagram().getModel();
		
		((DiagramGUI)MainFrame.getInstance().getRightPane().getSelectedFrame()).getCommandManager().addCommand(
				new AddDeleteElementCommand(diagramModel, selectionModel));

	}
}

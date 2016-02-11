package graphos.tree;

import graphos.controller.TreeController;
import graphos.gui.MainFrame;
import graphos.model.workspace.Diagram;
import graphos.model.workspace.Project;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;



public class WorkspaceTreeEditor extends DefaultTreeCellEditor implements ActionListener {
	
    @SuppressWarnings("unused")
	private Object stavka=null;
    private JTextField edit=null;
    
	public WorkspaceTreeEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
		super(arg0, arg1);
	}

	public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
		
		stavka=arg1;
		edit=new JTextField(arg1.toString());
		edit.addActionListener(this);
		return edit;
	}

	public boolean isCellEditable(EventObject arg0) {
		if (arg0 instanceof MouseEvent)
			if (((MouseEvent)arg0).getClickCount()==3){
				return true;
			}
			else 
				if (((MouseEvent)arg0).getClickCount()==2) {
					TreeController tr = new TreeController();
					tr.doubleClick();
				}
				return false;
	}

	public void actionPerformed(ActionEvent e){
		if (stavka instanceof Project){
			((Project)stavka).setName(e.getActionCommand());
			
		}else{
			((Diagram)stavka).setName(e.getActionCommand());
			
		}
		
		((Project)MainFrame.getInstance().getTreePane().getCurrentProject()).updatePerformed(null);
		//MainFrame.getInstance().getRightPane().repaint();
      //posle promene imena promeniti ga i u DiagramPartu
	}
}

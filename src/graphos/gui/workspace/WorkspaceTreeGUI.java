package graphos.gui.workspace;



import graphos.controller.TreeController;
import graphos.model.workspace.Project;
import graphos.model.workspace.WorkspaceModel;
import graphos.tree.WorkspaceTreeCellRendered;
import graphos.tree.WorkspaceTreeEditor;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;


public class WorkspaceTreeGUI extends JTree {

	public WorkspaceTreeGUI() {         
		addTreeSelectionListener(new TreeController());
	    setCellEditor(new WorkspaceTreeEditor(this,new DefaultTreeCellRenderer()));
	    setCellRenderer(new WorkspaceTreeCellRendered());
	    //setPreferredSize(new Dimension(400, HEIGHT));
	    setEditable(true);
	}
	
	public void addProject(Project project){
		((WorkspaceModel)getModel()).addProject(project);
		SwingUtilities.updateComponentTreeUI(this);
		
	}
	
	
	public Project getCurrentProject() {
		TreePath path = getSelectionPath();
		for(int i=0; i<path.getPathCount(); i++){
			if(path.getPathComponent(i) instanceof Project){
				return (Project)path.getPathComponent(i);
			}
		}
		return null;
	}

}
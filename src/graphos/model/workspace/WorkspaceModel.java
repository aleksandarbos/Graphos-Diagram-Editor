package graphos.model.workspace;


import javax.swing.tree.DefaultTreeModel;

public class WorkspaceModel extends DefaultTreeModel {

	private boolean isProjectOpening = false;
	
	public WorkspaceModel() {
		super(new Workspace());
		
	}

	
	public void addProject(Project project) {
		((Workspace)getRoot()).addProject(project);
	}
	public void removeProject(Project project) {
		((Workspace)getRoot()).removeProject(project);
	}
	
	
}
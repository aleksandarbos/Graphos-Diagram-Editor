package graphos.model.workspace;

import graphos.gui.MainFrame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

public class Workspace implements TreeNode, Serializable {

	private ArrayList<Project> projects = new ArrayList<Project>();  //kolekcija projekata
	
	//transient boolean fileOpen = false;
	
	public Workspace() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String toString(){
		return "Workspace";
	}
	public void removeProject(Project project) {
		//projects.
		projects.remove(project);
	}
	
	public TreeNode getChildAt(int arg0) {
		return getProject(arg0);
	}

	public int getChildCount() {
		return getProjectsCount();
	}

	public TreeNode getParent() {
		return null;
	}

	public int getIndex(TreeNode arg0) {
		return getProjectIndex((Project) arg0);
	}

	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	public Enumeration<Diagram> children() {
		// TODO Auto-generated method stub
		return (Enumeration<Diagram>) projects;
	}

	public void addProject(Project project){
		projects.add(project);
		System.out.println("PROVERAAAA: " + MainFrame.getInstance().isProjectOpening());
		if(!MainFrame.getInstance().isProjectOpening()) {
			MainFrame.getInstance().setProjectOpening(false);
			project.setName("Project: "+projects.size());
			
		}
		MainFrame.getInstance().setProjectOpening(false);
		project.setParent(this);
	}
	
	public Project getProject(int index) {
		return projects.get(index);
	}	
	
	
	public ArrayList<Project> getProjects() {
		return projects;
	}

	public int getProjectIndex(Project project) {
		return projects.indexOf(project);
	}
	public int getProjectsCount() {
		return projects.size();
	}
}
package graphos.model.workspace;

import graphos.event.UpdateEvent;
import graphos.event.UpdateListener;
import graphos.gui.MainFrame;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreeNode;

public class Project implements TreeNode, Serializable, UpdateListener  {

	private ArrayList<Diagram> diagrams = new ArrayList<Diagram>();		//kolekcija dijagrama u okviru jednog projekta
	private Workspace parent;
	private String name;
	private File projectFile;

	private transient boolean changed;
	
	
	public Project(String projectName) {
		this.name=projectName;
		this.changed = false;
		this.projectFile = null;
	}
	
	public Project() {
	
	}
	
	public ArrayList<Diagram> getDiagramList() {
		return diagrams;
	}
	
	public String toString() {				//ako je uzmenjen, stavi * ispred		
		return ((changed?"* ":"")+ name);
	}	

	public TreeNode getChildAt(int arg0) {
		return getDiagram(arg0);
	}

	public int getChildCount() {
		return getDiagramCount();
	}
	
	public void setParent(Workspace parent) {
		this.parent=parent;
	}

	public TreeNode getParent() {
		return parent;
	}

	public int getIndex(TreeNode arg0) {
		return getDiagramIndex((Diagram)arg0);
	}

	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}	
	
	public boolean isLeaf() {
		return false;
	}	

	@SuppressWarnings("unchecked")
	public Enumeration children() {
		// TODO Auto-generated method stub
		return (Enumeration) diagrams;
	}
	
	public Diagram getDiagram(int index) {
		return diagrams.get(index);
	}
	
	public int getDiagramIndex(Diagram diagram) {
		return diagrams.indexOf(diagram);
	}
	
	public int getDiagramCount() {
		return diagrams.size();
	}
	
	public void setName(String name){
		this.name=name;
	}

	public void addDiagram(Diagram diagram){
		diagram.getModel().addUpdateListener(this);
		diagrams.add(diagram);
		diagram.setName( this.name+" - Diagram: "+String.valueOf(diagrams.size()));
		diagram.setParent(this);
	}
	
	public void removeDiagram(int index) {
		diagrams.remove(index);
	}
	
	public int getFirstChildIndex() {
		int i=0;
		while (diagrams.get(i)==null)
			i++;
		return i;
	}
	
	
	public void updatePerformed(UpdateEvent e) {
		setChanged(true);
		System.out.println("Promena u projektu!!!");
	}


	public boolean isChanged() {
		return changed;
	}

	
	public String getName() {
		return name;
	}

	public File getProjectFile() {
		return projectFile;
	}


	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
	}

	public void setChanged(boolean changed) {
		if (this.changed != changed){
		     this.changed = changed;
		     SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTreePane());
		}
	
	}
}
package graphos.model.workspace;

import graphos.model.DiagramSelectionModel;

import java.io.Serializable;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

public class Diagram implements TreeNode, Serializable {
	
	private String name;
	private Project parent;
	
	
	private DiagramModel model = new DiagramModel();
	
	private DiagramSelectionModel diagramSelectionModel = new DiagramSelectionModel();
	

	public Diagram(String diagramName) {
		name = diagramName;
	}
	
	public String toString(){
		return name;
	}

	public TreeNode getChildAt(int arg0) {
		return null;
	}

	public int getChildCount() {
		return 0;
	}
	
	public void setParent(Project parent) {
		this.parent=parent;
	}
	
	public boolean equals(Diagram diag) {
		return (this.name == diag.name);
	}
	
	public TreeNode getParent() {
		return parent;
	}

	public int getIndex(TreeNode arg0) {
		return -1;
	}

	public boolean getAllowsChildren() {
		return false;
	}
	
	public DiagramModel getModel() {
		return model;
	}

	public boolean isLeaf() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public Enumeration children() {
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setModel(DiagramModel model) {
		this.model = model;
	}
	
	public DiagramSelectionModel getDiagramSelectionModel() {
		return diagramSelectionModel;
	}

}

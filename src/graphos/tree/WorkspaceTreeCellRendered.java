package graphos.tree;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

@SuppressWarnings("serial")
public class WorkspaceTreeCellRendered extends DefaultTreeCellRenderer {


	public WorkspaceTreeCellRendered() {
		
	}

	  public Component getTreeCellRendererComponent(JTree tree,Object value,boolean sel,
			  						boolean expanded,boolean leaf,int row,boolean hasFocus) {
		  
             super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);
            
             return this;
	  }
}

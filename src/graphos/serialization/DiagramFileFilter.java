package graphos.serialization;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class DiagramFileFilter extends FileFilter {

	public DiagramFileFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
		return (f.isDirectory() || f.getName().toLowerCase().endsWith(".gpf"));
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Graphos Project Files (*.gpf)";
		
	}

}

package graphos.gui;

import graphos.gui.workspace.DiagramGUI;
import graphos.gui.workspace.WorkspaceTreeGUI;
import graphos.model.elements.DiagramElement;
import graphos.model.workspace.WorkspaceModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

public class MainFrame extends JFrame{		//JFRAME
	
	private static MainFrame instance;	//klasa AppCore je singleton klasa
	private Toolbar toolbar = new Toolbar();
	private JScrollPane leftPane;
	private JDesktopPane rightPane;
	private Menu menuBar = new Menu();
	private Palette palette = new Palette();
	private WorkspaceTreeGUI treePane = new WorkspaceTreeGUI();
	private StatusBar statusBar = new StatusBar();
	private AboutDialog aboutWin = new AboutDialog();
	private ElementDialog elementWin;
	private WorkspaceModel model = new WorkspaceModel();
	
	private boolean isProjectOpening = false;
	
	private ArrayList<DiagramGUI> diagrams = new ArrayList<DiagramGUI>();
	
	private MainFrame() {
		super("Graphos - Best graphics editor software ");
		initFrame();	//init frame gui
		initGUI();		//init app gui
		
	}
	
	private void initFrame() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim.width-143,dim.height-100);
		
		
		
		ImageIcon img = new ImageIcon("src/images/logo.png");
		setIconImage(img.getImage());
	
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}

	public static MainFrame getInstance() {
		if(instance == null) {
			instance = new MainFrame();
			return instance;
		}
		else
			return instance;
	}
	
	private void initGUI() {

		//menu init
		
		
		setJMenuBar(menuBar);
		
		//toolbar init
		add(toolbar, BorderLayout.NORTH);
		
		//palette init
		add(palette, BorderLayout.EAST);
		
		//JDesktop
		
		rightPane = new JDesktopPane() {
		    private Image image;
		    {
		       
		            image = Toolkit.getDefaultToolkit().createImage("src/images/background.png");
		      
		    }

		    @Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		        
		    }
		};
		
		//JTree - left pane
		WorkspaceModel model = new WorkspaceModel();
		treePane = new WorkspaceTreeGUI();
		treePane.setModel(model);
		treePane.setExpandsSelectedPaths(true);
		ToolTipManager.sharedInstance().registerComponent(treePane);
		
		leftPane = new JScrollPane(treePane);
		
		//jsplitpane init
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
		splitPane.setDividerLocation(210);
		add(splitPane);
		
		//statusBar
		add(statusBar, BorderLayout.SOUTH);
		
		
		
	}
	
	public AboutDialog getAboutWin() {
		return aboutWin;
	}
	
	public JDesktopPane getRightPane() {
		return rightPane;
	}
	
	public ArrayList<DiagramGUI> getDiagramsGUI() {
		return diagrams;
	}
	
	public WorkspaceTreeGUI getTreePane() {
		return treePane;
	}
	
	public StatusBar getStatusBar() {
		return statusBar;
	}
	
	public Palette getPalette() {
		return palette;
	}
	
	
	
	public void showElementWin(DiagramElement diagEl) {
		elementWin = new ElementDialog(diagEl);
		elementWin.setVisible(true);
	}
	
	public ElementDialog getElemetWin() {
		return elementWin;
	}

	public WorkspaceModel getWorkspaceModel() {
		return model;
	}
	
	public boolean isProjectOpening() {
		System.out.println("opening: " + isProjectOpening);
		return isProjectOpening;
	}


	public void setProjectOpening(boolean isOpening) {
		this.isProjectOpening = isOpening;
		System.out.println("set: " + isProjectOpening);
	}

	public Toolbar getToolbar() {
		return toolbar;
	}

	public ArrayList<DiagramGUI> getDiagrams() {
		return diagrams;
	}
	
	
	
	
}

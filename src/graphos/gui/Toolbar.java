package graphos.gui;

import graphos.abs.actions.BtnAbout;
import graphos.abs.actions.BtnCascadeWindows;
import graphos.abs.actions.BtnRotateRight;
import graphos.abs.actions.BtnCopy;
import graphos.abs.actions.BtnCut;
import graphos.abs.actions.BtnDelete;
import graphos.abs.actions.BtnRotateLeft;
import graphos.abs.actions.BtnFind;
import graphos.abs.actions.BtnFindReplace;
import graphos.abs.actions.BtnNewDiagram;
import graphos.abs.actions.BtnNewProject;
import graphos.abs.actions.BtnNextWindow;
import graphos.abs.actions.BtnOpen;
import graphos.abs.actions.BtnPaste;
import graphos.abs.actions.BtnPreferences;
import graphos.abs.actions.BtnPrevWindow;
import graphos.abs.actions.BtnPrint;
import graphos.abs.actions.BtnRedo;
import graphos.abs.actions.BtnRemoveDiag;
import graphos.abs.actions.BtnRemoveProj;
import graphos.abs.actions.BtnSave;
import graphos.abs.actions.BtnSelectAll;
import graphos.abs.actions.BtnTileWindowsHorz;
import graphos.abs.actions.BtnTileWindowsVer;
import graphos.abs.actions.BtnUndo;
import graphos.abs.actions.BtnZoomIn;
import graphos.abs.actions.BtnZoomOut;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Toolbar extends JToolBar{

	private BtnRotateLeft miRotateLeft;
	private BtnRotateRight miRotateRight;
	
	private BtnUndo miUndo;
	private BtnRedo miRedo;
	
	public Toolbar() {
		super("ToolBar", SwingConstants.HORIZONTAL);
		initToolbar();
	}
	
	void initToolbar() {
		
	
		
		BtnNewProject miNewProject = new BtnNewProject("New Project", "src/images/ico/newProject.png", 0, "control shift P");
		BtnNewDiagram miNewDiagram = new BtnNewDiagram("New Diagram", "src/images/ico/newDiagram.png", 'D', "control shift D");	
		BtnPrint miPrint = new BtnPrint("Print", "src/images/ico/print.png", 'P', "control P");
		BtnOpen miOpen = new BtnOpen("Open Project", "src/images/ico/open.png", 'I', "control O");
		BtnSave miSave = new BtnSave("Save Project", "src/images/ico/save.png", 'E', "control S");
		
		BtnRemoveDiag miRemoveDiag = new BtnRemoveDiag("Remove Diagram", "src/images/ico/removeDiag.png", 'R', "alt shift F4");
		BtnRemoveProj miRemoevProj = new BtnRemoveProj("Remove Project", "src/images/ico/removeProj.png", 'G', "alt shift F3");
		
		miUndo = new BtnUndo("Undo", "src/images/ico/undo.png", 'U', "control Z");
		miRedo = new BtnRedo("Redo", "src/images/ico/redo.png", 'R', "control Y");
		BtnCut miCut = new BtnCut("Cut", "src/images/ico/cut.png", 'C', "control X");
		BtnCopy miCopy = new BtnCopy("Copy", "src/images/ico/copy.png", 'C', "control C");
		BtnPaste miPaste = new BtnPaste("Paste", "src/images/ico/paste.png", 'V', "control V");
		BtnDelete miDelete = new BtnDelete("Delete", "src/images/ico/delete.png", 'D', "control D");
		BtnSelectAll miSelectAll = new BtnSelectAll("Select All", "src/images/ico/selectAll.png", 'A', "control A");
		BtnFind miFind = new BtnFind("Find", "src/images/ico/find.png", 'F', "control F");
		BtnFindReplace miFindReplace = new BtnFindReplace("Find and replace", "src/images/ico/findReplace.png", 'E', "control R");
	
		BtnZoomIn miZoomIn = new BtnZoomIn("Zoom In", "src/images/ico/zoomIn.png", 'Z', "control PLUS");
		BtnZoomOut miZoomOut = new BtnZoomOut("Zoom Out", "src/images/ico/zoomOut.png", 'O', "control MINUS");
		miRotateLeft= new BtnRotateLeft("Rotate Left", "src/images/ico/rotate_left.png", 'E', "control alt ENTER");
		miRotateRight = new BtnRotateRight("Rotate Right", "src/images/ico/rotate_right.png", 'C', "control alt ESCAPE");
		
		BtnCascadeWindows miCascade = new BtnCascadeWindows("Cascade Windows", "src/images/ico/cascadeWindows.png", 'C', "control shift C");
		BtnTileWindowsHorz miTileWinHorz = new BtnTileWindowsHorz("Tile Windows Horizontally", "src/images/ico/tileWindowsHorz.png", 'T', "control shift T");
		BtnTileWindowsVer miTileWinVer = new BtnTileWindowsVer("Tile Windows Vertically", "src/images/ico/tileWindowsVer.png", 'V', "control shift V");
		BtnNextWindow miNextWindow = new BtnNextWindow("Next Window", "src/images/ico/nextWindow.png", 'N', "control shift N");
		BtnPrevWindow miPrevWindow = new BtnPrevWindow("Previous Window", "src/images/ico/prevWindow.png", 'P', "control shift P");

		BtnPreferences miPreferences = new BtnPreferences("Preferences", "src/images/ico/settings.png", 'S', "control shift S");
		BtnAbout miAbout = new BtnAbout("About", "src/images/ico/about.png", 'A', "control shift A");
		//adding items at toolbar
		
				add(miNewProject);
				add(miNewDiagram);	
				addSeparator();
				add(miPrint);
				addSeparator();
				add(miOpen);
				add(miSave);
				addSeparator();
				add(miUndo);
				add(miRedo);
				addSeparator();
				add(miCut);
				add(miCopy);
				add(miPaste);
				add(miDelete);
				addSeparator();
				add(miRemoevProj);
				add(miRemoveDiag);
				addSeparator();
				add(miFind);
				add(miFindReplace);
				add(miSelectAll);
				addSeparator();
				add(miZoomIn);
				add(miZoomOut);
				add(miRotateLeft);
				add(miRotateRight);
				addSeparator();
				add(miCascade);
				add(miTileWinHorz);
				add(miTileWinVer);
				add(miNextWindow);
				add(miPrevWindow);
				addSeparator();
				add(miPreferences);
				addSeparator();
				add(miAbout);
				
				miUndo.setEnabled(false);
				miRedo.setEnabled(false);
				
	}

	public BtnUndo getBtnUndo() {
		return miUndo;
	}

	public BtnRedo getBtnRedo() {
		return miRedo;
	}


	

	
	
}

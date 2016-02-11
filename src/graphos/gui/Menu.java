package graphos.gui;


import graphos.abs.actions.BtnAbout;
import graphos.abs.actions.BtnCascadeWindows;
import graphos.abs.actions.BtnRotateRight;
import graphos.abs.actions.BtnCopy;
import graphos.abs.actions.BtnCut;
import graphos.abs.actions.BtnDelete;
import graphos.abs.actions.BtnExit;
import graphos.abs.actions.BtnRotateLeft;
import graphos.abs.actions.BtnExport;
import graphos.abs.actions.BtnFind;
import graphos.abs.actions.BtnFindReplace;
import graphos.abs.actions.BtnImport;
import graphos.abs.actions.BtnNewDiagram;
import graphos.abs.actions.BtnNewProject;
import graphos.abs.actions.BtnNextWindow;
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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;


public class Menu extends JMenuBar {

	private JToolBar toolbar = new JToolBar();
	
	public Menu() {
		//FILE
		JMenu menuFile = new JMenu("File");
		menuFile.setMnemonic('F');
		
		JMenu menuNew = initMenu("New", new ImageIcon("src/images/ico/new.png"), 'N');
		JMenu menuOpen = initMenu("Open", new ImageIcon("src/images/ico/open.png"), 'O');
		JMenu menuSave = initMenu("Save", new ImageIcon("src/images/ico/save.png"), 'S');
		JMenu menuCloseRemove = initMenu("Close/remove", new ImageIcon("src/images/ico/closeRemove.png"), 'C');
		BtnRemoveDiag miRemoveDiag = new BtnRemoveDiag("Remove Diagram", "src/images/ico/removeDiag.png", 'R', "alt shift F4");
		BtnRemoveProj miRemoevProj = new BtnRemoveProj("Remove Project", "src/images/ico/removeProj.png", 'G', "alt shift F3");
		
		BtnNewProject miNewProject = new BtnNewProject("New Project", "src/images/ico/newProject.png", 0, "control shift P");
		BtnNewDiagram miNewDiagram = new BtnNewDiagram("New Diagram", "src/images/ico/newDiagram.png", 'D', "control shift D");
		BtnSave miSave = new BtnSave("Save", "src/images/ico/save.png", 'S', "control S");
		BtnPrint miPrint = new BtnPrint("Print", "src/images/ico/print.png", 'P', "control P");
		BtnImport miImport = new BtnImport("Import", "src/images/ico/import.png", 'I', "control I");
		BtnExport miExport = new BtnExport("Export", "src/images/ico/export.png", 'E', "control E");
		BtnExit miExit = new BtnExit("Exit", "src/images/ico/exit.png", 'I', "alt F4");
		
		//1st File section group
		//menuFile.add(miNew); 
		menuFile.add(menuNew);
		menuNew.add(miNewProject);
		menuNew.add(miNewDiagram);
		
		menuFile.add(menuOpen); 
		menuFile.add(menuSave);
		menuFile.add(menuCloseRemove); 
		menuCloseRemove.add(miRemoevProj);
		menuCloseRemove.add(miRemoveDiag);
		menuFile.addSeparator();
		//2nd File section group
		menuFile.add(miPrint);
		menuFile.addSeparator();
		//3th File section group
		menuFile.add(miImport);
		menuFile.add(miExport);
		menuFile.add(miExit);
		
		
		
		//EDIT section
		
		
		
		JMenu menuEdit = new JMenu("Edit");
		menuEdit.setMnemonic('E');
		
		BtnUndo miUndo = new BtnUndo("Undo", "src/images/ico/undo.png", 'U', "control Z");
		BtnRedo miRedo = new BtnRedo("Redo", "src/images/ico/redo.png", 'R', "control Y");
		BtnCut miCut = new BtnCut("Cut", "src/images/ico/cut.png", 'C', "control X");
		BtnCopy miCopy = new BtnCopy("Copy", "src/images/ico/copy.png", 'C', "control C");
		BtnPaste miPaste = new BtnPaste("Paste", "src/images/ico/paste.png", 'V', "control V");
		BtnDelete miDelete = new BtnDelete("Delete", "src/images/ico/delete.png", 'D', "control D");
		BtnSelectAll miSelectAll = new BtnSelectAll("Select All", "src/images/ico/selectAll.png", 'A', "control A");
		BtnFind miFind = new BtnFind("Find", "src/images/ico/find.png", 'F', "control F");
		BtnFindReplace miFindReplace = new BtnFindReplace("Find and replace", "src/images/ico/findReplace.png", 'E', "control R");
		
		
		menuEdit.add(miUndo);
		menuEdit.add(miRedo);
		menuEdit.addSeparator();
		menuEdit.add(miCut);
		menuEdit.add(miCopy);
		menuEdit.add(miPaste);
		menuEdit.addSeparator();
		menuEdit.add(miDelete);
		menuEdit.add(miSelectAll);
		menuEdit.addSeparator();
		menuEdit.add(miFind);
		menuEdit.add(miFindReplace);
		
		//VIEW section
		JMenu menuView = new JMenu("View");
		menuView.setMnemonic('V');
		
		BtnZoomIn miZoomIn = new BtnZoomIn("Zoom In", "src/images/ico/zoomIn.png", 'Z', "control PLUS");
		BtnZoomOut miZoomOut = new BtnZoomOut("Zoom Out", "src/images/ico/zoomOut.png", 'O', "control MINUS");
		BtnRotateLeft miExpand = new BtnRotateLeft("Expand", "src/images/ico/expand.png", 'E', "control alt ENTER");
		BtnRotateRight miCollapse = new BtnRotateRight("Collapse", "src/images/ico/collapse.png", 'C', "control alt ESCAPE");
		
		
		menuView.add(miZoomIn);
		menuView.add(miZoomOut);
		menuView.addSeparator();
		menuView.add(miExpand);
		menuView.add(miCollapse);
		
		//WINDOW section
		JMenu menuWindow = new JMenu("Window");
		menuWindow.setMnemonic('W');
		
		BtnCascadeWindows miCascade = new BtnCascadeWindows("Cascade Windows", "src/images/ico/cascadeWindows.png", 'C', "control shift C");
		BtnTileWindowsHorz miTileWinHorz = new BtnTileWindowsHorz("Tile Windows Horizontally", "src/images/ico/tileWindowsHorz.png", 'T', "control shift T");
		BtnTileWindowsVer miTileWinVer = new BtnTileWindowsVer("Tile Windows Vertically", "src/images/ico/tileWindowsVer.png", 'V', "control shift V");
		BtnNextWindow miNextWindow = new BtnNextWindow("Next Window", "src/images/ico/nextWindow.png", 'N', "control shift N");
		BtnPrevWindow miPrevWindow = new BtnPrevWindow("Previous Window", "src/images/ico/prevWindow.png", 'P', "control shift P");
		
		menuWindow.add(miCascade); 
		menuWindow.add(miTileWinHorz);
		menuWindow.add(miTileWinVer);
		menuWindow.addSeparator();
		menuWindow.add(miNextWindow);
		menuWindow.add(miPrevWindow);
		
		//TOOLS section
		JMenu menuTools = new JMenu("Tools");
		menuTools.setMnemonic('T');
		
		BtnPreferences miPreferences = new BtnPreferences("Preferences", "src/images/ico/settings.png", 'S', "control shift S");
		menuTools.add(miPreferences);
		
		//HELP section
		JMenu menuHelp = new JMenu("Help");
		menuHelp.setMnemonic('H');
		
		BtnAbout miAbout = new BtnAbout("About", "src/images/ico/about.png", 'A', "control shift A");
		menuHelp.add(miAbout);
		
		
		
		//adding menu items to menu bar
		add(menuFile); add(menuEdit); add(menuView);
		add(menuTools); add(menuWindow); add(menuHelp);
		
		
		
	}
	
	private JMenu initMenu(String name, Icon icon, char mnemonic) {
		JMenu ret = new JMenu(name);
		ret.setIcon(icon);
		ret.setMnemonic(mnemonic);
		return ret;
	}
	
}

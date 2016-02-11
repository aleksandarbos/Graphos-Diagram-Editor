package graphos.gui;

import graphos.model.elements.DiagramElement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ElementDialog extends JDialog {
	private DiagramElement diagramElement;
	private Color chosenColor;
	
	public ElementDialog(DiagramElement diagEl) {
		super();
		this.diagramElement = diagEl;
		setModal(true);
		this.chosenColor = (Color)this.diagramElement.getPaint();
		initGUI();
	}
	
	private void initGUI() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		setLocation((dim.width) - 450, (dim.height/2) - 200);
		setSize(260, 450);
		setTitle("Svojstva: " + diagramElement.getName());
		
		final Color tempColor = chosenColor;
		
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//jpanel.setLayout(new L);
		
		JLabel jlHeader = new JLabel("<html><h2>Svojstva elementa:</h2></html>");
		JLabel jlSubHeader = new JLabel("<html><h3>Izaberi boju elementa:<h3></html>");
		JLabel jlName = new JLabel("Preimenuj naziv elementa: ");
		JLabel jlDescription = new JLabel("Promeni opis elementa: ");
		final JLabel jlSquare = new JLabel("       ");
		JLabel jlRazmak = new JLabel("<html><h2> <br><br>  <br> <h2></html>");
		
		jlName.setOpaque(true);
		jlDescription.setOpaque(true);
		jlSquare.setOpaque(true);
		jlSquare.setBackground(chosenColor);
		
		final JTextField tfName = new JTextField(diagramElement.getName());
		tfName.setPreferredSize(new Dimension(220, 30));
		
		final JTextArea taDescription = new JTextArea(diagramElement.getDescription());
		taDescription.setPreferredSize(new Dimension(220, 100));
		
		JButton jbColorDialog = new JButton("PODESI BOJU ELEMENTA");
		JButton jbSaveChanges = new JButton("OK");
		JButton jbCancel = new JButton("OTKAZI");
		
		jbSaveChanges.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				diagramElement.setName(tfName.getText());
				diagramElement.setDescription(taDescription.getText());
				MainFrame.getInstance().getRightPane().repaint();
				MainFrame.getInstance().getElemetWin().setVisible(false);
			}
		});
		
		jbCancel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				diagramElement.setPaint(tempColor);
				MainFrame.getInstance().getRightPane().repaint();
				MainFrame.getInstance().getElemetWin().setVisible(false);
				
			}
		});
		
		ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        Color initialBackground = jlSquare.getBackground();
		        chosenColor = JColorChooser.showDialog(null, "Izaberite boju: ", initialBackground);
		        if (chosenColor != null) {
		        	jlSquare.setBackground(chosenColor);
		        	diagramElement.setPaint(chosenColor);
		        	MainFrame.getInstance().getRightPane().repaint();
		        }
		      }
		};

		jbColorDialog.addActionListener(actionListener);
		
		
		jpanel.add(jlHeader);
		jpanel.add(jlName);
		jpanel.add(tfName);
		jpanel.add(jlDescription);
		jpanel.add(taDescription);
		jpanel.add(jlSubHeader);
		jpanel.add(jbColorDialog);
		jpanel.add(jlSquare);
		jpanel.add(jlRazmak);
		jpanel.add(jbSaveChanges);
		jpanel.add(jbCancel);
		
		
		
		add(jpanel);
	}
}

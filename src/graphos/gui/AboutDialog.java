package graphos.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import com.sun.xml.internal.ws.api.Component;




@SuppressWarnings("serial")
public class AboutDialog extends JDialog {

	public AboutDialog() {
		super();
		initGUI();
	}

	private void initGUI() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		setTitle("About the program.");
		setSize(400, 280);
		setLocation((dim.width/2)-200, (dim.height/2)-140);
		JLabel ta = new JLabel();
		JPanel jpanel = new JPanel();
	
		
		ta.setText("<html><h2>Graphos v1.0</h2><br>Aleksandar Bosnjak<br>RA-92/2012<br>Faculty of "
				+ "technical sciencies.<br><a href='http://www.aleksandarbosnjak.tk'>www.aleksandarbosnjak.tk</a> </html>");
		
		jpanel.setBackground(Color.WHITE);
		
		JLabel logo = new JLabel(new ImageIcon("src/images/logo.gif"));
		
		
		
		
		
		jpanel.add(logo, BorderLayout.CENTER);
		jpanel.add(ta, BorderLayout.CENTER);
		add(jpanel);
		
	}
	
}

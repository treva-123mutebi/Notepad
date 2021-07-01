import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Function_Color extends JFrame implements ActionListener{
	GUI gui;
	JFrame f;
	JButton b;
	JTextArea ta;
	
	
	
	public Function_Color(GUI gui) {
		this.gui = gui;
	}
public void changeColor(String color) {
		switch(color) {
		case "White" : 
			gui.window.getContentPane().setBackground(Color.white);
			gui.textArea.setBackground(Color.white);
			gui.textArea.setForeground(Color.BLACK);
			break;
		case "Black" : 
			gui.window.getContentPane().setBackground(Color.black);
			gui.textArea.setBackground(Color.black);
			gui.textArea.setForeground(Color.white);
			break;
		case "Blue" : 
			gui.window.getContentPane().setBackground(Color.blue);
			gui.textArea.setBackground(Color.blue);
			gui.textArea.setForeground(Color.white);
			break;
		}

	}
@Override
public void actionPerformed(ActionEvent e) {
	Color c = JColorChooser.showDialog(this, "Color selector", Color.cyan);
	gui.textArea.setBackground(c);
}
public void colorChoice() {
	
	f = new JFrame("Color Selector");
	b = new JButton("Color pad");
	b.setBounds(200,250,100,30);
	ta = new JTextArea();
	ta.setBounds(10, 10, 300, 200);

	b.addActionListener(this);
	f.add(b);
	f.add(ta);
	f.setLayout(null);
	f.setSize(400,400);
	f.setVisible(true);

}
public void statusBar() {
	JPanel statusbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
	statusbar.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY) , new EmptyBorder(4 , 4 , 4 , 4)));
	final JLabel status = new JLabel();
	statusbar.add(status);
	
	JLabel content = new JLabel("Content in the middle");
	content.setHorizontalAlignment(JLabel.CENTER);
	
	gui.window.add(content);
	gui.window.add(statusbar, BorderLayout.SOUTH);
	statusbar.setVisible(true);
	
}
}	

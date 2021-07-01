import javax.swing.JOptionPane;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Function_help {
	GUI gui;
	Desktop desktop =Desktop.getDesktop();
	
	public  Function_help(GUI gui){
		this.gui = gui;
	}
	
	public void aboutNotepad() {
		JOptionPane.showMessageDialog(null , "This notepad was created edited "
				+ "and developed by Agonza" + "\n For more Info:\n"
				
				+ "Tel : +256707 855823" , 
				"About Agonza's Notepad", 1);
		
	}
	public void viewhelp() throws Exception {
		desktop.browse(new URI("http://bing.com"));
	}

}

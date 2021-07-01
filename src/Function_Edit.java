//import java.awt.datatransfer.Clipboard;
//import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;


public class Function_Edit {
	GUI gui;

	
	
	public Function_Edit(GUI gui) {
		this.gui = gui;
	}
	
	public void undo(){
			
		gui.um.undo();
		
	}
	public void redo(){
		gui.um.redo();

	}
	public void cut_paste_copy_selectAll(ActionEvent e) {
		if(e.getSource() == gui.editCut) {
			gui.textArea.cut();
		}
		if(e.getSource()== gui.editPaste) {
			gui.textArea.paste();
		}
		if(e.getSource()== gui.editCopy) {
			gui.textArea.copy();
		}
		if(e.getSource()== gui.editSelectAll) {
			gui.textArea.selectAll();
		}if(e.getSource() == gui.editDelete) {
			gui.textArea.cut();
			/*String selected = gui.textArea.getSelectedText();
			if(!selected.equals("")) {
				
			} */
		}
		
	}
	/*public void find() {
		String findStr = gui.textArea.getText().toUpperCase();
		int findstrLength = findStr.length();
		
		String findtextarea = gui.textArea.getText().toUpperCase();
		Highlighter h = gui.textArea.getHighlighter();
		h.removeAllHighlights();
		
		try {
			int index = 0;
			while(index>=0) {
				index = findtextarea.indexOf(findStr,index);
				h.addHighlight(index, index + findstrLength , DefaultHighlighter.DefaultPainter);
			}
		}catch(Exception e){
			System.out.println("Problem with finding that");
		}
	}*/
	public void dateTime() {
	LocalDateTime current = LocalDateTime.now();
	
	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss.SSS");
	String formatted = current.format(date);
	
	
	System.out.println(formatted);
}
public void findandreplace() {
	
}
}

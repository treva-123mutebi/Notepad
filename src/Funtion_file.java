import java.awt.FileDialog;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.text.View;





//import javax.swing.JFrame;

public class Funtion_file {
	GUI gui; // instantiate the gui class for its access
	String fileName;
	String fileAddress;
	//PrinterJob print = PrinterJob.getPrinterJob();
	//PageFormat pformat = print.pageDialog(print.defaultPage());

	
	public Funtion_file(GUI gui){
		//constructor to enable us access the original GUI
		
		this.gui = gui;
	}
	public void newFile() {
		
		//method to add function to  the new under File
		gui.textArea.setText("");//sets an empty slate or string for a new notepad
		gui.window.setTitle("New & untitled ");//title of the new window
		fileName = null;
		fileAddress = null;
	}
	public void open() {
		
		//method adds function to the open
		FileDialog fd = new FileDialog(gui.window, "Open" , FileDialog.LOAD);  // The FileDialog.LOAD is specific for opening 
		fd.setVisible(true);  //of course without this it wont show up
		
		if (fd.getFile()!= null) {
			fileName = fd.getFile(); //gets filename
			fileAddress = fd.getDirectory(); //gets file address
			gui.window.setTitle(fileName); //sets file name in notepad
			
		}
		//System.out.println("file address + filename " + fileAddress + fileName);
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileAddress+ fileName));// needed to receive the file address
			gui.textArea.setText("");
			String line = null;
			
			while ((line = br.readLine())!=null) {
				//readline() reads a text line buy line
				gui.textArea.append(line + "\n");
				
			}
			br.close();   //closes the text reader
		}catch(Exception e) {
			System.out.println("File Not Openened");
			
		}
	}
	public void save() {
		if(fileName == null) {
			saveAs();
			try {
				FileWriter fw = new FileWriter(fileAddress + fileName);
				fw.write(gui.textArea.getText());
				gui.window.setTitle(fileName);
				fw.close();
				
			}catch(Exception e) {
				System.out.println("SOMETHING WRONG");
			}
		}
		
	}
	public void saveAs() {
		FileDialog fd = new FileDialog(gui.window , "Save", FileDialog.SAVE);
		fd.setVisible(true);
		
		if (fd.getFile()!= null) {
			fileName =fd.getFile();
			fileAddress = fd.getDirectory();
			gui.window.setTitle(fileName);
		}
		try {
			FileWriter fw = new FileWriter(fileAddress + fileName);
			fw.write(gui.textArea.getText());
			fw.close();
		}catch(Exception e){
			System.out.println("SOMETHING IS WRONG");
		}
		}
	public void exit() {
		gui.conf.cofirm();
		System.exit(0);
	}
public void newWindow() {
	new GUI();
}
public void print() {
	PrinterJob print = PrinterJob.getPrinterJob();
	PageFormat pformat = print.pageDialog(print.defaultPage());
	
		if(print.printDialog()) {
			try {
				print.print();
			}catch(PrinterException e) {
				System.out.println(e);
		
			}
		}
}

}

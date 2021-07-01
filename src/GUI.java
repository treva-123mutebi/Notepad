import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;


public class GUI extends JFrame implements ActionListener{
	// All these classes help us create and make this happen
	//TextArea components
	JFrame window; //creates window on .....JFrame
	
	
	
	JTextArea textArea; //creates the text area on ..........JFrame
	boolean wordWrapOn = false;
	JScrollPane scrolPane;
	
	// creates the vertical and horizontal scroll panes to control the boundaries
	
	//Top menu bar
	JMenuBar menuBar;  //obviously for the menu bar 
	JMenu menuFile , menuEdit , menuFormat , menuView , menuHelp ,menuColor  ;  //creates the File on the menu bar
	JMenuItem fileNew , fileNewWindow , fileOpen , fileSave , fileSaveAs , filePageSetUp , filePrint , fileExit ;   //sub menu items for File
	
	//sub menu items for edit
	JMenuItem editUndo , editCut , editCopy ,editPaste , editDelete , editSearchWithBing , editFind ;
	JMenuItem editFindNext , editFindPrevious , editReplace , editGoTo , editSelectAll , editTimeDate ;
	//Edit menu
	
	
	//file menu
	//sub menu items for format
	JMenuItem formatWordWrap , fontArial , formatColor , fontComicSans , fontTNR , fontSize8 , fontSize12 , fontSize16 , fontSize20 , fontSize24 , fontSize28 ;
	JMenu  formatFont , formatFontSize;
	
	//sub menu items for view 
	JMenuItem viewZoom , viewStatusBar;
	
	//sub menu items for help
	JMenuItem helpViewHelp , helpSendFeedBack , helpAboutNotePad;
	
	JMenuItem color1 , color2 , color3;
	
	Funtion_file file = new Funtion_file(this);   //instantiates the function class so we can access it 
	Function_Format format = new Function_Format(this);
	Function_Color color = new Function_Color(this);
	Function_Edit edit = new Function_Edit(this);
	Function_help help = new Function_help(this);
	ConfirmationMessage conf = new ConfirmationMessage(this);
	
	UndoManager um = new UndoManager();

	public static void main(String[] args) {
		new GUI();
	}
	
	public GUI() { //constructor of the window
		
		
		createWindow(); //initializes the create window method
		createTextArea(); //initializes the text area to be created
		createMenuBar(); // for menu bar obviously
		format.selectedFont = "Arial";
		format.createFont(16);
		//color.changeColor("White");
		//format.wordWrap();
		window.setVisible(true);    //makes the visibility official
		createFileMenu();  		//creates subMenuItems for file
		createEditMenu();		//creates subMenuItems for edit
		createFormatMenu();
		//createColorMenu();
		//creates subMenuItems for format
		createViewMenu();		//creates subMenuItems for view
		createHelpMenu();		//creates subMenuItems for help
	}
	public void createWindow() {
		
		//Display on top of the window instantiates the new JFrame window
		window = new JFrame("MyNotepad");
		
		//default size of note pad window you can alter it accordingly
		window.setSize(800,600); 
		
		//closes the window efficiently
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		
	}
	
	//method creates the text area
	public void createTextArea() {
		
		// instantiates the new JTextArea
		textArea = new JTextArea(); 
		textArea.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent e) {
				um.addEdit(e.getEdit());
			}
		});		
		
		
		scrolPane = new JScrollPane(textArea , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrolPane.setBorder(BorderFactory.createEmptyBorder());
		window.add(scrolPane);
		
		//window.add(textarea); not necessary since we have ScrolPane
	}
	public void createMenuBar() {
		
		//creates the menu bar
		menuBar = new JMenuBar();
		window.setJMenuBar(menuBar);
		
		//creates file menu and adds it to the menu bar
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		//edit on menu bar
		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);
		
		//format on menu bar
		menuFormat = new JMenu("Format");
		menuBar.add(menuFormat);
		

		//color on menu bar
		menuView = new JMenu("View");
		menuBar.add(menuView);
		
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		
	}
	public void createFileMenu() {
		
		
		// creates sub JMenuItems under the file on the MenuBar
		fileNew = new JMenuItem("New");
		fileNew.addActionListener(this);
		fileNew.setActionCommand("New"); //passes this "New" string over to action listener
		menuFile.add(fileNew);
		fileNew.setAccelerator(KeyStroke.getKeyStroke('N' , Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		fileNewWindow = new JMenuItem("New Window");
		fileNewWindow.addActionListener(this);
		fileNewWindow.setActionCommand("New Window");
		menuFile.add(fileNewWindow);
		//fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N , Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		fileNewWindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.SHIFT_MASK));
		
		fileOpen = new JMenuItem("Open...");
		fileOpen.addActionListener(this);
		fileOpen.setActionCommand("Open...");
		menuFile.add(fileOpen);
		fileOpen.setAccelerator(KeyStroke.getKeyStroke('O' , Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		fileSave = new JMenuItem("Save");
		fileSave.addActionListener(this);
		fileSave.setActionCommand("Save");
		menuFile.add(fileSave);
		fileSave.setAccelerator(KeyStroke.getKeyStroke('S' , Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		fileSaveAs = new JMenuItem("Save As...");
		fileSaveAs.addActionListener(this);
		fileSaveAs.setActionCommand("Save As...");
		menuFile.add(fileSaveAs);
		fileSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.SHIFT_MASK));
		
		filePageSetUp = new JMenuItem("Page Setup...");
		menuFile.add(filePageSetUp);
		
		filePrint = new JMenuItem("Print...");
		filePrint.addActionListener(this);
		filePrint.setActionCommand("Print");
		menuFile.add(filePrint);
		filePrint.setAccelerator(KeyStroke.getKeyStroke('P' , Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		fileExit = new JMenuItem("Exit");
		fileExit.addActionListener(this);
		fileExit.setActionCommand("Exit");
		menuFile.add(fileExit);
		
	}
	
	public void createEditMenu() {
		
		// creates sub JMenuItems under the Edit on the MenuBar
		editUndo = new JMenuItem("Undo");
		editUndo.addActionListener(this);
		editUndo.setActionCommand("Undo");
		menuEdit.add(editUndo);
		editUndo.setAccelerator(KeyStroke.getKeyStroke('Z' , Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		editCut = new JMenuItem("Cut");
		menuEdit.add(editCut);
		editCut.addActionListener(this);
		editCut.setActionCommand("Cut");
		editCut.setAccelerator(KeyStroke.getKeyStroke('X' , Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		editCopy = new JMenuItem("Copy");
		menuEdit.add(editCopy);
		editCopy.addActionListener(this);
		editCopy.setActionCommand("Copy");
		editCopy.setAccelerator(KeyStroke.getKeyStroke('C' , Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		editPaste = new JMenuItem("Paste");
		editPaste.addActionListener(this);
		editPaste.setActionCommand("Paste");
		menuEdit.add(editPaste);
		editPaste.setAccelerator(KeyStroke.getKeyStroke('V' , Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		
		editDelete = new JMenuItem("Delete");
		menuEdit.add(editDelete);
		editDelete.addActionListener(this);
		editDelete.setActionCommand("Delete");
		editDelete.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_DELETE, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		editSearchWithBing = new JMenuItem("Search with Bing...");
		menuEdit.add(editSearchWithBing);
		editSearchWithBing.addActionListener(this);
		editSearchWithBing.setActionCommand("Search with Bing...");
		editSearchWithBing.setAccelerator(KeyStroke.getKeyStroke('E' , Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		editFind = new JMenuItem("Find...");
		menuEdit.add(editFind);
		editFind.addActionListener(this);
		editFind.setActionCommand("Find...");
		editFind.setAccelerator(KeyStroke.getKeyStroke('F' , Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		editFindNext = new JMenuItem("Find Next");
		menuEdit.add(editFindNext);
		editFindNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F13 , Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		
		editFindPrevious = new JMenuItem("Redo");
		menuEdit.add(editFindPrevious);
		editFindPrevious.addActionListener(this);
		editFindPrevious.setActionCommand("Redo");
		editFindPrevious.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F13,ActionEvent.SHIFT_MASK));
		
		editReplace = new JMenuItem("Replace...");
		menuEdit.add(editReplace);
		editReplace.setAccelerator(KeyStroke.getKeyStroke('H' , Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		editGoTo = new JMenuItem("Go To...");
		menuEdit.add(editGoTo);
		editGoTo.setAccelerator(KeyStroke.getKeyStroke('G' , Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		editSelectAll = new JMenuItem("Select All");
		menuEdit.add(editSelectAll);
		editSelectAll.addActionListener(this);
		editSelectAll.setActionCommand("Select All");
		editSelectAll.setAccelerator(KeyStroke.getKeyStroke('A' , Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		editTimeDate = new JMenuItem("Time/Date");
		editTimeDate.addActionListener(this);
		editTimeDate.setActionCommand("Time/Date");
		menuEdit.add(editTimeDate);
		
	}
	
	
	public void createFormatMenu() {
		
		// creates sub JMenuItems under the format on the MenuBar
		formatWordWrap = new JMenuItem("Word  Wrap");
		formatWordWrap.addActionListener(this);
		formatWordWrap.setActionCommand("Word  Wrap");
		menuFormat.add(formatWordWrap);
		
		formatFont = new JMenu("Font...");
		menuFormat.add(formatFont);
		
		formatFontSize = new JMenu("FontSize...");
		menuFormat.add(formatFontSize);
		
		fontArial = new JMenu("Arial");
		fontArial.addActionListener(this);
		fontArial.setActionCommand("Arial");
		formatFont.add(fontArial);
		
		fontComicSans = new JMenu("Comic Sans MS");
		fontComicSans.addActionListener(this);
		fontComicSans.setActionCommand("Comic Sans MS");
		formatFont.add(fontComicSans);
		
		fontTNR = new JMenu("Times New Roman");
		fontTNR.addActionListener(this);
		fontTNR.setActionCommand("Times New Roman");
		formatFont.add(fontTNR);
		
		formatColor = new JMenuItem("Color");
		formatColor.addActionListener(this);
		formatColor.setActionCommand("Color");
		menuFormat.add(formatColor);
		
		fontSize8 = new JMenuItem("8");
		fontSize8.addActionListener(this);
		fontSize8.setActionCommand("8");
		formatFontSize.add(fontSize8);
		
		fontSize12 = new JMenuItem("12");
		fontSize12.addActionListener(this);
		fontSize12.setActionCommand("12");
		formatFontSize.add(fontSize12);
		
		fontSize16 = new JMenuItem("16");
		fontSize16.addActionListener(this);
		fontSize16.setActionCommand("16");
		formatFontSize.add(fontSize16);
		
		fontSize20 = new JMenuItem("20");
		fontSize20.addActionListener(this);
		fontSize20.setActionCommand("20");
		formatFontSize.add(fontSize20);
		
		fontSize28 = new JMenuItem("28");
		fontSize28.addActionListener(this);
		fontSize28.setActionCommand("28");
		formatFontSize.add(fontSize28);
		
		
		
		
		
	}
	/*public void createColorMenu() {
		color1 = new JMenuItem("White");
		color1.addActionListener(this);
		color1.setActionCommand("White");
		menuColor.add(color1);
		
		color2 = new JMenuItem("Black");
		color2.addActionListener(this);
		color2.setActionCommand("Black");
		menuColor.add(color2);
		
		color3 = new JMenuItem("Blue");
		color3.addActionListener(this);
		color3.setActionCommand("Blue");
		menuColor.add(color3);
		
	}*/
	
	public void createViewMenu() {
		
		// creates sub JMenuItems under the View on the MenuBar
		viewZoom = new JMenuItem("Zoom        >");
		menuView.add(viewZoom);
		
		viewStatusBar = new JMenuItem("Status Bar");
		viewStatusBar.addActionListener(this);
		viewStatusBar.setActionCommand("Status Bar");
		menuView.add(viewStatusBar);
	}
	
	public void createHelpMenu() {
		
		// creates sub JMenuItems under the Help on the MenuBar
		helpViewHelp = new JMenuItem("View Help");
		helpViewHelp.addActionListener(this);
		helpViewHelp.setActionCommand("View Help");
		menuHelp.add(helpViewHelp);
		
		helpSendFeedBack = new JMenuItem("Send Feedback");
		menuHelp.add(helpSendFeedBack);
		
		helpAboutNotePad = new JMenuItem("About Notepad");
		helpAboutNotePad.addActionListener(this);
		helpAboutNotePad.setActionCommand("About Notepad");
		menuHelp.add(helpAboutNotePad);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	//	Color c = JColorChooser.showDialog(this, "Color selector", Color.cyan);
	//	textArea.setBackground(c);
		
		String command = e.getActionCommand();  // expects a string command to take up the value of e
		
		switch(command) {
		case "New": file.newFile();     // on receiving the "New" string in this case calls the newfile function in the FunctionFile class
			break;
		case  "Open...": file.open();   // on receiving the "Open..." string in this case calls the open function in the FunctionFile class
			break;
		case "Save As...": file.saveAs();
			break;
		case "Save": file.save();
			break;
		case "Exit": file.exit();
			break;
		case "Word  Wrap": format.wordWrap();
			break;
		case "Arial" : format.setFont(command);
			break;
		case "Times New Roman" : format.setFont(command);
			break;
		case "Comic Sans MS" : format.setFont(command);
			break;
		case "8" : format.createFont(8);
			break;
		case "12" : format.createFont(12);
			break;
		case "16" : format.createFont(16);
			break;
		case "20" : format.createFont(20);
			break;
		case "24" : format.createFont(24);
			break;
		case "28" : format.createFont(28);
			break;
		case "Color" : color.colorChoice();
			break;
			case "New Window" : file.newWindow();
			break;
		case "Print" : file.print();
			break;
		case "About Notepad": help.aboutNotepad();
			break;
		case "Undo" : edit.undo();
			break;
		case "Cut" : edit.cut_paste_copy_selectAll(e);
		break;
		case "Copy" : edit.cut_paste_copy_selectAll(e);
		break;
		case "Paste" : edit.cut_paste_copy_selectAll(e);
		break;
		case "Select All" : edit.cut_paste_copy_selectAll(e);
		break;
		case "Status Bar" : color.statusBar();
		break;
		case "Delete" : edit.cut_paste_copy_selectAll(e);
		break;
		case "Time/Date": edit.dateTime();
		break;
		case "Redo" : edit.redo();
		break;
		case "View Help": try {
				help.viewhelp();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		break;
		case "Search with Bing...": try {
			help.viewhelp();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	}
}

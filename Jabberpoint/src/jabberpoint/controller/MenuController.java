package jabberpoint.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import jabberpoint.model.accessor.Accessor;
import jabberpoint.model.accessor.AccessorFactory;
import jabberpoint.model.Slideshow;
import jabberpoint.model.accessor.XMLAccessor;
import jabberpoint.model.util.Parameters;
import jabberpoint.model.action.ActionFactory;
import jabberpoint.model.action.RelativeNavigationAction;
import jabberpoint.view.AboutBox;
import jabberpoint.view.SlideViewerFrame;

/** <p>De controller voor het menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.7 2017/11/13 Randy Pottgens, Ivo Willemsen
 */
public class MenuController extends MenuBar {

	private SlideViewerFrame frame; // het frame, alleen gebruikt als ouder voor de Dialogs
	private Slideshow slideShow; // Er worden commando's gegeven aan de presentatie

	private static final long serialVersionUID = 227L;

	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN_SOURCE = "Open...";
	protected static final String OPEN_DEMO = "Open configured demo";
	protected static final String SLIDE_NUMBER = "Enter the slide number to navigate to";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";

	protected static final String TESTFILE = "test.xml";
	protected static final String SAVEFILE = "dump.xml";

	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";
	protected static final String SAVEERR = "Save Error";

	// blocking empty construction
	private MenuController(){
		
	}
	
	private void loadFile(){
		System.out.println("loadFile chosen.");
        JFileChooser fc = new JFileChooser();
		int r = fc.showOpenDialog(new Frame());
		if (r == JFileChooser.APPROVE_OPTION) {
			System.out.println("Opening file...");
            File file = fc.getSelectedFile();
            Accessor xmlAccessor = AccessorFactory.getInstance();
			//try {
				Parameters param = new Parameters();
				System.out.println(file.getAbsolutePath());
				param.setValue(Parameters.Parameter.FILE_NAME, file.getAbsolutePath());
				Slideshow show = xmlAccessor.load(param);
				//show.setSlideNumber(0);
				//throw new IOException("fdfd");
			//} catch (IOException exc) {
				//JOptionPane.showMessageDialog(frame, IOEX + exc,
     			//LOADERR, JOptionPane.ERROR_MESSAGE);
			//}
			
            //This is where a real application would open the file.
            //log.append("Opening: " + file.getName() + "." + newline);
        } else {
            //log.append("Open command cancelled by user." + newline);
        }
        //log.setCaretPosition(log.getDocument().getLength());
	}
	
	private void saveFile(){
		System.out.println("saveFile chosen.");
        JFileChooser fc = new JFileChooser();
		int r = fc.showSaveDialog(new Frame());
		if (r == JFileChooser.APPROVE_OPTION) {
			System.out.println("saving to file...");
            File file = fc.getSelectedFile();
            Accessor xmlAccessor = AccessorFactory.getInstance();
			//try {
				Parameters param = new Parameters();
				System.out.println(file.getAbsolutePath());
				param.setValue(Parameters.Parameter.FILE_NAME, file.getAbsolutePath());
				Slideshow show = Slideshow.getInstance();
				xmlAccessor.save(param, show);
				//show.setSlideNumber(0);
				//throw new IOException("fdfd");
			//} catch (IOException exc) {
				//JOptionPane.showMessageDialog(frame, IOEX + exc,
     			//LOADERR, JOptionPane.ERROR_MESSAGE);
			//}
			
            //This is where a real application would open the file.
            //log.append("Opening: " + file.getName() + "." + newline);
        } else {
            //log.append("Open command cancelled by user." + newline);
        }
        //log.setCaretPosition(log.getDocument().getLength());
	}
	
	
	/**
	 * Constructor that builds the menu controller
	 * @param frame the frame that owns the composite drawing component
	 */
	protected MenuController(final SlideViewerFrame frame) {
		this();
		this.frame = frame;
		slideShow = Slideshow.getInstance();
		MenuItem menuItem;
		Menu fileMenu = new Menu(FILE);
		fileMenu.add(menuItem = mkMenuItem(OPEN_SOURCE));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				//presentation.clear();
				loadFile();
				frame.update();
			}
		} );
		fileMenu.add(menuItem = mkMenuItem(OPEN_DEMO));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				ActionFactory.createOpenDemoSlideshowAction().execute();
				frame.update();
			}
		} );
		fileMenu.add(menuItem = mkMenuItem(NEW));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				//presentation.clear();
				frame.update();
			}
		});
		fileMenu.add(menuItem = mkMenuItem(SAVE));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
				frame.update();
			}
		});
		fileMenu.addSeparator();
		fileMenu.add(menuItem = mkMenuItem(EXIT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				//presentation.exit(0);
			}
		});
		add(fileMenu);
		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(menuItem = mkMenuItem(NEXT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				ActionFactory.createRelativeNavigationAction(RelativeNavigationAction.NavigationDirection.NEXT).execute();
				frame.update();
			}
		});
		viewMenu.add(menuItem = mkMenuItem(PREV));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				ActionFactory.createRelativeNavigationAction(RelativeNavigationAction.NavigationDirection.PREVIOUS).execute();
				frame.update();
			}
		});
		viewMenu.add(menuItem = mkMenuItem(GOTO));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				int slideNumber = Integer.parseInt(JOptionPane.showInputDialog((Object) SLIDE_NUMBER)) - 1;
				if (Slideshow.getInstance().isCorrectSlideNumber(slideNumber)) {
					ActionFactory.createAbsoluteNavigationAction(slideNumber ).execute();
				}
				frame.update();
			}
		});
		add(viewMenu);
		Menu helpMenu = new Menu(HELP);
		helpMenu.add(menuItem = mkMenuItem(ABOUT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AboutBox.show(frame);
			}
		});
		setHelpMenu(helpMenu);		// nodig for portability (Motif, etc.).
	}

// een menu-item aanmaken
	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}

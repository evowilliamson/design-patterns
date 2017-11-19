package jabberpoint.controller;

import java.awt.Frame;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import jabberpoint.model.action.ActionFactory;

/**
 * This class has only static methods to display the file open and file save dialog boxes.
 * @author Randy Pottgens
 *
 */
public class FileDialogs {
	public static void loadFile(Frame frame){
		System.out.println("loadFile chosen.");
		JFileChooser fc = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("XML presentations", "xml");
		fc.addChoosableFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		int r = fc.showOpenDialog(frame);
		if (r == JFileChooser.APPROVE_OPTION) {
			System.out.println("Opening file...");
            File file = fc.getSelectedFile();
            ActionFactory.createOpenSlideshowAction(file.getAbsolutePath()).execute();
		}
	}
	
	public static void saveFile(Frame frame){
		System.out.println("saveFile chosen.");
        JFileChooser fc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("XML presentations", "xml");
        fc.addChoosableFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);
		int r = fc.showSaveDialog(frame);
		if (r == JFileChooser.APPROVE_OPTION) {
			System.out.println("Saving file...");
            File file = fc.getSelectedFile();
            ActionFactory.createSaveSlideshowAction(file.getAbsolutePath()).execute();
		}
	}
}

package jabberpoint.controller;

import java.awt.Frame;
import java.io.File;

import javax.swing.JFileChooser;

import jabberpoint.model.action.ActionFactory;

public class FileDialogs {
	public static void loadFile(Frame frame){
		System.out.println("loadFile chosen.");
        JFileChooser fc = new JFileChooser();
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
		int r = fc.showSaveDialog(frame);
		if (r == JFileChooser.APPROVE_OPTION) {
			System.out.println("Saving file...");
            File file = fc.getSelectedFile();
            ActionFactory.createSaveSlideshowAction(file.getAbsolutePath()).execute();
		}
	}
}

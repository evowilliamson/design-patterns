package jabberpoint.view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import jabberpoint.controller.ControllerFactory;
import jabberpoint.controller.KeyController;
import jabberpoint.controller.MenuController;

/**
 * <p>Het applicatiewindow voor een slideviewcomponent</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.7 2017/11/12 Randy Pottgens, Ivo Willemsen
*/

public class SlideViewerFrame extends JFrame {
	private static final long serialVersionUID = 3227L;
	
	public final static int WIDTH_SCREEN = 1200;
	public final static int HEIGHT_SCREEN = 800;

	public SlideViewerFrame(String title) {
		super(title);
	}

	/**
	 * Method that initializes the frame
	 */
	public void initialize(SlideViewerComponent slideViewerComponent) {
		addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		getContentPane().add(slideViewerComponent);
		addKeyListener(ControllerFactory.createKeyController(this));
		setMenuBar(ControllerFactory.createMenuController(this));
		setSize(new Dimension(WIDTH_SCREEN, HEIGHT_SCREEN));
		setVisible(true);
	}

	/**
	 * Refreshes the screen: It will cause the {@link SlideViewerComponent#paintComponent(Graphics)} to be fired.
	 */
	public void update() {
		repaint();
	}

}

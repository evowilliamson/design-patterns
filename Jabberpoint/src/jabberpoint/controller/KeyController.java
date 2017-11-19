package jabberpoint.controller;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import jabberpoint.model.Slideshow;
import jabberpoint.model.action.AbsoluteNavigationAction;
import jabberpoint.model.action.ActionFactory;
import jabberpoint.model.action.RelativeNavigationAction;
import jabberpoint.view.SlideViewerFrame;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.7 2017/11/13 Randy Pottgens, Ivo Willemsen
*/

public class KeyController extends KeyAdapter {
	private SlideViewerFrame frame;

	private KeyController(){
	}

	protected KeyController(SlideViewerFrame frame) {
		this.frame = frame;

	}

	public void keyPressed(KeyEvent keyEvent) {
		switch(keyEvent.getKeyCode()) {
			case KeyEvent.VK_PAGE_DOWN:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_ENTER:
			case '+':
				ActionFactory.createRelativeNavigationAction(RelativeNavigationAction.NavigationDirection.NEXT).execute();
				frame.update();
				break;
			case KeyEvent.VK_PAGE_UP:
			case KeyEvent.VK_UP:
			case '-':
				ActionFactory.createRelativeNavigationAction(RelativeNavigationAction.NavigationDirection.PREVIOUS).execute();
				frame.update();
				break;
			case KeyEvent.VK_END:
				ActionFactory.createAbsoluteNavigationAction(AbsoluteNavigationAction.NavigationPosition.LAST).execute();
				frame.update();
				break;
			case KeyEvent.VK_HOME:
				ActionFactory.createAbsoluteNavigationAction(AbsoluteNavigationAction.NavigationPosition.FIRST).execute();
				frame.update();
				break;
		case 'q':
			case 'Q':
				System.exit(0);
				break; // wordt nooit bereikt als het goed is
			default:
				break;
		}
	}
}

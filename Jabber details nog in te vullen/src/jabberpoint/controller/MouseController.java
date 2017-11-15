/**
 * 
 */
package jabberpoint.controller;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import jabberpoint.model.action.Action;
import jabberpoint.model.slideitems.ActionItemDecorator;
import jabberpoint.model.slideitems.SlideItem;
import jabberpoint.view.ActionItemComponent;
import jabberpoint.view.SlideViewerComponent;

/**
 * @author rpott
 *
 */
public class MouseController extends MouseInputAdapter {

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse Click Detected.");
		JComponent c = (JComponent) e.getComponent();
		Point p = e.getPoint();
		ActionItemComponent a =  (ActionItemComponent) c.getComponentAt(p);
		//ActionItemComponent a =  (ActionItemComponent) e.getComponent();
		SlideItem slideItem = a.getSlideItem();
		List<Action> actions = slideItem.getActions();
		for (int i = 0; i < actions.size();i++) {
			Action action = actions.get(i);
			action.execute();	
		}
		c.removeAll();
		//c.revalidate();
		//c.repaint();
	}
}

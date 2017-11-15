/**
 * 
 */
package jabberpoint.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import jabberpoint.model.action.Action;
import jabberpoint.model.slideitems.ActionItemDecorator;
import jabberpoint.view.ActionItemComponent;

/**
 * @author rpott
 *
 */
public class MouseController implements MouseListener {

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		ActionItemComponent actionItemComponent =  (ActionItemComponent) e.getComponent();
		ActionItemDecorator actionItemDecorator = actionItemComponent.getActionItem();
		List<Action> actions = actionItemDecorator.getActions();
		for (int i = 0; i < actions.size();i++) {
			Action action = actions.get(i);
			action.execute();	
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

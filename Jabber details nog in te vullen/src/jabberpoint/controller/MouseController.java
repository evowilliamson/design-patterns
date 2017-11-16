/**
 * 
 */
package jabberpoint.controller;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import jabberpoint.model.action.Action;
import jabberpoint.model.slideitems.SlideItem;

/**
 * @author rpott
 *
 */
public class MouseController extends MouseInputAdapter {
	
	private List<BoundingBox> boxes;
	
	public MouseController(){
		clearList();
	}
	
	public void addBoundingBox(Rectangle r, SlideItem s){
		boxes.add(new BoundingBox(r, s));
	}
	
	public void clearList(){
		boxes = new ArrayList<BoundingBox>();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse Click Detected.");
		JComponent c = (JComponent) e.getComponent();
		Point p = e.getPoint();
		int s = boxes.size();
		for (int i = 0; i < s; i++)
		{
			System.out.print("Looking if box ");
			System.out.print(i);
			System.out.print(" from ");
			System.out.print(s);
			System.out.println(" contains the clicked point.");
			
			BoundingBox b = boxes.get(i);
			if(b.contains(p))
			{
				System.out.println("Cliked point found, doing actions");
				
				SlideItem slideItem = b.getItem();
				List<Action> actions = slideItem.getActions();
				for (int j = 0; j < actions.size();j++) {
					Action action = actions.get(j);
					action.execute();	
				}
				break;
			}
		}
		//clearList();
		c.repaint();
	}
}

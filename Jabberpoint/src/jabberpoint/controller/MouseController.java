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
import jabberpoint.model.slideitems.ActionItemDecorator;
import jabberpoint.model.slideitems.SlideItem;
import jabberpoint.view.SlideViewerFrame;

/**
 * @author rpott
 *
 */
public class MouseController extends MouseInputAdapter {
	
	private List<BoundingBox> boxes;
	
	protected MouseController(){
		clearList();
	}
	
	// just for contract reason, frame is not used, but MouseController uses input from SlideViewerComponent
	protected MouseController(SlideViewerFrame frame){
		//just eating the frame argument because it is not needed.
		this();
	}
	
	public void addBoundingBox(Rectangle r, ActionItemDecorator a){
		boxes.add(ControllerFactory.createBoundingBox(r, a));
	}
	
	public void clearList(){
		boxes = new ArrayList<BoundingBox>();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		System.out.println("Mouse Click Detected.");
		JComponent component = (JComponent) event.getComponent();
		Point point = event.getPoint();
		int s = boxes.size();
		for (int i = 0; i < s; i++)
		{
			System.out.print("Looking if box ");
			System.out.print(i);
			System.out.print(" from ");
			System.out.print(s);
			System.out.println(" contains the clicked point.");
			
			BoundingBox box = boxes.get(i);
			if(box.contains(point))
			{
				System.out.println("Cliked point found, doing actions");
				ActionItemDecorator decorator = box.getItem();
				//SlideItem slideItem = decorator.getSlideItem();
				//List<Action> actions = slideItem.getActions();
				List<Action> actions = decorator.getActions();
				for (int j = 0; j < actions.size();j++) {
					Action action = actions.get(j);
					action.execute();	
				}
				break;
			}
		}
		//clearList();
		component.repaint();
	}
}

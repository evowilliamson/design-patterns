/**
 * 
 */
package jabberpoint.view;

import javax.swing.JComponent;

import jabberpoint.model.slideitems.ActionItemDecorator;
import jabberpoint.model.slideitems.SlideItem;

/**
 * @author rpott
 *
 */
public class ActionItemComponent extends JComponent {
	SlideItem slideItem;

	/**
	 * 
	 */
	public ActionItemComponent(SlideItem s) {
		super();
		slideItem = s;
		// TODO Auto-generated constructor stub
	}
	public SlideItem getSlideItem(){
		return slideItem;
	}
}

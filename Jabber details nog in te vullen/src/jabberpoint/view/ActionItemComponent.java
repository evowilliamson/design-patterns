/**
 * 
 */
package jabberpoint.view;

import javax.swing.JComponent;

import jabberpoint.model.slideitems.ActionItemDecorator;

/**
 * @author rpott
 *
 */
public class ActionItemComponent extends JComponent {
	ActionItemDecorator actionItemDecorator;

	/**
	 * 
	 */
	public ActionItemComponent(ActionItemDecorator a) {
		super();
		actionItemDecorator = a;
		// TODO Auto-generated constructor stub
	}
	public ActionItemDecorator getActionItem(){
		return actionItemDecorator;
	}
}

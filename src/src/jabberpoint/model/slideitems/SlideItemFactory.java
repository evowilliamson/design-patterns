package jabberpoint.model.slideitems;

import java.util.List;

import jabberpoint.model.action.Action;

/**
 * Factory class that creates the SlideItems
 * @author Randy Pottgens
 */
public class SlideItemFactory {

	/**
	 * Creates an object of type {@link ActionItemDecorator}
	 * @param slideItem the {@link DisplayableItem} that should be attached to the {@link ActionItemDecorator}
	 * @param actions the list of {@link Action}
	 * @return an object of type {@link ActionItemDecorator}
	 */
	public static ActionItemDecorator createActionItemDecorator(DisplayableItem slideItem, List<Action> actions){
		return new ActionItemDecorator(slideItem, actions);
	}

	/**
	 * Creates an object of type {@link BitmapItem}
	 * @param level the level of the item
	 * @param fileName the name of the file that contains the bitmap
	 * @return an object of type {@link BitmapItem}
	 */
	public static BitmapItem createBitmapItem(final int level, String fileName){
		return new BitmapItem(level, fileName);
	}

	/**
	 * Creates a {@link TextItem}
	 * @param level the level of the item
	 * @param text the text that should be displayed of this item
	 * @return an object of type {@link TextItem}
	 */
	public static TextItem createTextItem(final int level, String text){
		return new TextItem(level, text);
	}
}

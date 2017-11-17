package jabberpoint.model.slideitems;

import java.util.List;

import jabberpoint.model.action.Action;

public class SlideItemFactory {
	public static ActionItemDecorator createActionItemDecorator(SlideItem slideItem, List<Action> actions){
		return new ActionItemDecorator(slideItem, actions);
	}
	
	public static BitmapItem createBitmapItem(final int level, String fileName){
		return new BitmapItem(level, fileName);
	}
	
	public static TextItem createTextItem(final int level, String text){
		return new TextItem(level, text);
	}
}

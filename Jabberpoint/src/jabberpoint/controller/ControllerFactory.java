package jabberpoint.controller;

import java.awt.Rectangle;

import jabberpoint.model.slideitems.ActionItemDecorator;
import jabberpoint.view.SlideViewerFrame;

public class ControllerFactory {

	public ControllerFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static MouseController createMouseController(SlideViewerFrame frame) {
		return new MouseController(frame);
	}
	public static MouseController createMouseController() {
		return new MouseController();
	}
	
	public static KeyController createKeyController(SlideViewerFrame frame) {
		return new KeyController(frame);
	}
	
	public static MenuController createMenuController(SlideViewerFrame frame) {
		return new MenuController(frame);
	}

	protected static BoundingBox createBoundingBox(Rectangle r, ActionItemDecorator a){
		return new BoundingBox(r, a);
	}
}

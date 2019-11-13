package jabberpoint.view;

import jabberpoint.controller.MouseController;

/**
 * Class that creates View objects
 * @author Randy Pottgens
 */
public class SlideViewerFactory {
	public static SlideViewerFrame createSlideViewerFrame(String title){
		return new SlideViewerFrame(title);
	}

	public static SlideViewerComponent createSlideViewerComponent(MouseController mouseController){
		return new SlideViewerComponent(mouseController);
	}

}

package jabberpoint.view;

import jabberpoint.controller.MouseController;

public class SlideViewerFactory {
	public static SlideViewerFrame createSlideViewerFrame(String title){
		return new SlideViewerFrame(title);
	}
	/*public static SlideViewerComponent getSlideViewerComponent(){
		return new SlideViewerComponent();
	}*/
	public static SlideViewerComponent createSlideViewerComponent(MouseController mouseController){
		return new SlideViewerComponent(mouseController);
	}
}

package jabberpoint.model.action;

import jabberpoint.model.SlideShow;
import jabberpoint.model.accessor.AccessorFactory;
import jabberpoint.model.util.Parameters;

/**
 * Definition of an action that opens the Demo Slideshow
 */
public class OpenDemoSlideshowAction extends SlideshowAction {

    @Override
    public void execute() {
        Parameters parameters = new Parameters();
        parameters.setValue(Parameters.Parameter.SLIDESHOW_NAME, "Demo Slideshow");
        SlideShow slideShow = AccessorFactory.getInstance().load(parameters);
        slideShow.drawSlide(slideShow.getCurrentSlideNumber());
    }
}

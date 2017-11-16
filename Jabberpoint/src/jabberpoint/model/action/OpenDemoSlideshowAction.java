package jabberpoint.model.action;

import jabberpoint.model.Slideshow;
import jabberpoint.model.accessor.AccessorFactory;
import jabberpoint.model.util.Parameters;

/**
 * Definition of an action that opens the Demo Slideshow
 * 
 * @author Ivo Willemsen
 */
public class OpenDemoSlideshowAction extends SlideshowAction {

    @Override
    public void execute() {
        Parameters parameters = new Parameters();
        parameters.setValue(Parameters.Parameter.SLIDESHOW_NAME, "Demo Slideshow");
        AccessorFactory.getDemoAccessor().load(parameters);
        Slideshow slideShow = Slideshow.getInstance();
        slideShow.setCurrentSlideNumber(Slideshow.FIRST_SLIDE);
    }
}

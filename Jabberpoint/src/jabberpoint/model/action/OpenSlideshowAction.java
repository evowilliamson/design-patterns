package jabberpoint.model.action;

import jabberpoint.model.Slideshow;
import jabberpoint.model.accessor.AccessorFactory;
import jabberpoint.model.util.Parameters;

/**
 * Definition of an action that opens the Demo Slideshow
 * 
 * @author Ivo Willemsen
 */
public class OpenSlideshowAction extends SlideshowAction {

    private String fileName;

	protected OpenSlideshowAction(final String fileName) {

	    this.fileName = fileName;

    }
	
    @Override
    public void execute() {
        Parameters parameters = new Parameters();
        parameters.setValue(Parameters.Parameter.FILE_NAME, this.fileName);
        AccessorFactory.getInstance().load(parameters);
        Slideshow slideShow = Slideshow.getInstance();
        slideShow.setCurrentSlideNumber(slideShow.getCurrentSlideNumber());
        //slideShow.draw();

    }
    
    // SAVE TO FILE NEEDS GETTERS
    public String getFileName(){
    	return fileName;
    }
}

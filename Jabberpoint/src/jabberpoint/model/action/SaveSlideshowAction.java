package jabberpoint.model.action;

import jabberpoint.model.Slideshow;
import jabberpoint.model.accessor.AccessorFactory;
import jabberpoint.model.util.Parameters;

/**
 * Definition of an action that opens the Demo Slideshow
 * 
 * @author Ivo Willemsen
 * @author Randy Pottgens (copy from file open and change to save)
 */
public class SaveSlideshowAction extends SlideshowAction {

    private String fileName;

	protected SaveSlideshowAction(final String fileName) {

	    this.fileName = fileName;

    }
	
    @Override
    public void execute() {
        Parameters parameters = new Parameters();
        parameters.setValue(Parameters.Parameter.FILE_NAME, this.fileName);
        Slideshow slideShow = Slideshow.getInstance();
        AccessorFactory.getInstance().save(parameters, slideShow);
        slideShow.setCurrentSlideNumber(slideShow.getCurrentSlideNumber());
        //slideShow.draw();

    }
    
    // SAVE TO FILE NEEDS GETTERS
    public String getFileName(){
    	return fileName;
    }
}

package jabberpoint.model.accessor;

import jabberpoint.model.Slideshow;
import jabberpoint.model.util.Parameters;

/**
 * Interface that defines the contract of loading and saving {@link Slideshow} to a source
 * 
 * @author Ivo Willemsen
 */
public interface Accessor {

    /**
     * Method that saves a {@link Slideshow} to the source. When saving a slideshow, the parameter
     * Parameters.Parameter.SLIDESHOW_NAME should be provided with a name. A file with this names
     * will be created.
     *
     * @param parameters
     *            object of type {@link Parameters} that contain the parameters
     * @param slideShow
     *            the {@link Slideshow}
     */
    void save(Parameters parameters, Slideshow slideShow);

    /**
     * Retrieves the {@link Slideshow} from the source. When loading a slideshow, the parameter
     * Parameters.Parameter.SLIDESHOW_NAME should be provided with a name. A file with this names
     * will be looked for.
     * 
     * @param parameters
     *            object that contains the parameters that are used. When loading a
     *            slideshow, the name of the slideshow must be specified as a parameter
     * @return
     */
    Slideshow load(Parameters parameters);

}

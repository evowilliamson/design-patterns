package jabberpoint.model.accessor;

import jabberpoint.model.SlideShow;
import jabberpoint.model.util.Parameters;

/**
 * Interface that defines the contract of loading and saving {@link SlideShow} to a source
 */
public interface Accessor {

    /** Method that saves a {@link jabberpoint.model.SlideShow} to the source
     *
     * @param parameters object of type {@link Parameters} that contain the parameters
     * @param slideShow the {@link SlideShow}
     */
    void save(Parameters parameters, SlideShow slideShow);

    /**
     * Retrieves the {@link SlideShow} from the source
     * @param parameters object that contains the parameters that are used. When loading a
     *                   slideshow, the name of the slideshow must be specified as a parameter
     * @return
     */
    SlideShow load(Parameters parameters);

}

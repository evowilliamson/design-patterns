package jabberpoint.model.slide;

/**
 * Class that creates a new slide
 * @author Randy Pottgens
 */
public class SlideFactory {

	/**
	 * This method creates a new slide
	 * @param title the title of the slide
	 * @return the Slide object
	 */
	public static Slide createSlide(String title){
		return new Slide(title);
	}

}

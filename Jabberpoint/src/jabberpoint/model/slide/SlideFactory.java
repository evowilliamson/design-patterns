package jabberpoint.model.slide;

public class SlideFactory {
	public static Slide createSlide(String title){
		return new Slide(title);
	}
}

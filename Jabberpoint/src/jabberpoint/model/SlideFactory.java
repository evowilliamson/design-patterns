package jabberpoint.model;

public class SlideFactory {
	public static Slide createSlide(String title){
		return new Slide(title);
	}
}

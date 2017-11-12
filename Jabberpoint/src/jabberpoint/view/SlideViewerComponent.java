package jabberpoint.view;

import java.awt.*;

import javax.swing.*;

import jabberpoint.model.SlideShow;
import jabberpoint.model.old.PresentationOld;
import jabberpoint.model.old.SlideOld;

/** <p>SlideViewerComponent is een grafische component die Slides kan laten zien.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent {

	private Graphics graphics;

	private SlideOld slide; // de huidige slide
	private Font labelFont = null; // het font voor labels
	private SlideShow slideShow = null; // de presentatie
	private JFrame frame = null;

	private static final long serialVersionUID = 227L;

	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;

	public SlideViewerComponent(JFrame frame) {
		setBackground(BGCOLOR); 
		slideShow = SlideShow.getInstance();
		labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
		this.frame = frame;
	}

	public Dimension getPreferredSize() {
		return new Dimension(SlideOld.WIDTH, SlideOld.HEIGHT);
	}

	public void update(PresentationOld presentation, SlideOld data) {
		if (data == null) {
			repaint();
			return;
		}
		this.slide = data;
		repaint();
		frame.setTitle(presentation.getTitle());
	}

	public void bla(Graphics g) {
		Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
		slide.draw(g, area, this);
	}



	/**
	 * Update the graphics object. This object will be used later when the text items and
	 * bitmaps are printed on the screen.
	 * @param graphics
	 */
	public void paintComponent(Graphics graphics) {

		// Store refreshed graphics object as an attribute in "this" object
		this.graphics = graphics;

		// Initialize the graphics
		this.graphics.setColor(BGCOLOR);
		this.graphics.fillRect(0, 0, getSize().width, getSize().height);
		this.graphics.setFont(labelFont);
		this.graphics.setColor(COLOR);

	}

	/**
	 * calls the repaint method so that the graphics object is refreshed. Swing will call the
	 * {@link SlideViewerComponent#paintComponent(Graphics)} method
	 */
	public void initializeSlideGraphics() {

		repaint();

	}

	/**
	 * This method draws the slide number on the screen
	 * @param slideNumber the slide number
	 */
	public void drawCurrentSlideNumber(int slideNumber) {
		this.graphics.drawString("Slide " + (1 + slideNumber) + " of " +
				slideShow.getNumberOfSlides(), XPOS, YPOS);
	}

}

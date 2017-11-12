package jabberpoint.view;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import jabberpoint.model.Slideshow;
import jabberpoint.model.old.PresentationOld;
import jabberpoint.model.old.SlideItemOld;
import jabberpoint.model.old.SlideOld;
import jabberpoint.model.old.StyleOld;
import jabberpoint.model.old.TextItemOld;

/**
 * <p>
 * SlideViewerComponent is een grafische component die Slides kan laten zien.
 * </p>
 * 
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent {

    private static final long serialVersionUID = 227L;
    private static final Color BGCOLOR = Color.white;
    private static final Color COLOR = Color.black;
    private static final String FONTNAME = "Dialog";
    private static final int FONTSTYLE = Font.BOLD;
    private static final int FONTHEIGHT = 10;
    private static final int XPOS = 1100;
    private static final int YPOS = 20;
    private Graphics graphics;
    private Rectangle area;
    private int y;
    private float scale;
    private SlideOld slide; // de huidige slide
    private Font labelFont = null; // het font voor labels
    private Slideshow slideShow = null; // de presentatie
    private JFrame frame = null;

    public SlideViewerComponent(JFrame frame) {
        setBackground(BGCOLOR);
        slideShow = Slideshow.getInstance();
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

        slide.draw(g, area, this);
    }

    /**
     * Update the graphics object. This object will be used later when the text items and
     * bitmaps are printed on the screen.
     * 
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
        this.area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
        this.scale = getScale(area);
        this.y = area.y;

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
     * 
     * @param slideNumber
     *            the slide number
     */
    public void drawCurrentSlideNumber(int slideNumber) {

        this.graphics.drawString("Slide " + (1 + slideNumber) + " of " +
                slideShow.getNumberOfSlides(), XPOS, YPOS);

    }

    /**
     * Draws the title on the screen
     * 
     * @param title
     *            the title
     */
    public void drawTitle(String title) {

        SlideItemOld slideItem = new TextItemOld(1, title);
        StyleOld style = StyleOld.getStyle(slideItem.getLevel());
        slideItem.draw(area.x, y, scale, this.graphics, style, this);

    }

    /*
    private void drawText(String text, int x, int y) {

        if (text == null || text.length() == 0) {
            return;
        }
        List<TextLayout> layouts = getLayouts(g, myStyle, scale);
        Point pen = new Point(x + (int) (myStyle.indent * scale),
                y + (int) (myStyle.leading * scale));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(myStyle.color);
        Iterator<TextLayout> it = layouts.iterator();
        while (it.hasNext()) {
            TextLayout layout = it.next();
            pen.y += layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);
            pen.y += layout.getDescent();
        }

    }*/

	private List<TextLayout> getLayouts(Graphics g, StyleOld s, float scale) {
		List<TextLayout> layouts = new ArrayList<TextLayout>();
		AttributedString attrStr = getAttributedString(s, scale);
		Graphics2D g2d = (Graphics2D) g;
		FontRenderContext frc = g2d.getFontRenderContext();
		LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);

		int indent = 0;

		//float wrappingWidth = (SlideOld.WIDTH - s.indent) * scale;
		while (measurer.getPosition() < getText("vul text in").length()) {
			//TextLayout layout = measurer.nextLayout(wrappingWidth);
			//layouts.add(layout);
		}
		return layouts;
	}

	public AttributedString getAttributedString(StyleOld style, float scale) {
		AttributedString attrStr = new AttributedString(getText("vul text in"));
		attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0,
				"vul text in".length());
		return attrStr;
	}

	public String getText(String text) {
		return text == null ? "" : text;
	}

    private float getScale(Rectangle area) {

        return Math.min(
                ((float) area.width) / ((float) WIDTH),
                ((float) area.height) / ((float) HEIGHT));

    }

}

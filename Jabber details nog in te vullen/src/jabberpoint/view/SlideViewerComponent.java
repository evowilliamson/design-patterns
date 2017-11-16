package jabberpoint.view;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import jabberpoint.controller.MouseController;
import jabberpoint.model.Slideshow;
import jabberpoint.model.action.Action;
import jabberpoint.model.old.PresentationOld;
import jabberpoint.model.old.SlideOld;
import jabberpoint.model.old.StyleOld;
import jabberpoint.model.slideitems.BitmapItem;
import jabberpoint.model.slideitems.SlideItem;
import jabberpoint.model.slideitems.TextItem;
import jabberpoint.model.style.BitmapStyle;
import jabberpoint.model.style.Style;
import jabberpoint.model.style.StyleFactory;
import jabberpoint.model.style.TextStyle;

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
    private static final int TITLE_LEVEL = 1;
    
    
    private MouseController mouseController = new MouseController();
        
    private Graphics graphics;
    private Rectangle area;
    private int adjustableY;
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
    	this.addMouseListener(mouseController); // add mouse listener to the newly created Component
    	//this.addMouseMotionListener(mouseController);
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

        System.out.println("paintCommand");
        mouseController.clearList();
        // Store refreshed graphics object as an attribute in "this" object
        this.graphics = graphics;

        // Initialize the graphics
        this.graphics.setColor(BGCOLOR);
        this.graphics.fillRect(0, 0, getSize().width, getSize().height);
        this.graphics.setFont(labelFont);
        this.graphics.setColor(COLOR);
        this.area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
        this.scale = getScale(area);
        this.adjustableY = area.y;
        Slideshow slideshow = Slideshow.getInstance();
        //this.removeAll();
        //this.revalidate();
        slideshow.draw();

    }

    /**
     * calls the repaint method so that the graphics object is refreshed. Swing will call the
     * {@link SlideViewerComponent#paintComponent(Graphics)} method
     */
    public void initializeSlideGraphics() {

        System.out.println("repainting in component");
        //this.removeAll();
        //this.revalidate();
        repaint();

    }

    /**
     * This method draws the slide number on the screen
     *
     * @param slideNumber the slide number
     */
    public void drawCurrentSlideNumber(int slideNumber) {

        this.graphics.drawString("Slide " + (1 + slideNumber) + " of " + slideShow.getComponentCount(),
                XPOS, YPOS);

    }

    /**
     * Draws the title on the screen
     *
     * @param title the title
     */
    public void drawTitle(String title) {

        // Title is a text item with level 0
        TextItem textItem = new TextItem(TITLE_LEVEL, title);
        drawTextItem(textItem);
        
        //this.removeAll();
        //this.revalidate();
        //this.repaint();
    }

    private void registerEventHandling(SlideItem s, Rectangle r){
    	System.out.println("Registering event handling for SlideItem");
    	mouseController.addBoundingBox(r, s);
    	if (s.getActions().size() > 0){
    		System.out.println("SlideItem has actions, drawing Rectangle");
    		graphics.setColor(COLOR);
        	graphics.drawRect(r.x, r.y, r.width, r.height);
    	}
    }
    
    public void drawTextItem(TextItem textItem) {
    	System.out.println("Drawing TextItem");
        TextStyle style = StyleFactory.getTextStyle(textItem.getLevel());
        this.drawText(textItem, style, this.area.x, adjustableY);
        Rectangle r = this.getBoundingBox(textItem, scale, style);
        this.adjustableY += r.height;
        registerEventHandling(textItem, r);
    }

    public void drawBitmapItem(BitmapItem bitmapItem) {
    	System.out.println("Drawing BitmapItem");
        BitmapStyle style = StyleFactory.getBitmapStyle(bitmapItem.getLevel());
        this.drawBitmap(bitmapItem, style, this.area.x, adjustableY);
        Rectangle r = this.getBoundingBox(bitmapItem, scale, style);
        this.adjustableY += r.height;
        registerEventHandling(bitmapItem, r);
    }

    /**
     * This method draws the text on the screen
     *
     * @param textItem the text item to be drawn
     * @param style the style to be used
     * @param x the X-coordinate
     * @param y the Y-coordinate
     */
    private void drawText(TextItem textItem, TextStyle style, int x, int y) {

        if (textItem.getText() == null || textItem.getText().length() == 0) {
            return;
        }
        List<TextLayout> layouts = getLayouts(textItem.getText(), style, scale);
        Point pen = new Point(x + (int) (style.getIndent() * scale), y + (int) (style.getLeading() * scale));
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(style.getColor());
        Iterator<TextLayout> it = layouts.iterator();
        while (it.hasNext()) {
            TextLayout layout = it.next();
            pen.y += layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);
            pen.y += layout.getDescent();
        }

    }

    private void drawBitmap(final BitmapItem bitmapItem, final BitmapStyle style,
            final int x, final int y) {

        int width = x + (int) (style.getIndent() * scale);
        int height = y + (int) (style.getLeading() * scale);
        this.graphics.drawImage(bitmapItem.getBufferedImage(), width, height,
                (int) (bitmapItem.getBufferedImage().getWidth(this)*scale),
                (int) (bitmapItem.getBufferedImage().getHeight(this)*scale), this);

    }

    private List<TextLayout> getLayouts(final String text, final TextStyle style, final float scale) {

        List<TextLayout> layouts = new ArrayList<TextLayout>();
        AttributedString attrStr = getAttributedString(text, style, scale);
        Graphics2D g2d = (Graphics2D) graphics;
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
        float wrappingWidth = (SlideOld.WIDTH - style.getIndent()) * scale;
        while (measurer.getPosition() < getText(text).length()) {
            TextLayout layout = measurer.nextLayout(wrappingWidth);
            layouts.add(layout);
        }
        return layouts;

    }

    public AttributedString getAttributedString(final String text, final TextStyle style, final float scale) {

        AttributedString attrStr = new AttributedString(getText(text));
        attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
        return attrStr;

    }

    /**
     * Null-safe method to format text
     *
     * @param text the text
     * @return the text or an empty string in case null
     */
    public String getText(String text) {

        return text == null ? "" : text;

    }

    /**
     * Calculates the scale of the rectangle
     *
     * @param area the area of the rectangle
     * @return the calculated scale
     */
    private float getScale(Rectangle area) {

        return Math.min(((float) area.width) /
                ((float) SlideViewerFrame.WIDTH_SCREEN), ((float) area.height) / ((float) SlideViewerFrame.HEIGHT_SCREEN));

    }

    private Rectangle getBoundingBox(TextItem textItem, float scale, TextStyle style) {
        List<TextLayout> layouts = getLayouts(textItem.getText(), style, scale);
        int xsize = 0, ysize = (int) (style.getLeading() * scale);
        Iterator<TextLayout> iterator = layouts.iterator();
        while (iterator.hasNext()) {
            TextLayout layout = iterator.next();
            Rectangle2D bounds = layout.getBounds();
            // fsdfklkdf ldkf d
            //int a = 0;
            if (bounds.getWidth() > xsize) {
                xsize = (int) bounds.getWidth();
            }
            if (bounds.getHeight() > 0) {
                ysize += bounds.getHeight();
            }
            ysize += layout.getLeading() + layout.getDescent();
        }
        return new Rectangle((int) (style.getIndent() * scale), adjustableY, xsize, ysize);
    }

    public Rectangle getBoundingBox(BitmapItem bitmapItem, float scale, BitmapStyle style) {
        return new Rectangle((int) (style.getIndent() * scale), adjustableY, (int) (bitmapItem.getBufferedImage().getWidth(this) * scale),
                ((int) (style.getLeading() * scale)) + (int) (bitmapItem.getBufferedImage().getHeight(this) * scale));

    }

}

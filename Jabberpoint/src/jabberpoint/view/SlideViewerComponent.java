package jabberpoint.view;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import jabberpoint.controller.MouseController;
import jabberpoint.model.Slideshow;
import jabberpoint.model.Theme;
import jabberpoint.model.old.SlideOld;
import jabberpoint.model.slideitems.ActionItemDecorator;
import jabberpoint.model.slideitems.BitmapItem;
import jabberpoint.model.slideitems.TextItem;
import jabberpoint.model.style.BitmapStyle;
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
 * @version 1.7 2017/11/14 Randy Pottgens, Ivo Willemsen
 *
 */

public class SlideViewerComponent extends JComponent {

    private static final long serialVersionUID = 227L;
    private static final Color COLOR_BLACK = Color.black;
    private static final String FONTNAME = "Dialog";
    private static final int FONTSTYLE = Font.BOLD;
    private static final int FONTHEIGHT = 10;
    private static final int XPOS = 1100;
    private static final int YPOS = 20;

    private Graphics graphics;
    private Rectangle area;
    private int adjustableY;
    private float scale;
    private Font labelFont;

    private Theme theme;

    private Rectangle lastRectangle; // used for drawing and adding event handling for action decorators
    private MouseController mouseController; // the mouse controller needs input from the view so it can handle mouse clicks.

    // Mouse events start from the view and deliver the position data to the controller.

    private SlideViewerComponent() {
        this.labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
    }

    protected SlideViewerComponent(MouseController mouseController) {
        this();
        this.mouseController = mouseController;
        this.addMouseListener(mouseController);
        this.addMouseMotionListener(mouseController);
    }

    /**
     * Update the this.graphics object. This object will be used later when the text items and
     * bitmaps are printed on the screen.
     *
     * @param graphics
     *            the graphics object onto which elements are drawn
     */
    public void paintComponent(Graphics graphics) {
        // Clear the list in MouseController. This is needed every time a new slide will be drawn.
        // this removes old BoundingBoxes from the list.
        mouseController.clearList();
        this.setGraphics(graphics);

        /*
         * The following code should be removed. Drawing of the slideshow should be triggers by the controller
         * after the repaint on the frame is done. It should be able to draw the objects because the Graphics
         * objects has been captured above.
         */
        Slideshow slideshow = Slideshow.getInstance();
        if (slideshow != null) {
            slideshow.draw();
        }

    }

    /**
     * This method sets the details of the graphics object
     * 
     * @param graphics
     */
    private void setGraphics(Graphics graphics) {

        // Store refreshed graphics object as an attribute in "this" object
        this.graphics = graphics;
        // Initialize the graphics
        this.graphics.setColor(ThemeColorMapping.getColor(this.theme));
        this.graphics.fillRect(0, 0, getSize().width, getSize().height);
        this.graphics.setFont(labelFont);
        this.area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
        this.scale = getScale(area);
        this.adjustableY = area.y;

    }

    /**
     * This method draws the slide number on the screen
     *
     * @param currentSlideNumber
     *            the current slide number
     * @param totalSlides
     *            the total number of slides
     */
    public void drawCurrentSlideNumber(int currentSlideNumber, int totalSlides) {

        this.graphics.setColor(COLOR_BLACK);
        this.graphics.drawString("Slide " + (1 + currentSlideNumber) + " of " + totalSlides,
                XPOS, YPOS);

    }

    /**
     * This method draws the text on the graphics object
     *
     * @param textItem
     *            the text item to be drawn
     * @param style
     *            the style to be used
     */
    public void drawText(TextItem textItem, TextStyle style) {

        if (textItem.getText() == null || textItem.getText().length() == 0) {
            return;
        }
        List<TextLayout> layouts = getLayouts(textItem.getText(), style, scale);
        Point pen = new Point(this.area.x + (int) (style.getIndent() * scale),
                this.adjustableY + (int) (style.getLeading() * scale));
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(style.getColor());
        Iterator<TextLayout> it = layouts.iterator();
        while (it.hasNext()) {
            TextLayout layout = it.next();
            pen.y += layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);
            pen.y += layout.getDescent();
        }
        this.adjustableY += this.getBoundingBox(textItem, scale, style).height;

    }

    /**
     * Draws the bitmap on the graphics object
     * 
     * @param bitmapItem
     *            the bitmap item
     * @param style
     *            the style
     */
    public void drawBitmap(final BitmapItem bitmapItem, final BitmapStyle style) {

        int width = this.area.x + (int) (style.getIndent() * scale);
        int height = adjustableY + (int) (style.getLeading() * scale);
        this.graphics.drawImage(bitmapItem.getBufferedImage(), width, height,
                (int) (bitmapItem.getBufferedImage().getWidth(this) * scale),
                (int) (bitmapItem.getBufferedImage().getHeight(this) * scale), this);
        this.adjustableY += this.getBoundingBox(bitmapItem, scale, style).height;

    }

    /**
     * Get the layouts for wrapping text lines
     * 
     * @param text
     *            the text
     * @param style
     *            the style of the text item
     * @param scale
     *            the scale
     * @return the layouts
     */
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

    private AttributedString getAttributedString(final String text, final TextStyle style, final float scale) {

        AttributedString attrStr = new AttributedString(getText(text));
        attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
        return attrStr;

    }

    /**
     * Null-safe method to format text
     *
     * @param text
     *            the text
     * @return the text or an empty string in case null
     */
    private String getText(String text) {

        return text == null ? "" : text;

    }

    /**
     * Calculates the scale of the rectangle
     *
     * @param area
     *            the area of the rectangle
     * @return the calculated scale
     */
    private float getScale(Rectangle area) {

        return Math.min(((float) area.width) /
                ((float) SlideViewerFrame.WIDTH_SCREEN), ((float) area.height) / ((float) SlideViewerFrame.HEIGHT_SCREEN));

    }

    /**
     * Calculate and retrieve the bounding box of the textitem
     * 
     * @param textItem
     *            the text item
     * @param scale
     *            the scale
     * @param style
     *            the text style
     * @return
     */
    private Rectangle getBoundingBox(TextItem textItem, float scale, TextStyle style) {
        List<TextLayout> layouts = getLayouts(textItem.getText(), style, scale);
        int xsize = 0, ysize = (int) (style.getLeading() * scale);
        Iterator<TextLayout> iterator = layouts.iterator();
        while (iterator.hasNext()) {
            TextLayout layout = iterator.next();
            Rectangle2D bounds = layout.getBounds();
            if (bounds.getWidth() > xsize) {
                xsize = (int) bounds.getWidth();
            }
            if (bounds.getHeight() > 0) {
                ysize += bounds.getHeight();
            }
            ysize += layout.getLeading() + layout.getDescent();
        }
        lastRectangle = new Rectangle((int) (style.getIndent() * scale), adjustableY, xsize, ysize);
        return lastRectangle;
    }

    /**
     * Calculate the bounding box of the bitmap style
     * 
     * @param bitmapItem
     *            the bitmap item
     * @param scale
     *            the scale
     * @param style
     *            the style of the bitmap
     * @return
     */
    private Rectangle getBoundingBox(BitmapItem bitmapItem, float scale, BitmapStyle style) {
        lastRectangle = new Rectangle((int) (style.getIndent() * scale), adjustableY,
                (int) (bitmapItem.getBufferedImage().getWidth(this) * scale),
                ((int) (style.getLeading() * scale)) + (int) (bitmapItem.getBufferedImage().getHeight(this) * scale));
        return lastRectangle;

    }

    /**
     * Registers the SlideItem in the event handling MouseController and draws a rectangle if the SlideItem has actions
     * 
     * @param s
     *            , the SlideItem object
     * @param r
     *            , the corresponding Rectangle
     */
    public void registerEventHandling(ActionItemDecorator a) {
        System.out.println("Registering event handling for Decorator");
        // getting the last generated rectangle
        Rectangle r = lastRectangle;
        // add the Action decorator to the mouse controller together with the rectangle
        if (mouseController != null) {
            mouseController.addBoundingBox(r, a);
            // graphics.setColor(COLOR);
            // draw rectangle
            graphics.drawRect(r.x, r.y, r.width, r.height);
        }
        else {
            throw new NullPointerException("The mousController is not set.");
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(SlideOld.WIDTH, SlideOld.HEIGHT);
    }

    // Getters/Setters:

    public void setTheme(final Theme theme) {
        this.theme = theme;
    }

}

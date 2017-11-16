package jabberpoint.view.drawingdriver;

import jabberpoint.model.Theme;
import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.model.slideitems.ActionItemDecorator;
import jabberpoint.model.slideitems.BitmapItem;
import jabberpoint.model.slideitems.SlideItem;
import jabberpoint.model.slideitems.TextItem;
import jabberpoint.model.style.BitmapStyle;
import jabberpoint.model.style.StyleFactory;
import jabberpoint.model.style.TextStyle;
import jabberpoint.view.SlideViewerComponent;
import jabberpoint.view.SlideViewerFrame;

/**
 * Implements the DrawingDriver contract for Swing API
 */
public class SwingDrawingDriver implements DrawingDriver {

    private SlideViewerFrame slideViewerFrame;
    private SlideViewerComponent slideViewerComponent;

    private static final int TITLE_LEVEL = 1;

    public SwingDrawingDriver() {
    }

    @Override
    public void initialize(final String title) {
        this.slideViewerFrame = new SlideViewerFrame(title);
        this.slideViewerComponent = new SlideViewerComponent();
        this.slideViewerFrame.initialize(this.slideViewerComponent);
    }

    @Override
    public void applyTheme(final Theme theme) {
        this.slideViewerComponent.setTheme(theme);

    }

    @Override
    public void drawCurrentSlideNumber(final int currentSlideNumber) {
        this.slideViewerComponent.drawCurrentSlideNumber(currentSlideNumber);
    }

    @Override
    public void drawTitle(final String title) {
        // Title is a text item with level 0
        TextItem textItem = new TextItem(TITLE_LEVEL, title);
        drawTextItem(textItem);
    }

    @Override
    public void drawTextItem(final TextItem textItem) {
        TextStyle style = StyleFactory.getTextStyle(textItem.getLevel());
        this.slideViewerComponent.drawText(textItem, style);

    }

    @Override
    public void drawBitmapItem(final BitmapItem bitmapItem) {
        BitmapStyle style = StyleFactory.getBitmapStyle(bitmapItem.getLevel());
        this.slideViewerComponent.drawBitmap(bitmapItem, style);

    }

    @Override
    public void drawActionItemDecorator(final ActionItemDecorator actionItemDecorator) {
        // TODO Code plaatsen voor de aansturing van printen van border in slideViewerComponent
    	SlideItem decoratedItem = actionItemDecorator.getSlideItem();
    	decoratedItem.draw(); // draw decorated item
    	this.slideViewerComponent.registerEventHandling(actionItemDecorator);
    }

}

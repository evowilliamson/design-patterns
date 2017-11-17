package jabberpoint.view.drawingdriver;

import jabberpoint.controller.ControllerFactory;
import jabberpoint.controller.MouseController;
import jabberpoint.model.Theme;
import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.model.slideitems.ActionItemDecorator;
import jabberpoint.model.slideitems.BitmapItem;
import jabberpoint.model.slideitems.SlideItem;
import jabberpoint.model.slideitems.SlideItemFactory;
import jabberpoint.model.slideitems.TextItem;
import jabberpoint.model.style.BitmapStyle;
import jabberpoint.model.style.TextStyle;
import jabberpoint.view.SlideViewerComponent;
import jabberpoint.view.SlideViewerFactory;
import jabberpoint.view.SlideViewerFrame;

/**
 * Implements the DrawingDriver contract for Swing API
 */
public class SwingDrawingDriver implements DrawingDriver {

    private static final int TITLE_LEVEL = 1;
    private SlideViewerFrame slideViewerFrame;
    private SlideViewerComponent slideViewerComponent;

    public SwingDrawingDriver() {
    }

    //TODO remove!
    public void setMouseController(MouseController mouseController) {
        this.setMouseController(mouseController);
    }

    @Override
    public void initialize(final String title) {
        this.slideViewerFrame = SlideViewerFactory.createSlideViewerFrame(title);
        this.slideViewerComponent = SlideViewerFactory.createSlideViewerComponent(ControllerFactory.createMouseController());
        this.slideViewerFrame.initialize(this.slideViewerComponent);
    }

    @Override
    public void applyTheme(final Theme theme) {
        this.slideViewerComponent.setTheme(theme);

    }

    @Override
    public void drawSlideNumberInfo(
            final int currentSlideNumber,
            final int totalSlides) {
        this.slideViewerComponent.drawCurrentSlideNumber(currentSlideNumber, totalSlides);
    }

    @Override
    public void drawTitle(final String title) {
        // Title is a text item with level 0
        TextItem textItem = SlideItemFactory.createTextItem(TITLE_LEVEL, title);
        drawTextItem(textItem);
    }

    @Override
    public void drawTextItem(final TextItem textItem) {
        // TODO change to Style
        TextStyle style = (TextStyle) textItem.getStyle();
        this.slideViewerComponent.drawText(textItem, style);

    }

    @Override
    public void drawBitmapItem(final BitmapItem bitmapItem) {
        // TODO change to Style
        BitmapStyle style = (BitmapStyle) bitmapItem.getStyle();
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

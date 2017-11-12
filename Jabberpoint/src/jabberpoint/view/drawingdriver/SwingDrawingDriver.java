package jabberpoint.view.drawingdriver;

import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.view.SlideViewerComponent;
import jabberpoint.view.SlideViewerFrame;

/**
 * Implements the DrawingDriver contract for Swing API
 */
public class SwingDrawingDriver implements DrawingDriver {

    private SlideViewerFrame slideViewerFrame;
    private SlideViewerComponent slideViewerComponent;

    public SwingDrawingDriver() {
    }

    @Override
    public void initialize(String title) {
        this.slideViewerFrame = new SlideViewerFrame(title);
        this.slideViewerComponent = new SlideViewerComponent(this.slideViewerFrame);
        this.slideViewerFrame.initialize(this.slideViewerComponent);
    }

    @Override
    public void drawCurrentSlideNumber(final int slideNumber) {
        this.slideViewerComponent.drawCurrentSlideNumber(slideNumber);
    }

    @Override
    public void prepareSlide() {
        this.slideViewerComponent.initializeSlideGraphics();
    }

    @Override
    public void drawSlideItem() {

    }

}

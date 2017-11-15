package jabberpoint.view.drawingdriver;

import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.model.exception.NotImplementedExcpeption;
import jabberpoint.model.slideitems.BitmapItem;
import jabberpoint.model.slideitems.TextItem;

/**
 * Implements the DrawingDriver contract for JavaFX API. Added as an example
 * to clarify the extendable character of {@link DrawingDriver}.
 */
public class JavaFXDrawingDriver implements DrawingDriver {

    @Override
    public void initialize(final String title) {
        throw new NotImplementedExcpeption("initialize(String) of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawCurrentSlideNumber(final int currentSlideNumber) {
        throw new NotImplementedExcpeption("drawCurrentSlideNumber(int) of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawTitle(final String title) {
        throw new NotImplementedExcpeption("drawTitle(String) of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawTextItem(final TextItem textItem) {
        throw new NotImplementedExcpeption("drawTextItem(TextItem) of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawBitmapItem(final BitmapItem bitmapItem) {
        throw new NotImplementedExcpeption("drawBitmapItem(BitmapItem) of " + this.getClass() + " not implemented");
    }

    @Override
    public void initializeSlide() {
        throw new NotImplementedExcpeption("initializeSlide() of " + this.getClass() + " not implemented");
    }

}

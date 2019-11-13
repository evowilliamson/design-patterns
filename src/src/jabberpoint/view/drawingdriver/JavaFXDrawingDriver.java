package jabberpoint.view.drawingdriver;

import jabberpoint.model.Theme;
import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.model.exception.ExceptionFactory;
import jabberpoint.model.exception.NotImplementedException;
import jabberpoint.model.slideitems.ActionItemDecorator;
import jabberpoint.model.slideitems.BitmapItem;
import jabberpoint.model.slideitems.TextItem;

/**
 * Implements the DrawingDriver contract for JavaFX API. Added as an example
 * to clarify the extendable character of {@link DrawingDriver}.
 */
public class JavaFXDrawingDriver implements DrawingDriver {

    @Override
    public void initialize(final String title) {
        throw ExceptionFactory.createNotImplementedException("initialize(String) of " + this.getClass() + " not implemented");
    }

    @Override
    public void applyTheme(final Theme theme) {
        throw ExceptionFactory.createNotImplementedException("applyTheme(Theme) of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawSlideNumberInfo(final int currentSlideNumber, final int totalSlides) {
        throw ExceptionFactory.createNotImplementedException("drawSlideNumberInfo(int, int) of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawTitle(final String title) {
        throw ExceptionFactory.createNotImplementedException("drawTitle(String) of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawTextItem(final TextItem textItem) {
        throw ExceptionFactory.createNotImplementedException("drawTextItem(TextItem) of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawBitmapItem(final BitmapItem bitmapItem) {
        throw ExceptionFactory.createNotImplementedException("drawBitmapItem(BitmapItem) of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawActionItemDecorator(final ActionItemDecorator actionItemDecorator) {
        throw ExceptionFactory.createNotImplementedException("drawActionItemDecorator(ActionItemDecorator) of " + this.getClass() + " not implemented");
    }

}

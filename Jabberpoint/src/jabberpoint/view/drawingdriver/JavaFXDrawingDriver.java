package jabberpoint.view.drawingdriver;

import jabberpoint.model.Theme;
import jabberpoint.model.drawingdriver.DrawingDriver;
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
        throw new NotImplementedException("initialize(String) of " + this.getClass() + " not implemented");
    }

    @Override
    public void applyTheme(final Theme theme) {
        throw new NotImplementedException("applyTheme(Theme) of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawSlideNumberInfo(final int currentSlideNumber, final int totalSlides) {
        throw new NotImplementedException("drawSlideNumberInfo(int, int) of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawTitle(final String title) {
        throw new NotImplementedException("drawTitle(String) of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawTextItem(final TextItem textItem) {
        throw new NotImplementedException("drawTextItem(TextItem) of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawBitmapItem(final BitmapItem bitmapItem) {
        throw new NotImplementedException("drawBitmapItem(BitmapItem) of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawActionItemDecorator(final ActionItemDecorator actionItemDecorator) {
        throw new NotImplementedException("drawActionItemDecorator(ActionItemDecorator) of " + this.getClass() + " not implemented");
    }

}

package jabberpoint.view.drawingdriver;

import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.model.exception.NotImplementedExcpeption;

/**
 * Implements the DrawingDriver contract for JavaFX API. Added as an example
 * of to clarify the purpose of {@link DrawingDriver}.
 */
public class JavaFXDrawingDriver implements DrawingDriver {

    @Override
    public void prepareSlide() {
        throw new NotImplementedExcpeption("drawSlide() of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawSlideItem() {
        throw new NotImplementedExcpeption("drawSlideItem() of " + this.getClass() + " not implemented");
    }

    @Override
    public void initialize(String title) {
        throw new NotImplementedExcpeption("initialize() of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawCurrentSlideNumber(final int slideNumber) {
        throw new NotImplementedExcpeption("drawCurrentSlideNumber() of " + this.getClass() + " not implemented");
    }

}

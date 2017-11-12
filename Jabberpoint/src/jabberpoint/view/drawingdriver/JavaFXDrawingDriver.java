package jabberpoint.view.drawingdriver;

import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.model.exception.NotImplementedExcpeption;

/**
 * Implements the DrawingDriver contract for JavaFX API. Added as an example
 * of to clarify the purpose of {@link DrawingDriver}.
 */
public class JavaFXDrawingDriver implements DrawingDriver {

    @Override
    public void drawSlideItem() {
        throw new NotImplementedExcpeption("drawSlideItem() of " + this.getClass() + " not implemented");
    }

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
    public void initializeSlide() {
        throw new NotImplementedExcpeption("initializeSlide() of " + this.getClass() + " not implemented");
    }

}

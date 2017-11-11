package jabberpoint.view.drawingdriver;

import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.model.exception.NotImplementedExcpeption;

/**
 * Implements the DrawingDriver contract for JavaFX API. Added as an example
 * of to clarify the purpose of {@link DrawingDriver}.
 */
public class JavaFXDrawingDriver implements DrawingDriver {

    @Override
    public void drawSlide() {
        throw new NotImplementedExcpeption("drawSlide() of " + this.getClass() + " not implemented");
    }

    @Override
    public void drawSlideItem() {
        throw new NotImplementedExcpeption("drawSlideItem() of " + this.getClass() + " not implemented");
    }

}

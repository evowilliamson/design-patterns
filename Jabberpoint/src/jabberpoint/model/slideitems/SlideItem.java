package jabberpoint.model.slideitems;

import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.view.drawingdriver.DrawingDriverFactory;

public abstract class SlideItem {

    /**
     * This method draws the slide item through the {@link DrawingDriver}
     */
    public void draw() {

        DrawingDriver drawingDriver = DrawingDriverFactory.getInstance();
        drawingDriver.drawSlideItem();

    }

}

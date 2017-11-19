package jabberpoint.controller;

import java.awt.*;

import jabberpoint.model.slideitems.ActionItemDecorator;
import jabberpoint.view.SlideViewerFrame;

/**
 * Factory class that creates the controller factories
 * 
 * @author Randy Pottgens
 */
public class ControllerFactory {

    /**
     * Creates the MouseController
     * 
     * @param frame
     *            the frame to which the MouseController will be attached
     * @return the MouseController
     */
    public static MouseController createMouseController(SlideViewerFrame frame) {
        return new MouseController(frame);
    }

    /**
     * This method creates the KeyController object
     * 
     * @param frame
     *            the frame to which the KeyController will be attached
     * @return the KeyController object
     */
    public static KeyController createKeyController(SlideViewerFrame frame) {
        return new KeyController(frame);
    }

    /**
     * Creates the MenuController
     * 
     * @param frame
     *            the frame to which the MenuController will be attached
     * @return the MenuController object
     */
    public static MenuController createMenuController(SlideViewerFrame frame) {
        return new MenuController(frame);
    }

    /**
     *
     * @param r
     *            the {@link Rectangle}
     * @param a
     *            the {@link ActionItemDecorator}
     * @return an object of type {@link BoundingBox}
     */
    protected static BoundingBox createBoundingBox(Rectangle r, ActionItemDecorator a) {
        return new BoundingBox(r, a);
    }
}

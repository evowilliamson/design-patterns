package jabberpoint.model.slideitems;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jabberpoint.view.drawingdriver.DrawingDriverFactory;

public class BitmapItem extends SlideItem {

    private BufferedImage bufferedImage;
    private String fileName;

    public BitmapItem(final int level, String fileName) {
        super(level);
        this.fileName = fileName;
        try {
            bufferedImage = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.err.println("File " + fileName + " not found");
        }
    }

    @Override
    public void draw() {
        DrawingDriverFactory.getInstance().drawBitmapItem(this);
    }

    // Getters/Setters:
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
    
    public String getFileName(){
    	return fileName;
    }

}

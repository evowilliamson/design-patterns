package jabberpoint.model.slideitems;

public class BitmapItem extends SlideItem {

    private String fileName;

    public BitmapItem(final int level, String fileName) {
        super(level);
        this.fileName = fileName;
    }

    @Override
    public void draw() {
        super.draw();
    }
}

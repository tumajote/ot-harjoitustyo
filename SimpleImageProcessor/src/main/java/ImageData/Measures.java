package ImageData;

import javafx.scene.image.Image;

public class Measures {

    Image image;
    int width;
    int height;
    String imageMeasures;

    public Measures() {
        this.height = 0;
        this.width = 0;
        imageMeasures = "width: " + this.width
                + " x height: " + this.height;
    }

    public void setImage(Image image) {
        this.image = image;
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
    }

    public String getImageMeasures() {
        imageMeasures = "width: " + this.width
                + " x height: " + this.height;
        return imageMeasures;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}

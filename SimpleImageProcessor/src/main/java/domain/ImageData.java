package domain;

import domain.methods.Method;
import java.util.LinkedList;
import javafx.scene.image.Image;
import org.opencv.core.Mat;

public class ImageData {

    Image image;
    int width;
    int height;
    String imageMeasures;
    Mat mat;
    Image histogram;
    ImageUtils imageUtils;
    LinkedList<Mat> history;

    public ImageData() {
        imageUtils = new ImageUtils();
        history = new LinkedList();
        this.height = 0;
        this.width = 0;

        imageMeasures = "width: " + this.width
                + " x height: " + this.height;
    }

    public void setMatAndUpdateImage(Mat mat) {
        if (!mat.empty()) {
            history.add(mat);
        }
        this.mat = mat;
        image = this.imageUtils.matToImage(mat);
        this.setImage(image);
        this.calculateHistogram();

    }

    public void setImage(Image image) {
        this.image = image;
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
    }

    public Image getImage() {
        return image;
    }

    public String getImageMeasures() {
        imageMeasures = "width: " + this.width
                + " x height: " + this.height;
        return imageMeasures;
    }

    public Image getHistogram() {
        return histogram;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Mat getMat() {
        return this.mat;
    }

    public void useMethod(Method method) {
        this.setMatAndUpdateImage(method.process(this.mat));
    }

    public void calculateHistogram() {
        Histogram histogram = new Histogram();
        this.histogram = imageUtils.matToImage(histogram.createHistogram(this.mat));
    }

}

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
    Mat originalMat;
    Mat adjustedMat;
    Image histogram;
    ImageUtils imageUtils;
    LinkedList<Mat> history;
    Boolean imageExists;

    public ImageData() {
        this.imageExists = false;
        imageUtils = new ImageUtils();
        history = new LinkedList();
        this.height = 0;
        this.width = 0;

        imageMeasures = "width: " + this.width
                + " x height: " + this.height;
    }

    public void setMat(Mat mat) {
        imageExists = true;
        this.originalMat = mat;
        this.adjustedMat = mat;
        this.updateMat();
    }

    public void updateMat() {
        if (!adjustedMat.empty()) {
            history.add(adjustedMat);
        }
        image = this.imageUtils.matToImage(adjustedMat);
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
        this.calculateHistogram();
    }

    public Image getImage() {
        return image;
    }
    public boolean exists(){
        return imageExists;
    }

    public String getImageMeasures() {
        imageMeasures = "width: " + this.width
                + " x height: " + this.height;
        return imageMeasures;
    }

    public Image getHistogram() {
        return histogram;
    }

    public Mat getMat() {
        return this.originalMat;
    }

    public void useMethod(Method method) {
        this.adjustedMat = method.process(this.originalMat);
        this.updateMat();
    }

    public void calculateHistogram() {
        Histogram hs = new Histogram();
        this.histogram = imageUtils.matToImage(hs.createHistogram(this.adjustedMat));
    }

}

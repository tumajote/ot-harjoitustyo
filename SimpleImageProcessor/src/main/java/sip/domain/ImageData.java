package sip.domain;

import sip.domain.methods.Method;
import javafx.scene.image.Image;
import org.opencv.core.Mat;

public class ImageData {

    Image image;
    String imageMeasures;
    Mat originalMat;
    Mat transformedMat;
    Mat processedMat;
    Image histogram;
    Boolean imageExists;

    public ImageData() {
        this.imageExists = false;
        imageMeasures = "width: 0 x height: 0";
    }

    public void setMat(Mat mat) {
        imageExists = true;
        this.originalMat = mat;
        this.transformedMat = mat;
        this.processedMat = mat;
        this.updateMat();
    }

    public void updateMat() {
//        if (!adjustedMat.empty()) {
//            history.add(adjustedMat);
//        }
        image = ImageUtils.matToImage(this.processedMat);
        imageMeasures = "width: " + this.processedMat.width()
                + " x height: " + this.processedMat.height();
        this.calculateHistogram();
    }

    public Image getImage() {
        return image;
    }

    public boolean exists() {
        return imageExists;
    }

    public String getImageMeasures() {
        return imageMeasures;
    }

    public Image getHistogram() {
        return histogram;
    }

    public Mat getMat() {
        return this.originalMat;
    }

    public Mat getProcessedMat() {
        return processedMat;
    }

    public void useTransformingMethod(Method method) {
        this.processedMat = method.process(this.processedMat);
        this.transformedMat = method.process(this.transformedMat);
        this.updateMat();
    }

    public void useProcessingMethod(Method method) {
        this.processedMat = method.process(this.transformedMat);
        this.updateMat();
    }

    public void calculateHistogram() {
        this.histogram = ImageUtils.matToImage(Histogram.createHistogram(this.processedMat));
    }

}

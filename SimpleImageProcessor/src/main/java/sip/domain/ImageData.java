package sip.domain;

import javafx.scene.image.Image;
import org.opencv.core.Mat;
import sip.domain.methods.BrightnessAndContrast;
import sip.domain.methods.GrayScale;
import sip.domain.methods.Rotate;
import sip.domain.methods.Sharpness;

/**
 *
 * Class for maintaining the state the of the loaded image
 */
public class ImageData {

    //Data
    Image image;
    String imageMeasures;
    Mat originalMat;
    Mat transformedMat;
    Mat processedMat;
    Image histogram;
    Boolean imageExists;

    //Methods
    Rotate rotate;
    GrayScale grayScale;
    BrightnessAndContrast brightnessAndContrast;
    Sharpness sharpness;

    /**
     *
     */
    public ImageData() {
        this.imageExists = false;
        imageMeasures = "width: 0 x height: 0";
        rotate = new Rotate();
        grayScale = new GrayScale();
        brightnessAndContrast = new BrightnessAndContrast();
        sharpness = new Sharpness();

    }

    /**
     * Sets the original Mat object
     *
     * @param mat new image in a MAt format
     */
    public void setMat(Mat mat) {
        imageExists = true;
        this.originalMat = mat;
        this.transformedMat = mat;
        this.processedMat = mat;
        this.updateMat();
    }

    /**
     * Updates the processed image and the histogram
     */
    public void updateMat() {
//        if (!adjustedMat.empty()) {
//            history.add(adjustedMat);
//        }
        image = ImageUtils.matToImage(this.processedMat);
        imageMeasures = "width: " + this.processedMat.width()
                + " x height: " + this.processedMat.height();
        this.calculateHistogram();
    }

    /**
     * Returns the processed Mat object as an Image object
     *
     * @return Returns the loaded/processed Image if it exists
     */
    public Image getImage() {
        if (exists()) {
            return image;
        }
        return null;
    }

    /**
     * Checks whether an image is loaded
     *
     * @return Returns true if an image is loaded
     */
    public boolean exists() {
        return imageExists;
    }

    /**
     * Returns the measures of a loaded image in a string format
     *
     * @return A string that contains the width and height of the loaded picture
     * or 0 X 0 if there is no image
     */
    public String getImageMeasures() {
        if (exists()) {
            return imageMeasures;
        } else {
            return "width: 0 x height: 0";
        }
    }

    /**
     *
     * @return
     */
    public Image getHistogram() {
        return histogram;
    }

    /**
     * Returns the unprocessed Mat object of the loaded image
     *
     * @return The original Mat object
     */
    public Mat getOriginaltMat() {
        return this.originalMat;
    }

    /**
     * Returns the processed Mat object of the loaded image
     *
     * @return The processed Mat object
     */
    public Mat getProcessedMat() {
        return processedMat;
    }

    /**
     * Applies the rotate method. Saves the transformed Mat object to the
     * transformedMat and processedMat variables.
     */
    public void rotate() {
        this.processedMat = rotate.process(this.processedMat);
        this.transformedMat = rotate.process(this.transformedMat);
        this.updateMat();
    }

    /**
     * Sets the grayscale conversion variable
     *
     * @param grayscale true if grayscale
     */
    public void setGrayScale(Boolean grayscale) {
        this.grayScale.setGrayScale(grayscale);
    }

    /**
     * Sets the beta value for brightness adjustment
     *
     * @param beta is added to the pixel values
     */
    public void setBrigthnessValue(double beta) {
        this.brightnessAndContrast.setBeta(beta);
    }

    /**
     * Sets the alpha value for contrast adjustment
     *
     * @param alpha the pixel values are multiplied by alpha
     */
    public void setContrastValue(double alpha) {
        this.brightnessAndContrast.setAlpha(alpha);
    }

    /**
     * Sets the strength of the sharpness adjustment
     *
     * @param strength is the times the Laplacian unsharp mask is applied.
     */
    public void setSharpnessValue(double strength) {
        sharpness.setStrength(strength);
    }

    /**
     * Applies all the processing methods with their current parameters to the
     * loaded image. Saves the processed Mat object to the processedMat
     * variable.
     *
     */
    public void process() {
        this.processedMat = grayScale.process(this.transformedMat);
        this.processedMat = brightnessAndContrast.process(this.processedMat);
        this.processedMat = sharpness.process(processedMat);
        this.updateMat();
    }

    /**
     * Calculates a histogram from a Mat object stored in the the processedMat
     * variable. The histogram is different depending if the picture is
     * grayscale. Transforms the histogram into an Image object and stores it
     * into the histogram variable.
     */
    public void calculateHistogram() {
        if (grayScale.getGrayScale()) {
            this.histogram = ImageUtils.matToImage(Histogram.calculateGrayScale(this.processedMat));
        } else {
            this.histogram = ImageUtils.matToImage(Histogram.calculate(this.processedMat));
        }

    }

    /**
     * Resets all the adjustments made to the mat
     */
    public void resetAll() {
        this.setBrigthnessValue(0.0);
        this.setContrastValue(1.0);
        this.setSharpnessValue(0.0);
        this.setGrayScale(false);
        setMat(originalMat);
        updateMat();
    }

}

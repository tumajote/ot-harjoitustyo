package sip.domain.methods;

import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 *
 * A Class that applies brightness and contrast adjustments to a Mat object
 */
public class BrightnessAndContrast implements Method {

    private Double alpha;
    private Double beta;

    /**
     *
     */
    public BrightnessAndContrast() {
        this.beta = 0.0;
        this.alpha = 1.0;
    }

    /**
     * Sets the alpha value for contrast adjustment
     *
     * @param alpha the pixel values are multiplied by alpha
     */
    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    /**
     * Sets the beta value for brightness adjustment
     *
     * @param beta is added to the pixel values
     */
    public void setBeta(Double beta) {
        this.beta = beta;
    }

    /**
     * Applies the contrast and brightness adjustment
     *
     * @param oldMat the Mat object to process
     * @return the processed Mat object
     */
    @Override
    public Mat process(Mat oldMat) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat newMat = Mat.zeros(oldMat.size(), oldMat.type());
        oldMat.convertTo(newMat, -1, alpha, beta);

        return newMat;
    }
}

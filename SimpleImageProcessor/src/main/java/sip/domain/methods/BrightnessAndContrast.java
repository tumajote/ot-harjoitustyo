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
     *Sets the parameters for the adjustment
     * @param alpha The parameter for the brightness adjustment
     * @param beta The parameter for the contrast adjustment
     */
    public void setAlphAndBeta(double alpha, double beta) {
        this.beta = beta;
        this.alpha = alpha;
    }

    /**
     *Applies the contrast and brightness adjustment
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

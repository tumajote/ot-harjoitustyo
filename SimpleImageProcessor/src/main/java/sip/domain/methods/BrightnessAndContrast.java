package sip.domain.methods;

import org.opencv.core.Core;
import org.opencv.core.Mat;

public class BrightnessAndContrast implements Method {

    private Double alpha;
    private Double beta;

    public BrightnessAndContrast() {
        this.beta = 0.0;
    }

    public void setAlphAndBeta(double alpha, double beta) {
        this.beta = beta;
        this.alpha = alpha;
    }

    @Override
    public Mat process(Mat oldMat) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat newMat = Mat.zeros(oldMat.size(), oldMat.type());
        oldMat.convertTo(newMat, -1, alpha, beta);

 

        return newMat;
    }
}

package sip.domain.methods;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author tmjterho
 */
public class Sharpness implements Method {

    Double strength;

    /**
     *
     */
    public Sharpness() {
        this.strength = 0.0;
    }

    /**
     * Sets the strength of the sharpness adjustment
     *
     * @param strength is the times the Laplacian unsharp mask is applied.
     */
    public void setStrength(Double strength) {
        this.strength = strength;
    }

    @Override
    public Mat process(Mat oldMat) {
        Mat newMat = Mat.zeros(oldMat.size(), oldMat.type());
        Imgproc.medianBlur(oldMat, newMat, 1);
        Imgproc.Laplacian(newMat, newMat, -1);
        newMat.convertTo(newMat, -1, strength);
        Core.subtract(oldMat, newMat, newMat);

        return newMat;
    }

}

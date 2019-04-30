package sip.domain.methods;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Sharpness implements Method {

    Double strength;

    public Sharpness() {
        this.strength = 0.0;
    }

    public void setStrength(Double strength) {
        this.strength = strength;
    }

    @Override
    public Mat process(Mat mat) {
        Mat newMat = new Mat(mat.rows(), mat.cols(), mat.type());
        Mat newMat2 = new Mat(mat.rows(), mat.cols(), mat.type());
        Mat newMat3 = new Mat(mat.rows(), mat.cols(), mat.type());
        Imgproc.medianBlur(mat, newMat, 1);
        Imgproc.Laplacian(newMat, newMat2, -1);
        newMat2.convertTo(newMat2, -1, strength);
        Core.subtract(mat, newMat2, newMat3);

        return newMat3;
    }

}

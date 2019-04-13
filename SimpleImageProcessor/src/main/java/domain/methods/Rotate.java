package domain.methods;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Rotate implements Method {

    @Override
    public Mat process(Mat oldMat) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Creating an empty matrix to store the result
        int angle = 90;
        Mat newMat = new Mat();
        Core.transpose(oldMat, newMat);
        Core.flip(newMat, newMat, 1);
//
//      
//
//        int newWidth = (int) oldMat.height();
//        int newHeight = (int) oldMat.width();
//
//        // rotating image
//        Point center = new Point(oldMat.cols() /2, oldMat.rows()/2);
//        Mat rotMatrix = Imgproc.getRotationMatrix2D(center, angle, 1.0); //1.0 means 100 % scale
//
//        Size size = new Size(newWidth, newHeight);
//        Imgproc.warpAffine(oldMat, newMat, rotMatrix, size);
        return newMat;
    }

}

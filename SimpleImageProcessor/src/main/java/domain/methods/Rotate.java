package domain.methods;

import org.opencv.core.Core;
import org.opencv.core.Mat;

public class Rotate implements Method {

    @Override
    public  Mat process(Mat oldMat) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat newMat = new Mat();
        Core.transpose(oldMat, newMat);
        Core.flip(newMat, newMat, 1);

        return newMat;
    }

}

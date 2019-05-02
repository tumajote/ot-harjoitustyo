package sip.domain.methods;

import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 * A Class that transforms a Mat object by rotating it
 *
 * @author tmjterho
 */
public class Rotate implements Method {

    /**
     * Rotates a Mat object 90 degrees clockwise
     *
     * @param oldMat the Mat object to be rotated
     * @return the rotated MAt object
     */
    @Override
    public Mat process(Mat oldMat) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat newMat = new Mat();
        Core.transpose(oldMat, newMat);
        Core.flip(newMat, newMat, 1);

        return newMat;
    }

}

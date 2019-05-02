
package sip.domain.methods;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author tmjterho
 */
public class GrayScale implements Method {

    Boolean grayScale;

    /**
     *
     */
    public GrayScale() {
        grayScale = false;
    }

    /**
     *Sets the grayscale conversion variable
     * @param grayscale true if grayscale
     */
    public void setGrayScale(Boolean grayscale) {
        this.grayScale = grayscale;
    }

    /**
     *Returns the grayscale conversion variable 
     * @return true if grayscale
     */
    public Boolean getGrayScale() {
        return grayScale;
    }
    

    /**
     * Applies the grayscale conversion
     *
     * @param oldMat the Mat object to convert
     * @return the converted Mat object
     */
    @Override
    public Mat process(Mat oldMat) {
        if (grayScale) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            Mat newMat = Mat.zeros(oldMat.size(), oldMat.type());
            Imgproc.cvtColor(oldMat, newMat, Imgproc.COLOR_RGB2GRAY);
            return newMat;
        }
        return oldMat;

    }

}

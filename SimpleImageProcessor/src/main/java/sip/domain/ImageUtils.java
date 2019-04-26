package sip.domain;

import java.io.ByteArrayInputStream;
import javafx.scene.image.Image;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;

/**
 *
 * Utility class for transforming Mat objects to Images
 */
public class ImageUtils {

    /**
     *Transcodes a Mat object into a Image object.
     * @param mat A Mat object 
     * @return An Image object
     */
    public static Image matToImage(Mat mat) {
        nu.pattern.OpenCV.loadLibrary();
        MatOfByte byteMat = new MatOfByte();
        Highgui.imencode(".bmp", mat, byteMat);
        return new Image(new ByteArrayInputStream(byteMat.toArray()));

    }

}

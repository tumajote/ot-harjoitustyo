
package domain;

import java.io.ByteArrayInputStream;
import javafx.scene.image.Image;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;

public class ImageUtils {

    public Image matToImage(Mat mat) {
        nu.pattern.OpenCV.loadLibrary();
        MatOfByte byteMat = new MatOfByte();
        Highgui.imencode(".bmp", mat, byteMat);
        return new Image(new ByteArrayInputStream(byteMat.toArray()));

    }

}

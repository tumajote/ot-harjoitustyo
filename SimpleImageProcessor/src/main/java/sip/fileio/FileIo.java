package sip.fileio;

import sip.domain.ImageData;
import java.io.File;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

public class FileIo {

    public static String loadImage(ImageData imageData, File file) {
        String path = "not found";
        try {
            nu.pattern.OpenCV.loadLibrary();
            Mat mat = Highgui.imread(file.getCanonicalPath());
            imageData.setMat(mat);
            path = file.getCanonicalPath();
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return path;
    }

    public static boolean saveImage(ImageData imageData, File file) {
        boolean success = false;
        try {
            nu.pattern.OpenCV.loadLibrary();
            success = Highgui.imwrite(file.getCanonicalPath(), imageData.getMat());

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return success;
    }
}

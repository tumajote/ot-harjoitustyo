package fileio;

import domain.ImageData;
import java.io.File;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

public class FileLoader {

    public String loadImage(ImageData imageData, File file) {
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
}

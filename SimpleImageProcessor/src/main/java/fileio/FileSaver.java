package fileio;

import domain.ImageData;
import java.io.File;
import org.opencv.highgui.Highgui;

public class FileSaver {

    public boolean saveImage(ImageData imageData, File file) {
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

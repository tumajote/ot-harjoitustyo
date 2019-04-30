package sip.fileio;

import sip.domain.ImageData;
import java.io.File;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

/**
 *An utility class for loading and saving image files from disk
 * @author tmjterho
 */
public class FileIo {

    /**
     *Loads an image from disk as an Mat object to an imageData object 
     * @param imageData the current imageData object
     * @param file the image file to be loaded
     * @return the path to the file
     */
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

    /**
     *Saves an mat object as an file to the disk.
     * @param imageData the current imageData object
     * @param file the save file location
     * @return the path to the saved file
     */
    public static boolean saveImage(ImageData imageData, File file) {
        boolean success = false;
        try {
            nu.pattern.OpenCV.loadLibrary();
            success = Highgui.imwrite(file.getCanonicalPath(), imageData.getProcessedMat());

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return success;
    }
}

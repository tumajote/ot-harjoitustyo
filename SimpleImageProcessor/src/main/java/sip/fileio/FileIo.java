package sip.fileio;

import sip.domain.ImageData;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

/**
 * An utility class for loading and saving image files from disk
 *
 * @author tmjterho
 */
public class FileIo {

    /**
     * Loads an image from disk as an Mat object to an imageData object
     *
     * @param imageData the current imageData object
     * @param file the image file to be loaded
     * @return the path to the file
     * @throws java.io.IOException to gui
     */
    public static String loadImage(ImageData imageData, File file) throws IOException {
        String path = "not found";

        nu.pattern.OpenCV.loadLibrary();
        Mat mat;

        mat = Highgui.imread(file.getCanonicalPath());
        imageData.setMat(mat);
        path = file.getCanonicalPath();

        return path;
    }

    /**
     * Saves an mat object as an file to the disk.
     *
     * @param imageData the current imageData object
     * @param file the save file location
     * @return the path to the saved file
     * @throws java.io.IOException
     */
    public static boolean saveImage(ImageData imageData, File file) throws IOException {
        boolean success = false;

        nu.pattern.OpenCV.loadLibrary();
        success = Highgui.imwrite(file.getCanonicalPath(), imageData.getProcessedMat());

        return success;
    }
}


package fileIO;

import domain.ImageData;
import java.io.File;
import javafx.stage.FileChooser;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;


public class FileLoader {

    public void loadImage(ImageData imageData) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        File file = fileChooser.showOpenDialog(null);

        try {
            nu.pattern.OpenCV.loadLibrary();
            Mat mat = Highgui.imread(file.getCanonicalPath());
            imageData.setMatAndUpdateImage(mat);  
        } catch (Exception ex) {
            System.out.println("Error");
        }

    }
}

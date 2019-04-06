
package fileIO;

import domain.ImageData;
import gui.LaunchApplication;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;


public class FileLoader {

    public void loadImage(ImageView currentImage, ImageView histogram, ImageData imageData, Label widthXHeight) {
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
//            BufferedImage bImage = ImageIO.read(file);
//            Image image = SwingFXUtils.toFXImage(bImage, null);
            nu.pattern.OpenCV.loadLibrary();
            Mat mat = Highgui.imread(file.getCanonicalPath());
            imageData.setMatAndUpdateImage(mat);
            Image image = imageData.getImage();
            
            currentImage.setImage(image);
            histogram.setImage(imageData.getHistogram());

            if (image.getHeight() > image.getWidth()) {
                currentImage.setFitHeight(1000);
            } else {
                currentImage.setFitWidth(1000);
            }
            
            currentImage.setPreserveRatio(true);
            currentImage.setSmooth(true);
            currentImage.setCache(true);
//            imageData.setImage(image);
            widthXHeight.setText(imageData.getImageMeasures());
            
        } catch (Exception ex) {
            System.out.println("Error");
        }

    }
}

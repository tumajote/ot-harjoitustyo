
package GUI;

import ImageData.Measures;
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


public class FileIo {

    public void loadImage(ImageView currentImage, Measures measures, Label widthXHeight) {
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
            BufferedImage bImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bImage, null);
            currentImage.setImage(image);

            if (image.getHeight() > image.getWidth()) {
                currentImage.setFitHeight(1200);
            } else {
                currentImage.setFitWidth(1200);
            }

            currentImage.setPreserveRatio(true);
            currentImage.setSmooth(true);
            currentImage.setCache(true);
            measures.setImage(image);
            widthXHeight.setText(measures.getImageMeasures());
        } catch (IOException ex) {
            Logger.getLogger(LaunchApplication.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

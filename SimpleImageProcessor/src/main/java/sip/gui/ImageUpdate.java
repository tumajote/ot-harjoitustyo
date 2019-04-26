package sip.gui;

import static sip.gui.LaunchApplication.currentImage;
import static sip.gui.LaunchApplication.histogram;
import static sip.gui.LaunchApplication.imageData;
import static sip.gui.LaunchApplication.widthXHeight;
import javafx.scene.image.Image;

/**
 *
 * @author tmjterho
 */
public class ImageUpdate {

    /**
     *
     */
    static public void update() {
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
        widthXHeight.setText(imageData.getImageMeasures());

    }

}

package gui;

import domain.ImageData;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageUpdate {

    public void update(ImageData imageData, ImageView histogram, ImageView currentImage, Label widthXHeight) {
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

package sip.gui;

import sip.fileio.FileIo;
import sip.domain.ImageData;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author tmjterho
 */
public class LaunchApplication extends Application {
    
    static ImageView currentImage;
    static ImageView histogram;
    static ImageData imageData;
    static Label widthXHeight;
    static Slider brightnessSlider;
    static Slider contrastSlider;
    static Slider sharpnessSlider;
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        
        imageData = new ImageData();

        // Load and save buttons
        Button loadButton = new Button("Load image");
        loadButton.setOnAction(btnLoadEventListener);
        Button saveButton = new Button("Save image");
        saveButton.setOnAction(btnSaveEventListener);
        HBox fileButtons = new HBox(5.0, loadButton, saveButton);

        //Width and height label
        widthXHeight = new Label(imageData.getImageMeasures());

        //Rotate button
        Button rotateButton = new Button("Rotate image");
        rotateButton.setOnAction(btnRotateEventListener);

        //Rotate button
        Button greyScalebutton = new Button("Convert to greyscale");
        greyScalebutton.setOnAction(grayScaleEventListener);

        //Reset all button 
        Button resetAllButton = new Button("Reset all");
        resetAllButton.setOnAction(btnResetAllEventListener);

        //Slider and reset button for brightness adjustement 
        Button resetBrigthnessButton = new Button("Reset");
        resetBrigthnessButton.setOnAction(resetBrigthnessButtonEventListener);
        brightnessSlider = new Slider(-100, 100, 0);
        brightnessSlider.setShowTickMarks(true);
        brightnessSlider.setShowTickLabels(true);
        brightnessSlider.setBlockIncrement(10.0);
        brightnessSlider.valueProperty().addListener(brightnessSliderListener);
        Label brightnessLabel = new Label("Brightness:");
        HBox bightnessLabelAndReset = new HBox(10.0, brightnessLabel, resetBrigthnessButton);

        //Slider and reset button for contrast adjustement
        Button resetContrastButton = new Button("Reset");
        resetContrastButton.setOnAction(resetContrastButtonEventListener);
        contrastSlider = new Slider(0, 3, 1);
        contrastSlider.setShowTickMarks(true);
        contrastSlider.setShowTickLabels(true);
        contrastSlider.setBlockIncrement(0.1);
        contrastSlider.valueProperty().addListener(contrastSliderListener);
        Label contrastLabel = new Label("Contrast:");
        HBox contrastLabelAndReset = new HBox(10.0, contrastLabel, resetContrastButton);

        //Slider and reset button for sharpness adjustement
        Button resetSharpnessButton = new Button("Reset");
        resetSharpnessButton.setOnAction(resetSharpnessButtonEventListener);
        sharpnessSlider = new Slider(0, 3, 0);
        sharpnessSlider.setShowTickMarks(true);
        sharpnessSlider.setShowTickLabels(true);
        sharpnessSlider.setBlockIncrement(0.1);
        sharpnessSlider.valueProperty().addListener(sharpnessSliderListener);
        Label sharpnessLabel = new Label("Sharpness:");
        HBox sharpnessLabelAndReset = new HBox(10.0, sharpnessLabel, resetSharpnessButton);

        //Imageviewer and histogram
        currentImage = new ImageView();
        histogram = new ImageView();
        histogram.setFitWidth(300);
        histogram.setFitHeight(200);

        //Layout for controls
        VBox controls = new VBox();
        controls.setSpacing(10);
        controls.setPadding(new Insets(15, 20, 10, 10));
        controls.getChildren().add(histogram);
        controls.getChildren().add(widthXHeight);
        controls.getChildren().add(fileButtons);
        controls.getChildren().add(rotateButton);
        controls.getChildren().add(greyScalebutton);
        controls.getChildren().add(bightnessLabelAndReset);
        controls.getChildren().add(brightnessSlider);
        controls.getChildren().add(contrastLabelAndReset);
        controls.getChildren().add(contrastSlider);
        controls.getChildren().add(sharpnessLabelAndReset);
        controls.getChildren().add(sharpnessSlider);
        controls.getChildren().add(resetAllButton);

        //General layout
        BorderPane setup = new BorderPane();
        setup.setLeft(controls);
        setup.setCenter(currentImage);
        setup.setPrefWidth(1500);
        setup.setPrefHeight(1500);
        
        Scene scene = new Scene(setup);
        primaryStage.setTitle("SimpleImageProcessor");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
        launch(LaunchApplication.class);
    }

    //Load button event listener
    EventHandler<ActionEvent> btnLoadEventListener
            = new EventHandler<ActionEvent>() {
        
        @Override
        public void handle(ActionEvent t) {
            File file = openFile();
            if (file == null) {
                return;
            }
            FileIo.loadImage(imageData, file);
            imageUpdate();
        }
    };

    //Save button event listener
    EventHandler<ActionEvent> btnSaveEventListener
            = new EventHandler<ActionEvent>() {
        
        @Override
        public void handle(ActionEvent t) {
            if (!imageData.exists()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("No image");
                alert.setHeaderText(null);
                alert.setContentText("No image to save!");
                
                alert.showAndWait();
                
                return;
            }
            
            File file = saveFile();
            
            if (file == null) {
                return;
            }
            
            if (!file.getAbsolutePath().endsWith(".png") || !file.getAbsolutePath().endsWith(".jpg")) {
                Boolean noExtension = true;
                while (noExtension) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("File type missing");
                    alert.setHeaderText("Choose file type!");
                    alert.setContentText("Please add extension .jpg or .png to the file name");
                    alert.showAndWait();
                    file = saveFile();
                    if (file == null) {
                        return;
                    }
                    if (file.getAbsolutePath().endsWith(".png") || file.getAbsolutePath().endsWith(".jpg")) {
                        noExtension = false;
                    }
                    
                }
                
            }
            Boolean success = FileIo.saveImage(imageData, file);
            if (success) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Save file success");
                alert.setHeaderText(null);
                alert.setContentText("File saved!");
                
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Save file failure");
                alert.setHeaderText("Save failed");
                alert.setContentText("File not saved!");
                
                alert.showAndWait();
            }
            
        }
    };

    //Rotate button event listener
    EventHandler<ActionEvent> btnRotateEventListener
            = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            if (!imageData.exists()) {
                return;
            }
            imageData.rotate();
            imageData.process();
            imageUpdate();
        }
    };

    //Grayscale button event listener
    EventHandler<ActionEvent> grayScaleEventListener
            = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            if (!imageData.exists()) {
                return;
            }
            imageData.setGrayScale(true);
            imageData.process();
            imageUpdate();
        }
    };
    //Brightness slider change listener
    ChangeListener<Number> brightnessSliderListener
            = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (!imageData.exists()) {
                return;
            }
            imageData.setBrigthnessValue((double) newValue);
            imageData.process();
            imageUpdate();
        }
    };

    //Brightness reset button event listener
    EventHandler<ActionEvent> resetBrigthnessButtonEventListener
            = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            if (!imageData.exists()) {
                return;
            }
            brightnessSlider.setValue(0);
            imageData.setBrigthnessValue(0);
            imageData.process();
            imageUpdate();
            
        }
    };

    //Contrast slider change listener
    ChangeListener<Number> contrastSliderListener
            = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (!imageData.exists()) {
                return;
            }
            imageData.setContrastValue((double) newValue);
            imageData.process();
            imageUpdate();
        }
    };

    //Contrast reset button event listener
    EventHandler<ActionEvent> resetContrastButtonEventListener
            = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            if (!imageData.exists()) {
                return;
            }
            contrastSlider.setValue(1);
            imageData.setContrastValue(1);
            imageData.process();
            imageUpdate();
            
        }
    };

    //Contrast slider change listener
    ChangeListener<Number> sharpnessSliderListener
            = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (!imageData.exists()) {
                return;
            }
            imageData.setSharpnessValue((double) newValue);
            imageData.process();
            imageUpdate();
        }
    };

    //Contrast reset button event listener
    EventHandler<ActionEvent> resetSharpnessButtonEventListener
            = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            if (!imageData.exists()) {
                return;
            }
            sharpnessSlider.setValue(0.0);
            imageData.setSharpnessValue(0.0);
            imageData.process();
            imageUpdate();
            
        }
    };

    //Reset all button event listener
    EventHandler<ActionEvent> btnResetAllEventListener
            = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            if (!imageData.exists()) {
                return;
            }
            sharpnessSlider.setValue(0);
            contrastSlider.setValue(1);
            sharpnessSlider.setValue(0);
            imageData.resetAll();
            imageUpdate();
            
        }
    };

    /**
     * Updates the image, histogram and measures.
     *
     *
     */
    static public void imageUpdate() {
        Image image = imageData.getImage();
        
        currentImage.setImage(image);
        histogram.setImage(imageData.getHistogram());
        
        if (image.getHeight() > image.getWidth()) {
            currentImage.setFitHeight(1000);
        } else {
            currentImage.setFitWidth(1000);
        }
        histogram.setPreserveRatio(true);
        histogram.setSmooth(true);
        histogram.setCache(true);
        currentImage.setPreserveRatio(true);
        currentImage.setSmooth(true);
        currentImage.setCache(true);
        widthXHeight.setText(imageData.getImageMeasures());
        
    }

    /**
     * Opens up a choose file dialogue
     *
     * @return a file chosen to be opened
     */
    static public File openFile() {
        FileChooser fileChooser = new FileChooser();
        
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterjpg, extFilterpng);
        
        return fileChooser.showOpenDialog(null);
    }

    /**
     * Opens up a choose file dialogue
     *
     * @return a file chosen to be saved
     */
    static public File saveFile() {
        FileChooser fileChooser = new FileChooser();
        
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterjpg, extFilterpng);
        
        return fileChooser.showSaveDialog(null);
    }
}

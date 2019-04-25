package sip.gui;

import sip.fileio.FileIo;
import sip.domain.ImageData;
import sip.domain.methods.BrightnessAndContrast;
import sip.domain.methods.Rotate;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LaunchApplication extends Application {

    static ImageView currentImage;
    static ImageView histogram;
    static ImageData imageData;
    static Label widthXHeight;
    static Slider brightnessSlider;
    static Slider contrastSlider;

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

        //Slider and reset button for brightness adjustement 
        Button resetBrigthnessButton = new Button("Reset");
        resetBrigthnessButton.setOnAction(resetBrigthnessButtonEventListener);
        brightnessSlider = new Slider(-100, 100, 0);
        brightnessSlider.setShowTickMarks(true);
        brightnessSlider.setShowTickLabels(true);
        brightnessSlider.setBlockIncrement(1.0);
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
        controls.getChildren().add(bightnessLabelAndReset);
        controls.getChildren().add(brightnessSlider);
        controls.getChildren().add(contrastLabelAndReset);
        controls.getChildren().add(contrastSlider);

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
            File file = FileChooserWindow.openFile();
            FileIo.loadImage(imageData, file);
            ImageUpdate.update();
        }
    };

    //Save button event listener
    EventHandler<ActionEvent> btnSaveEventListener
            = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent t) {
            if (!imageData.exists()) {
                return;
            }
            
            File file = FileChooserWindow.saveFile();
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
            imageData.useTransformingMethod(new Rotate());
            ImageUpdate.update();
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
            BrightnessAndContrast brightness = new BrightnessAndContrast();
            //Values
            System.out.println(newValue);
            System.out.println(contrastSlider.getValue());
            brightness.setAlphAndBeta(contrastSlider.getValue(), (double) newValue);
            imageData.useProcessingMethod(brightness);
            ImageUpdate.update();
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
            BrightnessAndContrast brightness = new BrightnessAndContrast();
            brightness.setAlphAndBeta(contrastSlider.getValue(), 0);
            imageData.useProcessingMethod(brightness);
            ImageUpdate.update();

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
            BrightnessAndContrast contrast = new BrightnessAndContrast();
            //Values
            System.out.println(newValue);
            System.out.println(brightnessSlider.getValue());
            contrast.setAlphAndBeta((double) newValue, brightnessSlider.getValue());
            imageData.useProcessingMethod(contrast);
            ImageUpdate.update();
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
            BrightnessAndContrast brightness = new BrightnessAndContrast();
            brightness.setAlphAndBeta(1, brightnessSlider.getValue());
            imageData.useProcessingMethod(brightness);
            ImageUpdate.update();

        }
    };

}

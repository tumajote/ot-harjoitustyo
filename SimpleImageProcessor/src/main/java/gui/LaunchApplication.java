package gui;

import fileio.FileLoader;
import domain.ImageData;
import domain.methods.AdjustBrightness;
import domain.methods.Rotate;
import fileio.FileSaver;
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

    ImageView currentImage;
    ImageView histogram;
    ImageData imageData;
    Label widthXHeight;
    BorderPane setup;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        imageData = new ImageData();

        // Create load and save buttons
        Button loadButton = new Button("Load image");
        loadButton.setOnAction(btnLoadEventListener);
        Button saveButton = new Button("Save image");
        saveButton.setOnAction(btnSaveEventListener);
        HBox fileButtons = new HBox(5.0, loadButton, saveButton);

        //Create width and height label
        widthXHeight = new Label(imageData.getImageMeasures());

        //Create rotate button
        Button rotateButton = new Button("Rotate image");
        rotateButton.setOnAction(btnRotateEventListener);

        //Create sliders for image adjustements
        Slider brightnessSlider = new Slider(-100, 100, 0);
        brightnessSlider.setShowTickMarks(true);
        brightnessSlider.setShowTickLabels(true);

        brightnessSlider.setBlockIncrement(10.0);
        brightnessSlider.valueProperty().addListener(brightnessSliderListener);
        Label brightnessLabel = new Label("Brightness:");

        //Create imageviewer
        currentImage = new ImageView();
        histogram = new ImageView();
        histogram.setFitWidth(300);
        histogram.setFitHeight(200);

        //Create layout for controls
        VBox controls = new VBox();
        controls.setSpacing(10);
        controls.setPadding(new Insets(15, 20, 10, 10));
        controls.getChildren().add(histogram);
        controls.getChildren().add(widthXHeight);
        controls.getChildren().add(fileButtons);
        controls.getChildren().add(rotateButton);
        controls.getChildren().add(brightnessLabel);
        controls.getChildren().add(brightnessSlider);

        //Create layout for the whole applications
        setup = new BorderPane();
        setup.setLeft(controls);
        setup.setCenter(currentImage);
        setup.setPrefWidth(1500);
        setup.setPrefHeight(1500);

        //Show
        Scene scene = new Scene(setup);
        primaryStage.setTitle("SimpleImageProcessor");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(LaunchApplication.class);
    }

    EventHandler<ActionEvent> btnLoadEventListener
            = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent t) {
            FileLoader fileloader = new FileLoader();
            FileChooserWindow loadFile = new FileChooserWindow();
            loadFile.openFile();
            File file = loadFile.getFile();
            fileloader.loadImage(imageData, file);
            ImageUpdate imageUpdate = new ImageUpdate();
            imageUpdate.update(imageData, histogram, currentImage, widthXHeight);
        }
    };

    EventHandler<ActionEvent> btnSaveEventListener
            = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent t) {
            if (!imageData.exists()) {
                return;
            }
            FileSaver fileSaver = new FileSaver();
            FileChooserWindow saveFile = new FileChooserWindow();
            saveFile.saveFile();
            File file = saveFile.getFile();
            Boolean success = fileSaver.saveImage(imageData, file);
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

    EventHandler<ActionEvent> btnRotateEventListener
            = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            if (!imageData.exists()) {
                return;
            }
            imageData.useMethod(new Rotate());
            ImageUpdate imageUpdate = new ImageUpdate();
            imageUpdate.update(imageData, histogram, currentImage, widthXHeight);
        }
    };

    ChangeListener<Number> brightnessSliderListener
            = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (!imageData.exists()) {
                return;
            }
            AdjustBrightness contrast = new AdjustBrightness();
            contrast.setValue((double) newValue);
            imageData.useMethod(contrast);
            ImageUpdate imageUpdate = new ImageUpdate();
            imageUpdate.update(imageData, histogram, currentImage, widthXHeight);
        }
    };

}

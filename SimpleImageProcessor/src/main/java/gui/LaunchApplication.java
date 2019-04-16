package gui;

import fileio.FileLoader;
import domain.ImageData;
import domain.methods.Rotate;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
        Button loadButton = new Button("Load image");
        loadButton.setOnAction(btnLoadEventListener);
        widthXHeight = new Label(imageData.getImageMeasures());

        Button rotateButton = new Button("Rotate picture");
        rotateButton.setOnAction(btnRotateEventListener);

        currentImage = new ImageView();
        histogram = new ImageView();
        histogram.setFitWidth(300);
        histogram.setFitHeight(200);

        VBox controls = new VBox();
        controls.setSpacing(10);
        controls.setPadding(new Insets(15, 20, 10, 10));
        controls.getChildren().add(histogram);
        controls.getChildren().add(widthXHeight);
        controls.getChildren().add(loadButton);
        controls.getChildren().add(rotateButton);

        setup = new BorderPane();
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

    EventHandler<ActionEvent> btnLoadEventListener
            = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent t) {
            FileLoader fileloader = new FileLoader();
            FileChooserWindow loadFile = new FileChooserWindow();
            loadFile.chooseFile();
            File file = loadFile.getFile();
            fileloader.loadImage(imageData, file);
            ImageUpdate imageUpdate = new ImageUpdate();
            imageUpdate.update(imageData, histogram, currentImage, widthXHeight);
        }
    };

    EventHandler<ActionEvent> btnRotateEventListener
            = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            imageData.useMethod(new Rotate());
            ImageUpdate imageUpdate = new ImageUpdate();
            imageUpdate.update(imageData, histogram, currentImage, widthXHeight);
        }
    };

}

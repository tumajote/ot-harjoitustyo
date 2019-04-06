package gui;

import fileIO.FileLoader;
import domain.ImageData;
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
        
        Button btnLoad = new Button("Load picture");
        btnLoad.setOnAction(btnLoadEventListener);
        imageData = new ImageData();
        widthXHeight = new Label(imageData.getImageMeasures());

        currentImage = new ImageView();
        histogram = new ImageView();
        histogram.setFitWidth(300);
        histogram.setFitHeight(200);
        VBox controls = new VBox();
        controls.setSpacing(10);
        controls.setPadding(new Insets(15, 20, 10, 10));
        controls.getChildren().add(histogram);
        controls.getChildren().add(widthXHeight);
        controls.getChildren().add(btnLoad);
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
            FileLoader fileIo = new FileLoader();
            fileIo.loadImage(currentImage,histogram, imageData, widthXHeight);

        }
    };

}

package GUI;

import ImageData.Measures;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class LaunchApplication extends Application {

    ImageView currentImage;
    Measures measures;
    Label widthXHeight; 
    BorderPane setup;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Button btnLoad = new Button("Load picture");
        btnLoad.setOnAction(btnLoadEventListener);
        measures = new Measures();
        widthXHeight = new Label(measures.getImageMeasures());
        
        currentImage = new ImageView();
        VBox controls = new VBox();
        controls.setSpacing(10);
        controls.setPadding(new Insets(15,20, 10,10));
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
            FileIo fileIo = new FileIo();
            fileIo.loadImage(currentImage, measures,widthXHeight);
            
            

        }
    };

}

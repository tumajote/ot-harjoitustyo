package sip.gui;

import java.io.File;
import javafx.stage.FileChooser;

/**
 *
 * @author tmjterho
 */
public class FileChooserWindow {

    /**
     *
     * @return
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
     *
     * @return
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

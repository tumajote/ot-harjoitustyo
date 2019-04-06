/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.ByteArrayInputStream;
import javafx.scene.image.Image;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;

/**
 *
 * @author tmjterho
 */
public class ImageUtils {

    public Image matToImage(Mat mat) {
        nu.pattern.OpenCV.loadLibrary();
        MatOfByte byteMat = new MatOfByte();
        Highgui.imencode(".bmp", mat, byteMat);
        return new Image(new ByteArrayInputStream(byteMat.toArray()));

    }

}

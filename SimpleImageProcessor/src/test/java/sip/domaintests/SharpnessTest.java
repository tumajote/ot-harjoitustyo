/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sip.domaintests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import sip.domain.ImageData;
import sip.domain.methods.BrightnessAndContrast;

/**
 *
 * @author tmjterho
 */
public class SharpnessTest {

    ImageData imageData;
    Mat mat;
    Mat mockMat;

    public SharpnessTest() {
    }

    @Before
    public void setUp() throws IOException {
        imageData = new ImageData();

        nu.pattern.OpenCV.loadLibrary();
        File file = new File("test.jpg");
        this.mat = Highgui.imread(file.getCanonicalPath());
        imageData.setMat(mat);

        File file2 = new File("test.jpg");
        this.mockMat = Highgui.imread(file2.getCanonicalPath());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void adjustSharpness() {
        // Adjust sharpness of the image with library methods
        Mat newMat = Mat.zeros(mockMat.size(), mockMat.type());
         Imgproc.medianBlur(mockMat, newMat, 1);
        Imgproc.Laplacian(newMat, newMat, -1);
        newMat.convertTo(newMat, -1, 2);
        Core.subtract(mockMat, newMat, newMat);

        // Process image with method class
        imageData.setSharpnessValue(2);
        imageData.process();

        // Compare the matrixes
        Mat result = new Mat();
        mat = imageData.getProcessedMat();
        Core.compare(mat, newMat, result, Core.CMP_NE);
        ArrayList<Mat> channels = new ArrayList();
        Core.split(result, channels);
        Boolean isEqual = true;
        for (int i = 0; i < channels.size(); i++) {
            if (Core.countNonZero(channels.get(i)) > 0) {
                System.out.println(Core.countNonZero(channels.get(i)));
                isEqual = false;
            }

        }

        assertTrue(isEqual);
    }
}

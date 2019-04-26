package sip.domaintests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import sip.domain.ImageData;
import sip.domain.methods.BrightnessAndContrast;
import sip.domain.methods.Rotate;

/**
 *
 * @author tmjterho
 */
public class BrightnessAndContrastTest {

    ImageData imageData;
    Mat mat;
    Mat mockMat;

    public BrightnessAndContrastTest() {
    }

    @Before
    public void setUp() throws IOException {
        imageData = new ImageData();

        nu.pattern.OpenCV.loadLibrary();
        File file = new File("test.JPG");
        System.out.println(file.getCanonicalPath());
        this.mat = Highgui.imread(file.getCanonicalPath());
        imageData.setMat(mat);

        File file2 = new File("test.JPG");
        System.out.println(file2.getCanonicalPath());
        this.mockMat = Highgui.imread(file2.getCanonicalPath());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void adjustBrightnessAndContrast() {
          // Adjust brightness and contrast of the image with library methods
        Mat newMat = Mat.zeros(mockMat.size(), mockMat.type());
        mockMat.convertTo(newMat, -1, 1.5, 20);
        

        // Process image with method class
        BrightnessAndContrast bc = new BrightnessAndContrast();
        bc.setAlphAndBeta(1.5, 20);
        imageData.useProcessingMethod(bc);

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

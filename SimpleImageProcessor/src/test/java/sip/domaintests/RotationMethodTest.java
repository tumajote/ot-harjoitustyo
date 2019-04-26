package sip.domaintests;

import sip.domain.ImageData;
import sip.domain.methods.Rotate;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

public class RotationMethodTest {

    ImageData imageData;
    Mat mat;
    Mat mockMat;

    public RotationMethodTest() {

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

    @Test
    public void rotation() {
        // Rotate image with library methods
        Mat newMat = new Mat();
        Core.transpose(mockMat, newMat);
        Core.flip(newMat, newMat, 1);

        // Rotate image with method class
        Rotate rotate = new Rotate();
        imageData.useTransformingMethod(rotate);

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

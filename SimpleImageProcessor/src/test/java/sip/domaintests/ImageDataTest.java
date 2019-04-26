package sip.domaintests;

import sip.domain.ImageData;
import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

/**
 *
 * @author tmjterho
 */
public class ImageDataTest {

    ImageData imageData;
    Mat mat;

    public ImageDataTest() {
    }

    @Before
    public void setUp() throws IOException {
        imageData = new ImageData();

        nu.pattern.OpenCV.loadLibrary();
        File file = new File("test.JPG");
        System.out.println(file.getCanonicalPath());
        this.mat = Highgui.imread(file.getCanonicalPath());
        imageData.setMat(mat);
    }

    
    @Test
    public void outputIsCorrect() {
        
        assertEquals("width: 2685 x height: 1846", imageData.getImageMeasures());
    }

}
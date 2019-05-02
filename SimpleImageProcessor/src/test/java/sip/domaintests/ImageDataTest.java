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
        nu.pattern.OpenCV.loadLibrary();
        File file = new File("test.jpg");
        this.mat = Highgui.imread(file.getCanonicalPath());
        imageData = new ImageData();
    }

    @Test
    public void existIsCorrectifEmpty() {
        assertFalse(imageData.exists());
    }

    @Test
    public void measureOutputIsCorrectIfEmpty() {
        assertEquals("width: 0 x height: 0", imageData.getImageMeasures());
    }

    @Test
    public void existIsCorrect() {
        imageData.setMat(mat);
        assertTrue(imageData.exists());
    }

    @Test
    public void measureOutputIsCorrect() {
        imageData.setMat(mat);
        assertEquals("width: 2685 x height: 1846", imageData.getImageMeasures());
    }

    @Test
    public void resetAllReturnsOriginal() {
        imageData.setMat(mat);
        imageData.setBrigthnessValue(3);
        imageData.setContrastValue(2);
        imageData.process();
        imageData.resetAll();
        assertEquals(imageData.getOriginaltMat(), imageData.getProcessedMat());
    }

}

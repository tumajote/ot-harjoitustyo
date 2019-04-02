
package Tests;

import ImageData.Measures;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import javafx.scene.control.Label;


/**
 *
 * @author tmjterho
 */
public class SIPTest {

    
    Measures measures;
    Image image;

    public SIPTest() {
    }

    @Before
    public void setUp() throws FileNotFoundException {
        measures = new Measures();
        image = new Image(new FileInputStream("test.JPG"));
        

    }

    @Test
    public void widthIsZeroInTheBeginning() {
        assertEquals(0, measures.getWidth());
    }

    @Test
    public void heightIsZeroInTheBeginning() {
        assertEquals(0, measures.getHeight());
    }

    @Test
    public void widthIsCorrect() {
        measures.setImage(image);
        int width = (int)image.getWidth();
        assertEquals(width, measures.getWidth());
    }

    @Test
    public void heightIs() {
        measures.setImage(image);
        int height = (int)image.getHeight();
        assertEquals(height, measures.getHeight());
    }

}

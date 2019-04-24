
package Tests;

import domain.ImageData;
import fileio.FileLoader;
import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tmjterho
 */
public class FileIOTest {
    FileLoader fileLoader;
    ImageData imageData;
    File file;
    String path;
    
    public FileIOTest() {
    }
    
   
    @Before
    public void setUp() throws IOException {
    
        file = new File("test.JPG");
        path = file.getCanonicalPath();
        
        fileLoader = new FileLoader();
        imageData = new ImageData();
      
    
    }
 
    
    @Test
    public void pathIsCorrect(){     
        assertEquals(path, fileLoader.loadImage(imageData, file));
        
    }
    
}


package sip.Tests;

import sip.domain.ImageData;
import sip.fileio.FileIo;
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
    FileIo fileLoader;
    ImageData imageData;
    File file;
    String path;
    
    public FileIOTest() {
    }
    
   
    @Before
    public void setUp() throws IOException {
    
        file = new File("test.JPG");
        path = file.getCanonicalPath();
        
        fileLoader = new FileIo();
        imageData = new ImageData();
      
    
    }
 
    
    @Test
    public void pathIsCorrect(){     
        assertEquals(path, fileLoader.loadImage(imageData, file));
        
    }
    
}

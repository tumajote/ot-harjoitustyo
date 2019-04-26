
package sip.fileiotests;

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
    
    ImageData imageData;
    File file;
    String path;
    
    public FileIOTest() {
    }
    
   
    @Before
    public void setUp() throws IOException {
    
        file = new File("test.JPG");
        path = file.getCanonicalPath();
        
       
        imageData = new ImageData();
      
    
    }
 
    
    @Test
    public void pathIsCorrect(){     
        assertEquals(path, FileIo.loadImage(imageData, file));
    }
    
}

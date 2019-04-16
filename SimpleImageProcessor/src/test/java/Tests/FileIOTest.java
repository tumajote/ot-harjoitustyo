/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import domain.ImageData;
import fileio.FileLoader;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.opencv.highgui.Highgui;

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

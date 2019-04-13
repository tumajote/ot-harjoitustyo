
package domain.methods;

import domain.ImageData;
import org.opencv.core.Mat;


public interface Method {
    Mat process(Mat mat);
}

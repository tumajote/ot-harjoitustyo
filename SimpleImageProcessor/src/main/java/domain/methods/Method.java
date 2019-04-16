package domain.methods;

import org.opencv.core.Mat;

public interface Method {

    Mat process(Mat mat);
}

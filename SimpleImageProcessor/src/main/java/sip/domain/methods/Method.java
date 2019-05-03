package sip.domain.methods;

import org.opencv.core.Mat;

/**
 * An interface for processing and transforming Mat objects
 *
 * @author tmjterho
 */
public interface Method {

    /**
     * Processes or transforms a Mat object
     *
     * @param mat a Mat object to be processed or transformed
     * @return transformed or processed Mat object
     */
    Mat process(Mat mat);
}

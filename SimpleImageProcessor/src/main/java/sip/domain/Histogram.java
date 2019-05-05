package sip.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 *
 * Utility class for creating histograms
 */
public class Histogram {

    /**
     *
     * Calculates an histogram for images with 3 channels
     *
     * @param mat the source image in the form of a Mat object
     *
     * @return The histogram in a mat format
     */
    static public Mat calculate(Mat mat) {

        // Create planes for different colour channels
        List<Mat> bgrPlanes = new ArrayList<>();
        // Split channels to separate matrixes
        Core.split(mat, bgrPlanes);
        //Create parameters for the histogram
        int histSize = 256;
        float[] range = {0, 256};
        MatOfFloat histRange = new MatOfFloat(range);
        boolean accumulate = false;
        //Calculate histograms for the channels
        Mat bHist = new Mat(), gHist = new Mat(), rHist = new Mat();
        Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), bHist, new MatOfInt(histSize), histRange, accumulate);
        Imgproc.calcHist(bgrPlanes, new MatOfInt(1), new Mat(), gHist, new MatOfInt(histSize), histRange, accumulate);
        Imgproc.calcHist(bgrPlanes, new MatOfInt(2), new Mat(), rHist, new MatOfInt(histSize), histRange, accumulate);
        return draw(histSize, bHist, gHist, rHist);
    }

    static private Mat draw(int histSize, Mat bHist, Mat gHist, Mat rHist) {
        //Create parameters for the histogram image
        int histW = 512, histH = 400;
        int binW = (int) Math.round((double) histW / histSize);
        Mat histImage = new Mat(histH, histW, CvType.CV_8UC3, new Scalar(0, 0, 0));
        //Normalize histogram values
        Core.normalize(bHist, bHist, 0, histImage.rows(), Core.NORM_MINMAX);
        Core.normalize(gHist, gHist, 0, histImage.rows(), Core.NORM_MINMAX);
        Core.normalize(rHist, rHist, 0, histImage.rows(), Core.NORM_MINMAX);
        //Draw the histogram
        float[] bHistData = new float[(int) (bHist.total() * bHist.channels())];
        bHist.get(0, 0, bHistData);
        float[] gHistData = new float[(int) (gHist.total() * gHist.channels())];
        gHist.get(0, 0, gHistData);
        float[] rHistData = new float[(int) (rHist.total() * rHist.channels())];
        rHist.get(0, 0, rHistData);
        for (int i = 1; i < histSize; i++) {
            Core.line(histImage, new Point(binW * (i - 1), histH - Math.round(bHistData[i - 1])),
                    new Point(binW * (i), histH - Math.round(bHistData[i])), new Scalar(255, 0, 0), 2);
            Core.line(histImage, new Point(binW * (i - 1), histH - Math.round(gHistData[i - 1])),
                    new Point(binW * (i), histH - Math.round(gHistData[i])), new Scalar(0, 255, 0), 2);
            Core.line(histImage, new Point(binW * (i - 1), histH - Math.round(rHistData[i - 1])),
                    new Point(binW * (i), histH - Math.round(rHistData[i])), new Scalar(0, 0, 255), 2);

        }
        return histImage;
    }

    /**
     *
     * Calculates an histogram for images with 3 channels
     *
     * @param mat the source image in the form of a Mat object
     *
     * @return The histogram in a mat format
     */
    static public Mat calculateGrayScaleHistogram(Mat mat) {

        LinkedList<Mat> imageList = new LinkedList<>();
        imageList.add(mat);
        //Create parameters for the histogram
        int histSize = 256;
        float[] range = {0, 256};
        MatOfFloat histRange = new MatOfFloat(range);
        boolean accumulate = false;
        Mat hist = new Mat();
        //Calculate histogram
        Imgproc.calcHist(imageList, new MatOfInt(0), new Mat(), hist, new MatOfInt(histSize), histRange, accumulate);
        return drawGrayScaleHistogram(histSize, hist);
    }

    static private Mat drawGrayScaleHistogram(int histSize, Mat hist) {
        //Create parameters for the histogram image
        int histW = 512, histH = 400;
        int binW = (int) Math.round((double) histW / histSize);
        Mat histImage = new Mat(histH, histW, CvType.CV_8UC3, new Scalar(0, 0, 0));
        //Normalize histogram values
        Core.normalize(hist, hist, 0, histImage.rows(), Core.NORM_MINMAX);
        //Draw the histogram
        float[] bHistData = new float[(int) (hist.total() * hist.channels())];
        hist.get(0, 0, bHistData);
        for (int i = 1; i < histSize; i++) {
            Core.line(histImage, new Point(binW * (i - 1), histH - Math.round(bHistData[i - 1])),
                    new Point(binW * (i), histH - Math.round(bHistData[i])), new Scalar(255, 0, 0), 2);
        }
        return histImage;
    }
}

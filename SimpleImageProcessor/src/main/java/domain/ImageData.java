package domain;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class ImageData {

    Image image;
    int width;
    int height;
    String imageMeasures;
    Mat mat;
    Image histogram;
    ImageUtils imageUtils;

    public ImageData() {
        imageUtils = new ImageUtils();
        this.height = 0;
        this.width = 0;

        imageMeasures = "width: " + this.width
                + " x height: " + this.height;
    }

    public void setMatAndUpdateImage(Mat mat) {
        this.mat = mat;
        image = this.imageUtils.matToImage(mat);
        this.setImage(image);
        this.calculateHistogram();

    }

    public void setImage(Image image) {
        this.image = image;
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
    }

    public Image getImage() {
        return image;
    }

    public String getImageMeasures() {
        imageMeasures = "width: " + this.width
                + " x height: " + this.height;
        return imageMeasures;
    }

    public Image getHistogram() {
        return histogram;
    }
    

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void calculateHistogram() {
        List<Mat> bgrPlanes = new ArrayList<>();
        Core.split(this.mat, bgrPlanes);
        int histSize = 256;
        float[] range = {0, 256}; //the upper boundary is exclusive
        MatOfFloat histRange = new MatOfFloat(range);
        boolean accumulate = false;
        Mat bHist = new Mat(), gHist = new Mat(), rHist = new Mat();
        Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), bHist, new MatOfInt(histSize), histRange, accumulate);
        Imgproc.calcHist(bgrPlanes, new MatOfInt(1), new Mat(), gHist, new MatOfInt(histSize), histRange, accumulate);
        Imgproc.calcHist(bgrPlanes, new MatOfInt(2), new Mat(), rHist, new MatOfInt(histSize), histRange, accumulate);
        int histW = 512, histH = 400;
        int binW = (int) Math.round((double) histW / histSize);
        Mat histImage = new Mat(histH, histW, CvType.CV_8UC3, new Scalar(0, 0, 0));
        Core.normalize(bHist, bHist, 0, histImage.rows(), Core.NORM_MINMAX);
        Core.normalize(gHist, gHist, 0, histImage.rows(), Core.NORM_MINMAX);
        Core.normalize(rHist, rHist, 0, histImage.rows(), Core.NORM_MINMAX);
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
        this.histogram = imageUtils.matToImage(histImage);
    }

}

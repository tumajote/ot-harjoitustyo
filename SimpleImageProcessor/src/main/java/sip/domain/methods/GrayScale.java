/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sip.domain.methods;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author tmjterho
 */
public class GrayScale implements Method {

    Boolean grayScale;

    public GrayScale() {
        grayScale = false;
    }
    public void setGrayScale(Boolean grayScale){
        this.grayScale = grayScale;
    }

    public Boolean getGrayScale() {
        return grayScale;
    }
    

    @Override
    public Mat process(Mat oldMat) {
        if (grayScale) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            Mat newMat = Mat.zeros(oldMat.size(), oldMat.type());
            Imgproc.cvtColor(oldMat, newMat, Imgproc.COLOR_RGB2GRAY);
            return newMat;
        }
        return oldMat;

    }

}

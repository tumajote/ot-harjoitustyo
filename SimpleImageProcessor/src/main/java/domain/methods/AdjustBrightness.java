 
package domain.methods;

import org.opencv.core.Core;
import org.opencv.core.Mat;


public class AdjustBrightness implements Method{

    private Double beta;

    public AdjustBrightness() {
        this.beta = 0.0;
    }
    
    public void setValue(double value){
        this.beta = value;
    } 
    
    @Override
    public Mat process(Mat oldMat) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat newMat = Mat.zeros(oldMat.size(),oldMat.type());
        oldMat.convertTo(newMat, -1, 1, beta);

        return newMat;
    }
}

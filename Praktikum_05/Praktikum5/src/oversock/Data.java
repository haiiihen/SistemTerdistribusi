package oversock;

import java.io.Serializable;

public class Data implements Serializable {
    private float a[] = null;
    private float b[] = null;
    public Data(float[] a, float[] b){
        super();
        this.a = a;
        this.b = b;
    }
    public float[] getA(){
        return a;
    }
    public void setA(float[] a){
        this.a = a;
    }
    public float[] getB(){
        return b;
    }
    public void setB(float[] b){
        this.b = b;
    }

}
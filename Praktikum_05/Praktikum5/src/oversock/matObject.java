package oversock;

import java.io.Serializable;

public class matObject implements Serializable {
    private float hasil[] = null;
    public void jumlah(Data a){
        if(a.getA().length != a.getB().length){
            return;
        }else{
            float[] x = a.getA();
            float[] y = a.getB();
            hasil = new float[x.length];
            for(int i=0;i<x.length;i++){
                hasil[i] = x[i]+y[i];
            }
        }
    }
    public float[] getHasil(){ return this.hasil; 
    
    }

}
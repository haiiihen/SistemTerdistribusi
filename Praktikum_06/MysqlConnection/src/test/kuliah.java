package test;

import java.io.Serializable;
import java.util.ArrayList;

public class kuliah implements Serializable{
    private ArrayList<mahasiswa> isi = null;
    public ArrayList<mahasiswa> getIsi(){
        return isi;
    }
    public void setIsi(ArrayList<mahasiswa> isi){
        this.isi = isi;
    }

}
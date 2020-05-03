package test;

import java.io.Serializable;

public class mahasiswa implements Serializable{
    private int nim;
    public int getNim(){
        return nim;
    }
    public void setNim(int nim){
        this.nim = nim;
    }
    public String getnama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    private String nama;

}
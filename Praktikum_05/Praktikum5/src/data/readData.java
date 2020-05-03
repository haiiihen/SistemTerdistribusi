package data;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class readData {
    public static void main(String[] args){
        try{
            FileInputStream fis = new FileInputStream("db");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Employee dat1 = (Employee) ois.readObject();
            Employee dat2 = (Employee) ois.readObject();
            dat1.print();
            dat2.print();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
package data;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class saveData {
    public  static void main(String[] args){
        try{
            Employee sifa = new Employee("Sifa",28,2500000);
            Employee maswi = new Employee("Maswi",34,2800000);
            FileOutputStream fos = new FileOutputStream("db");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(sifa);
            oos.writeObject(maswi);
            oos.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
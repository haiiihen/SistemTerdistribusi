package praktikum3; 
public class myThread3 extends Thread { 
    private static int i=0; 
    public void run(){ 
        while(i<100){ 
            System.out.print(i + " , "); 
            i++; 
        } 
    } 
}
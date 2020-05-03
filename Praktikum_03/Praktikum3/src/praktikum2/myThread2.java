package praktikum2;

public class myThread2 implements Runnable { 
    private static int i=0; 
    Thread myth; public myThread2(){ 
        myth = new Thread(this); 
        myth.start(); 
    } 
    @Override 
    public void run() { 
        while(i<100){ 
            System.out.print(i+", "); 
            i++; 
        } 
    } 
} 

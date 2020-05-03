package praktikum1;

public class myThread implements Runnable{
    private static int i=0;

    @Override
    public void run(){
        while(i<100){
            System.out.println(i+",");
            i++;
        }
        
    }
}
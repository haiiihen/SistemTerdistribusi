package praktikum1;

public class myThreadTest{
    public static void main(String[] args){
        myThread ot = new myThread();
        Thread th = new Thread(ot);
        th.start();
        for(int j=0; j<10; j++){
            System.out.println("From Main" + j);
        }
    }
}
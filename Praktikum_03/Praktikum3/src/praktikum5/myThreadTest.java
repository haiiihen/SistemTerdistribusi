package praktikum5;

public class myThreadTest{
    public static void main(String[] args){
        myThread5 ot = new myThread5();
        Thread th = new Thread(ot);
        th.start();
        for(int j=0; j<10; j++){
            System.out.println("From Main" + j);
        }
    }
}
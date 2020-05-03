package praktikum3;

public class myThreadTest{
    public static void main(String[] args){
        myThread3 ot = new myThread3();
        Thread th = new Thread(ot);
        th.start();
        for(int j=0; j<10; j++){
            System.out.println("From Main" + j);
        }
    }
}
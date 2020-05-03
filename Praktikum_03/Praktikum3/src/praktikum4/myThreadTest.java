package praktikum4;

public class myThreadTest{
    public static void main(String[] args){
        myThread4 ot = new myThread4();
        Thread th = new Thread(ot);
        th.start();
        for(int j=0; j<10; j++){
            System.out.println("From Main" + j);
        }
    }
}
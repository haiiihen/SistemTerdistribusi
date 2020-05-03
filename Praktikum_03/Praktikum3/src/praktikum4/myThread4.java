package praktikum4;

public class myThread4 {
    public static void main(String[] args) { 
        myThread4 ot = new myThread4(); 
        Thread th = new Thread(ot); 
        th.start(); for(int j=0;j<10;j++){ 
            if (j==5){ try { th.sleep(1000); 
            } catch(Exception e){ 
                e.printStackTrace(); 
            } 
        } 
        System.out.println("From Main "+j); 
    } 
} 

}
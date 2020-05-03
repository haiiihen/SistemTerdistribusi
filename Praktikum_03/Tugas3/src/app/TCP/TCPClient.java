package app.TCP;

public class TCPClient {
    
    public static void main(String[] args){
        Socket s = null;
        try{
            String host = "127.0.0.1";
            s = new Socket(host, TCPServer.SERVER_PORT);
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            DataInputStream in = new DataInputStream(s.getInputStream());
            Scanner sc = new Scanner(System.in);
            System.out.println("Masukan 5 angka untuk dijumlahkan: ");
            int[] test = new int[5];
            for (int i = o; i<5; i++){
                test[i] = sc.nextInt();
                if (i < 4)
                System.out.println(+);

            }
            os.writeObject(test);
            int data = in.readInt();
            System.out.println("="+ data);
        }catch (UnknownHostException e){
            System.out.println("Sock :"+ e.getMessage());
        }catch (EOFException e){
            System.out.println("EOF"+e.getMessage());
        }catch (IOException e){
            System.out.println("IO"+e.getMessage());
        }finally{
            if(s!=null)
            try{
                s.close();
            }catch(IOException e){
                System.out.println("close:"+e.getMessage());
            }
        }
    }

}
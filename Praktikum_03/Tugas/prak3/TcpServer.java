package psister;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * Thread untuk 5 angka, nanti angkanya
 * disimpan di int[] summ dan hasil penjumalahannya
 * disimpan di result.
 */
class BatchSummation implements Runnable {
    Thread thread;
    int[] summ;
    int result = 0;

    BatchSummation(int[] arr) {
        this.summ = arr;

        this.thread = new Thread(this);

        this.thread.start();
    }

    public void run() {
        this.result = Arrays.stream(this.summ).sum();
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    /**
     * Untuk membagi array menjadi chucks, misal kita punya
     * array [1, 2, 3, 4, 5] terus chucks 2 maka jadi [[1, 2], [3, 4], [5]]
     * 
     * @param arrayToSplit
     * @param chunkSize
     * @return
     */
    public static int[][] splitArray(int[] arrayToSplit, int chunkSize) {
        if (chunkSize <= 0) {
            return null; // just in case :)
        }
        // first we have to check if the array can be split in multiple
        // arrays of equal 'chunk' size
        int rest = arrayToSplit.length % chunkSize; // if rest>0 then our last array will have less elements than the
                                                    // others
        // then we check in how many arrays we can split our input array
        int chunks = arrayToSplit.length / chunkSize + (rest > 0 ? 1 : 0); // we may have to add an additional array for
                                                                           // the 'rest'
        // now we know how many arrays we need and create our result array
        int[][] arrays = new int[chunks][];
        // we create our resulting arrays by copying the corresponding
        // part from the input array. If we have a rest (rest>0), then
        // the last array will have less elements than the others. This
        // needs to be handled separately, so we iterate 1 times less.
        for (int i = 0; i < (rest > 0 ? chunks - 1 : chunks); i++) {
            // this copies 'chunk' times 'chunkSize' elements into a new array
            arrays[i] = Arrays.copyOfRange(arrayToSplit, i * chunkSize, i * chunkSize + chunkSize);
        }
        if (rest > 0) { // only when we have a rest
            // we copy the remaining elements into the last chunk
            arrays[chunks - 1] = Arrays.copyOfRange(arrayToSplit, (chunks - 1) * chunkSize,
                    (chunks - 1) * chunkSize + rest);
        }
        return arrays; // that's it
    }

    public void run() {
        try { // an echo server
            String data = in.readUTF();

            /**
             * Parse the response, karena input dari
             * user itu 1,2,3,4,5,6 diubah jadi [1,2,3,4,5,6]
             */
            String[] splitted = data.split(",");

            int[] number = new int[splitted.length];
            Arrays.fill(number, 0);

            for (int i = 0; i < splitted.length; i++) {
                try {
                    number[i] = Integer.valueOf(splitted[i].trim());
                } catch (NumberFormatException e) {
                    number[i] = 0;
                }
            }

            // chuncks array 5-5
            int[][] chuncks = Connection.splitArray(number, 5);

            // buat array untuk nyimpen threadnya
            BatchSummation[] threads = new BatchSummation[chuncks.length];

            // Buat thread dan start thread
            for (int i = 0; i < chuncks.length; i++) {
                threads[i] = new BatchSummation(chuncks[i]);

                System.out.println("Spawning new thread with id:" 
                + threads[i].thread.getId() + " for number: " + Arrays.toString(chuncks[i]));
            }

            // joining semua thread, menunggu semua thread untul selesai
            // baru proses selanjutnya
            for (BatchSummation th: threads) {
                th.thread.join();
            }

            int result = 0;

            // menjumlahkan semua hasil dari thread
            for (BatchSummation th : threads) {
                result += th.result;
            }

            out.writeUTF(Integer.toString(result));
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("InterruptedException:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                // None
            }
        }
    }
}

public class TcpServer {
    public static void main(String[] args) {
        try {
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Server sedang running.........");
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }
}
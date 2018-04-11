package xyz.comfyz.learning.socket.batch;

import xyz.comfyz.learning.socket.Worker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : comfy create at 2018-04-09 13:41
 */
public class BatchServer {
    private ServerSocket serverSocket;
    private ThreadPool threadPool = new ThreadPool(4);

    private BatchServer() throws IOException {
        int port = 8000;
        serverSocket = new ServerSocket(port);
        System.out.println(String.format("Server start at http://localhost:%d", port));
    }

    public static void main(String[] args) throws IOException {
        new BatchServer().service();
    }

    private void service() {
        while (true) {
            Socket socket;
            try {
                socket = serverSocket.accept();
                threadPool.execute(new Worker(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

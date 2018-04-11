package xyz.comfyz.learning.socket.batch;

import xyz.comfyz.learning.socket.Worker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : comfy create at 2018-04-11 10:18
 */
public class JUCBatchServer {

    private ServerSocket serverSocket;
    private ExecutorService executorService;

    private JUCBatchServer() throws IOException {
        serverSocket = new ServerSocket(8000);
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);
    }

    public static void main(String[] args) throws IOException {
        new JUCBatchServer().service();
    }

    private void service() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                executorService.execute(new Worker(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

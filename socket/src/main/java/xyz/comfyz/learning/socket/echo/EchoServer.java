package xyz.comfyz.learning.socket.echo;


import xyz.comfyz.learning.socket.Worker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : comfy create at 2018-04-09 13:41
 */
public class EchoServer {
    private ServerSocket serverSocket;

    private EchoServer() throws IOException {
        int port = 8000;
        serverSocket = new ServerSocket(port);
        System.out.println(String.format("Server start at port : %d", port));
    }

    public static void main(String[] args) throws IOException {
        EchoServer server = new EchoServer();
        server.service();
    }

    private void service() {
        while (true) {
            Socket socket;
            try {
                socket = serverSocket.accept();
                Thread worker = new Thread(new Worker(socket));
                worker.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

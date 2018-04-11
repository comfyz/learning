package xyz.comfyz.learning.socket.simple;
/**
 * @author : comfy create at 2018-04-08 18:11
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    private ServerSocket serverSocket;

    public SimpleServer() throws IOException {
        final int port = 8000;
        serverSocket = new ServerSocket(port, 6);//连接请求队列的长度为
        System.out.println("服务器启动 : http://127.0.0.1:" + port);
    }

    public static void main(String args[]) throws Exception {
        SimpleServer server = new SimpleServer();
        Thread.sleep(1000 * 10);
//        server.service();
    }

    public void service() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();     //从连接请求队列中取出一个连接
                System.out.println("New connection accepted " +
                        socket.getInetAddress() + ":" + socket.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null) socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


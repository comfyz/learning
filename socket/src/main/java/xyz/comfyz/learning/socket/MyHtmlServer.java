package xyz.comfyz.learning.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : comfy create at 2018-04-11 09:02
 */
public class MyHtmlServer {
    public static void main(String[] args) throws IOException {
        int port = 8000;
        if (args.length > 0)
            port = Integer.valueOf(args[0]);
        new MyHtmlServer().start(port);

    }

    /**
     * 在指定端口启动http服务器
     *
     * @param port 指定的端口
     * @throws IOException
     */
    public void start(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("server start at " + port + "...........");
        while (true) {
            Socket client = server.accept();
            ServerThread serverthread = new ServerThread(client);
            serverthread.start();

        }
    }

    /**
     * 服务器响应线程，每收到一次浏览器的请求就会启动一个ServerThread线程
     *
     * @author
     */
    class ServerThread extends Thread {
        Socket client;

        public ServerThread(Socket client) {
            this.client = client;
        }

        /**
         * 根据用户请求的资源类型，设定http响应头的信息，主要是判断用户请求的文件类型（html、jpg...）
         *
         * @return
         */
        private String getHead() {
            return "HTTP/1.0 200 OK\r\n" + "Content-Type: text/plain; charset=utf-8\r\n" + "Server: myserver\r\n" + "\r\n";
        }

        @Override
        public void run() {
            try {
                InputStream in = client.getInputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                StringBuffer request = new StringBuffer();
                StringBuilder body = new StringBuilder(getHead());
                int var1;
                int $space = 0;
                while ((var1 = in.read()) > 0 && $space < 2) {
                    request.append((char) var1);
                    if (Character.isWhitespace(var1))
                        ++$space;
                }
                body.append(request.toString());
                System.out.println(request);
                System.out.println("-----华丽的分割线-----");
                System.out.println(body);
                writer.write(body.toString());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

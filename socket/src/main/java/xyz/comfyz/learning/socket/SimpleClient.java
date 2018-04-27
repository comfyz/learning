package xyz.comfyz.learning.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * @author : comfy create at 2018-04-09 11:46
 */
public class SimpleClient {
    public static void main(String args[]) throws Exception {
        final int length = 10;
        String host = "localhost";
        int port = 8000;

        Socket[] sockets = new Socket[length];
        for (int i = 0; i < length; i++) {
            try {
                sockets[i] = new Socket(host, port);
                sockets[i].getOutputStream().write(i);
                System.out.println("第" + (i + 1) + "次连接成功");
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        Thread.sleep(3000);
        for (int i = 0; i < length; i++)
            if (sockets[i] != null)
                sockets[i].close();      //断开连接
    }
}

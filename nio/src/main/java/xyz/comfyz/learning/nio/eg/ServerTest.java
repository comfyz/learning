package xyz.comfyz.learning.nio.eg;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author :    comfy
 * @date :      2018-04-26 11:16:11
 * @since :     1.8
 * <p>
 *
 * </p>
 */
public class ServerTest {

    public static void main(String[] args) {
        ServerTest serverTest = new ServerTest();
        serverTest.service();
    }

    public void service() {
        ServerSocketChannel ssc = null;
        SocketChannel sc = null;
        try {
            ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));

            while (true) {
                sc = ssc.accept();
                ByteBuffer buffer = ByteBuffer.allocate(6);
                ByteBuffer buffer2 = ByteBuffer.allocate(6);
                ByteBuffer[] buffers = new ByteBuffer[]{buffer, buffer2};
                sc.read(buffers);
                for (ByteBuffer b : buffers) {
                    System.out.println(">>>>>>>>>>>>>>>>>>>> received : " + new String(b.array()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ssc != null) {
                try {
                    ssc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                try {
                    sc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

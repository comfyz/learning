package xyz.comfyz.learning.nio.eg;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author :    comfy
 * @date :      2018-04-26 11:17:11
 * @since :     1.8
 * <p>
 *
 * </p>
 */
public class ClientTest {

    public static void main(String[] args) {
        ClientTest clientTest = new ClientTest();
        clientTest.start();
    }

    public void start() {
        SocketChannel sc = null;
        try {
            sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("127.0.0.1", 8000));

            ByteBuffer buffer = ByteBuffer.allocate(12);
            ByteBuffer buffer2 = ByteBuffer.allocate(10);
            buffer.put("hello ".getBytes());
            buffer2.put("world".getBytes());

            buffer.flip();
            buffer2.flip();
            sc.write(new ByteBuffer[]{buffer, buffer2});

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

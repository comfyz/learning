package xyz.comfyz.learning.nio.nonBlocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author :    comfy
 * @date :      2018-04-27 11:29:24
 * @since :     1.8
 * <p>
 *
 * </p>
 */
public class NioClient {
    private Selector selector;
    private SocketChannel socket;

    public NioClient(String ip, int port) throws IOException {
        try {
            socket = SocketChannel.open();
            socket.configureBlocking(false);
            socket.connect(new InetSocketAddress(ip, port));
            selector = Selector.open();
            socket.register(selector, SelectionKey.OP_CONNECT);
        } catch (IOException e) {
            close();
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        new NioClient("127.0.0.1", 8000).work();
    }

    public void work() throws IOException {
        System.out.println("The client starts to work");
        while (true) {
            try {
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if (key.isConnectable()) {
                        socket = (SocketChannel) key.channel();
                        if (socket.isConnectionPending()) {
                            socket.finishConnect();
                        }

                        socket.configureBlocking(false);
                        socket.write(ByteBuffer.wrap("send msg to server".getBytes()));
                        socket.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        socket = (SocketChannel) key.channel();
                        ByteBuffer b = ByteBuffer.allocate(128);
                        StringBuilder sb = new StringBuilder();
                        while (socket.read(b) > 0) {
                            b.flip();
                            sb.append(new String(b.array()));
                            b.clear();
                        }
                        System.out.println("receive msg from server : " + sb);
                    }
                }
            } catch (IOException e) {
                throw new IOException(e.getLocalizedMessage());
            }
        }
    }

    private void close() throws IOException {
        try {
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            throw new IOException(e.getLocalizedMessage());
        }
        try {
            if (selector != null)
                selector.close();
        } catch (IOException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }
}

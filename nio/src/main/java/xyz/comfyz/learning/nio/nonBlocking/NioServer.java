package xyz.comfyz.learning.nio.nonBlocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author :    comfy
 * @date :      2018-04-27 10:53:33
 * @since :     1.8
 * <p>
 *
 * </p>
 */
public class NioServer {
    private Selector selector;
    private ServerSocketChannel server;

    public NioServer(String ip, int port) throws IOException {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(ip, port));
            selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            close();
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        new NioServer("127.0.0.1", 8000).work();
    }

    public void work() {
        System.out.println("The server starts up");
        while (true) {
            try {
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if (key.isAcceptable()) {
                        server = (ServerSocketChannel) key.channel();
                        SocketChannel socket = server.accept();
                        System.out.println("new connection from " + socket.getLocalAddress() + " : " + socket.getRemoteAddress());
                        socket.configureBlocking(false);
                        socket.write(ByteBuffer.wrap("send msg to client".getBytes()));
                        socket.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel socket = (SocketChannel) key.channel();
                        ByteBuffer b = ByteBuffer.allocate(128);
                        StringBuilder sb = new StringBuilder();
                        try {
                            while (socket.read(b) > 0) {
                                b.flip();
                                sb.append(new String(b.array()));
                                b.clear();
                            }
                        } catch (IOException e) {
                            System.out.println(e.getLocalizedMessage());
                            socket.close();
                        }
                        System.out.println("receive msg from client : " + sb);
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    private void close() throws IOException {
        try {
            if (server != null)
                server.close();
        } catch (IOException ioe) {
            throw new IOException(ioe.getLocalizedMessage());
        }
        try {
            if (selector != null)
                selector.close();
        } catch (IOException ioe) {
            throw new IOException(ioe.getLocalizedMessage());
        }
    }
}
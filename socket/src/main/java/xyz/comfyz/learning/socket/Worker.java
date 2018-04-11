package xyz.comfyz.learning.socket;

/**
 * @author : comfy create at 2018-04-10 17:43
 */

import java.io.*;
import java.net.Socket;

public class Worker implements Runnable {

    private final Socket socket;

    public Worker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            StringBuilder body = new StringBuilder(header());
            String readLine;
            if ((readLine = reader.readLine()) != null) {
                body.append(readLine);
            }
            writer.write(body.toString());
            writer.flush();
            writer.close();
            System.out.println(String.format("New connection accepted %s:%d ", socket.getInetAddress().toString(), socket.getPort()) + "------------------begin------------------");
            System.out.println("[request  --- begin]");
            System.out.println(readLine);
            System.out.println("[request  --- end]\r\n");
            System.out.println("[response --- begin]");
            System.out.println(body);
            System.out.println("[response --- end]");
            System.out.println(String.format("New connection accepted %s:%d ", socket.getInetAddress().toString(), socket.getPort()) + "------------------end--------------------");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String header() {

        return "HTTP/1.0 200 OK\n" + "Content-Type:text/plain;charset=utf-8\n" + "Server:BatchServer\n" + "\n";

    }

}

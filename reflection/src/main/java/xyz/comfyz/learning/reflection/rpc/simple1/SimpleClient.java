package xyz.comfyz.learning.reflection.rpc.simple1;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleClient {

    public static void main(String args[]) throws Exception {
        new SimpleClient().invoke();
    }

    public void invoke() throws Exception {
        Socket socket = new Socket("localhost", 8000);
        OutputStream out = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        InputStream in = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);

        Call call = new Call("xyz.comfyz.learning.reflection.rpc.simple1.HelloService", "getTime", new Class[]{}, new Object[]{});
//        Call call = new Call("xyz.comfyz.learning.reflection.rpc.simple1.HelloService", "echo",
//                new Class[]{String.class}, new Object[]{"Hello"});
        oos.writeObject(call); //向服务器发送 Call 对象
        call = (Call) ois.readObject(); //接收包含了方法执行结果的 Call 对象
        System.out.println(call.getResult());
        ois.close();
        oos.close();
        socket.close();
    }
}
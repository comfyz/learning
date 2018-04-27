package xyz.comfyz.learning.reflection.rpc.simple1;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SimpleServer {
    private Map<String, Object> remoteObjects = new HashMap<>(); //存放远程对象的缓存

    public static void main(String args[]) throws Exception {
        SimpleServer server = new SimpleServer();
        //把事先创建的 HelloServiceImpl 对象加入到服务器的缓存中
        server.register("xyz.comfyz.learning.reflection.rpc.simple1.HelloService", new HelloServiceImpl());
        server.service();
    }

    /**
     * 把一个远程对象放到缓存中
     */
    public void register(String className, Object remoteObject) {
        remoteObjects.put(className, remoteObject);
    }

    public void service() throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("服务器启动.");
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream in = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            OutputStream out = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Call call = (Call) ois.readObject(); //接收客户发送的 Call 对象
            System.out.println(call);
            call = invoke(call); //调用相关对象的方法
            oos.writeObject(call); //向客户发送包含了执行结果的 Call 对象

            ois.close();
            oos.close();
            socket.close();
        }
    }

    public Call invoke(Call call) {
        Object result = null;
        try {
            String className = call.getClassName();
            String methodName = call.getMethodName();
            Object[] params = call.getParams();
            Class classType = Class.forName(className);
            Class[] paramTypes = call.getParamTypes();
            Method method = classType.getMethod(methodName, paramTypes);
            Object remoteObject = remoteObjects.get(className); //从缓存中取出相关的远程对象
            if (remoteObject == null) {
                throw new Exception(className + "的远程对象不存在");
            } else {
                result = method.invoke(remoteObject, params);
            }
        } catch (Exception e) {
            result = e;
        }
        call.setResult(result); //设置方法执行结果
        return call;
    }
}
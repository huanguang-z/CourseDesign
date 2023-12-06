package IO;

import java.io.*;
import java.net.Socket;

public class IOStream {               //从Socket中读取对象
    public static Object readObject(Socket socket)
    {
        Object obj= null;
        try {
            InputStream is =socket.getInputStream();
            ObjectInputStream ois=new ObjectInputStream(is);
            obj = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }//处理readObject()方法抛出的异常
        return obj;
    }


    //根据Socket管道写出消息

    public static void writeMessage(Socket socket,Object message)
    {
        try {
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(message);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

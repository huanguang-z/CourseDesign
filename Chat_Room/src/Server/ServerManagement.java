package Server;

import Server.Entity.ChatStatus;
import Server.Entity.TransferInfo;
import IO.IOStream;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

/**
 * 服务器端开辟一个线程，一直读消息
 * */


public class ServerManagement extends Thread{
    Socket socket;
    Server serverFrame;
    public ServerManagement(Socket socket, Server serverFrame)
    {
        this.socket=socket;
        this.serverFrame=serverFrame;
    }

    public static List<String> onlineUsers= new ArrayList<>();
    public static List<Socket> onlineSockets=new ArrayList<>();

    public void run()
    {
        while(true)
        {
            try{
            Object obj= IOStream.readObject(socket);
            if (obj instanceof TransferInfo)
            {
                TransferInfo tif=(TransferInfo) obj;

                if(tif.getStatusEnum()== ChatStatus.LOGIN){
                    //这是登录消息
                    loginHandler(tif);
                }
                else if(tif.getStatusEnum()==ChatStatus.CHAT){
                    //这是聊天消息
                    chatHandler(tif);
                }
                else if(tif.getStatusEnum()==ChatStatus.SHAKE){
                    //这是抖动消息
                    shake(tif);
                }
                else if(tif.getStatusEnum()==ChatStatus.QUIT)
                {
                    //退出处理

                    loginOut(tif);

                    //休眠1秒后
                    Thread.sleep(1000);
                    //关闭当前socket
                    socket.close();
                    //关闭当前线程
                    this.interrupt();
                    //跳出循环
                    break;
                }
                else if(tif.getStatusEnum()==ChatStatus.REMOVE)
                {
                    //remove(tif);
                    loginOut2(tif);

                    //休眠1秒后
                    Thread.sleep(1000);
                    //关闭当前socket
                    socket.close();
                    //关闭当前线程
                    this.interrupt();
                    //跳出循环
                    break;
                }

            }
            Thread.sleep(1000);
            }catch(InterruptedException | IOException e){
                e.printStackTrace();
            }
        }
    }

    public  void loginOut(TransferInfo tfi) {
        String userName = tfi.getUserName();
        //将该用户从用户集合移除
        Iterator<String> userIter = onlineUsers.iterator();
        while(userIter.hasNext()) {
            if(userIter.next().equals(userName)) {
                userIter.remove();
            }
        }
        //将该用户从socket集合移除
        Iterator<Socket> socketIter = onlineSockets.iterator();
        while(socketIter.hasNext()) {
            Socket next = socketIter.next();
            if(socket == next) {
                socketIter.remove();
            }
        }

        //将user与Socket的关系从Map中移除
        ChatServer.userSocketMap.remove(userName);

        String notice=userName+"退出群聊";
        //刷新服务器面板的用户列表
        flushOnlineUserList();

        //给所有在线的用户发送下线消息
        tfi.setStatusEnum(ChatStatus.NOTICE);
        sendAll(tfi);

        //告诉其他人刷新用户列表
        tfi.setUserOnlineArray(onlineUsers.toArray(new String [onlineUsers.size()]));
        tfi.setStatusEnum(ChatStatus.ULIST);
        sendAll(tfi);

        log(notice);
    }

    public  void loginOut2(TransferInfo tfi) {
        String userName = tfi.getUserName();
        //将该用户从用户集合移除
        Iterator<String> userIter = onlineUsers.iterator();
        while(userIter.hasNext()) {
            if(userIter.next().equals(userName)) {
                userIter.remove();
            }
        }
        //将该用户从socket集合移除
        Iterator<Socket> socketIter = onlineSockets.iterator();
        while(socketIter.hasNext()) {
            Socket next = socketIter.next();
            if(socket == next) {
                socketIter.remove();
            }
        }

        //将user与Socket的关系从Map中移除
        ChatServer.userSocketMap.remove(userName);

        String notice=userName+"被服务器移除";
        //刷新服务器面板的用户列表
        flushOnlineUserList();

        //给所有在线的用户发送下线消息
        tfi.setStatusEnum(ChatStatus.NOTICE);
        sendAll(tfi);

        //告诉其他人刷新用户列表
        tfi.setUserOnlineArray(onlineUsers.toArray(new String [onlineUsers.size()]));
        tfi.setStatusEnum(ChatStatus.ULIST);
        sendAll(tfi);

        log(notice);
    }

    /**
     * 登录功能
     * */
    public boolean checkUserLogin(TransferInfo tif)
    {
        try {
            String userName=tif.getUserName();
            String passWord=tif.getPassword();
            FileInputStream fis=new FileInputStream(new File("user.txt"));
            DataInputStream dis=new DataInputStream(fis);
            String row=null;
            while((row=dis.readLine())!=null){
                //从文件中读取的行
                String fileUser=row;
                if((userName+"|"+passWord).equals(fileUser))
                {
                    System.out.println("用户名密码正确");
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 登录处理，处理客户端的登录请求
     * */
    private void loginHandler(TransferInfo tif)
    {
        boolean flag= checkUserLogin(tif);
        tif.setLoginSucceessFlag(false);

        if(flag)
        {
            //返回登录成功给客户端
            tif.setLoginSucceessFlag(true);
            tif.setStatusEnum(ChatStatus.LOGIN);
            IOStream.writeMessage(socket,tif);
            String Username=tif.getUserName();

            //统计在线人数
            onlineUsers.add(Username);
            onlineSockets.add(socket);
            //在线人和管道的对应关系
            ChatServer.userSocketMap.put(Username,socket);
            //发消息给客户端，该用户已上线
            tif=new TransferInfo();
            tif.setStatusEnum(ChatStatus.NOTICE);
            String notice=">>>"+ Username+","+"欢迎您来到群聊"+"\n";
            tif.setNotice(notice);
            sendAll(tif);

            //准备最新用户列表到客户端
            tif=new TransferInfo();
            tif.setUserOnlineArray(onlineUsers.toArray(new String[onlineUsers.size()]));
            tif.setStatusEnum(ChatStatus.ULIST);
            sendAll(tif);

            //刷新在线用户列表
            flushOnlineUserList();
            //记录日志
            log(notice);

        }
        else
        {
            //返回登录失败给客户端
            IOStream.writeMessage(socket,tif);
            log(tif.getUserName()+"登录失败");
        }
    }

    /***
     * 日志文件
     */

    private void log(String log)
    {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString=sdf.format(date);
        JTextPane txtLog=serverFrame.serverInfoPanel.txtLog;
        String oldLog=txtLog.getText();
        txtLog.setText(oldLog+"\n"+dateString+" "+log);
    }





    /**
     * 刷新用户列表
     * */
    public void flushOnlineUserList()
    {
        JList lstUser=serverFrame.onlineUserPanel.lstUser;
        String [] UserArray=onlineUsers.toArray(new String [onlineUsers.size()]);
        lstUser.setListData(UserArray);
        serverFrame.serverInfoPanel.txtNumber.setText(UserArray.length+"");
    }




    /**
     * 发送消息给所有人
     * */
    public void sendAll(TransferInfo tif)
    {
        for (int i=0; i<onlineSockets.size(); i++) {
            Socket tempSocket = onlineSockets.get(i);
            IOStream.writeMessage(tempSocket,tif);
        }
    }

    /**
     * 处理客户端聊天请求
     * */

    private void chatHandler(TransferInfo tif)
    {
        //转发给其它用户
        String reciever=tif.getReciver();
        if("所有人".equals(reciever))
        {
            //发送给所有人
            sendAll(tif);
            //记录日志
            log(tif.getSender()+"给所有人发消息");
        }
        else
        {
            send(tif);
            log(tif.getSender()+"给"+tif.getReciver()+"发消息");
        }
    }

    private void shake(TransferInfo tif)
    {
        //转发给其它用户
        String reciever=tif.getReciver();
        if("所有人".equals(reciever))
        {
            //发送给所有人
            sendAll(tif);
            log(tif.getSender()+"给所有人发抖动消息");
        }else
        {
            send(tif);
            log(tif.getSender()+"对"+tif.getReciver()+"给所有人发抖动消息");
        }
    }

    private void send (TransferInfo tif)
    {
        //发送给私聊的接受者
        String receiver=tif.getReciver();
        String sender=tif.getSender();
        Socket socket1=ChatServer.userSocketMap.get(receiver);
        IOStream.writeMessage(socket1,tif);
        //根据receiver拿到Socket
        Socket socket2=ChatServer.userSocketMap.get(sender);
        IOStream.writeMessage(socket2,tif);

    }


    public void remove(TransferInfo tif)
    {
        String remove="you are removed3352$%&234";
        tif.setServerContent(remove);
        System.out.println("rferwer");
        IOStream.writeMessage(socket,tif);
    }

}

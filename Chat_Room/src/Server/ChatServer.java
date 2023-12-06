package Server;
//服务器启动入口

import Server.Entity.ServerInfo;
import Constants.Port;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ChatServer {
    public Server serverFrame;
    public static Map<String,Socket> userSocketMap=new HashMap<>();
    public ChatServer()
    {
        try {
            //建立服务器的Socket监听
            ServerSocket sso=new ServerSocket(Port. SERVER_PORT);
            serverFrame=new Server();             //显示服务器窗口
            //初始化服务器参数信息
            ServerInfo serverInfo=getServerIP();
            loadServerInfo(serverInfo);
            //等待连接，阻塞实现，会得到一个客户端的连接
            while(true) {
                Socket socket = sso.accept();

                ServerManagement serverHandler = new ServerManagement(socket,serverFrame);
                serverHandler.start();
                InputStream is = socket.getInputStream();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //服务器参数
    public void loadServerInfo(ServerInfo serverInfoBean)
    {
        serverFrame.serverInfoPanel.txtIP.setText(serverInfoBean.getIP());
        serverFrame.serverInfoPanel.txtServerName.setText(serverInfoBean.getHostName());
        serverFrame.serverInfoPanel.txtLog.setText("服务器已启动.... ");
    }
    public ServerInfo getServerIP()
    {
        ServerInfo sib = null;
        try {
            InetAddress serverAddress = InetAddress.getLocalHost();
            byte[] ipAddress = serverAddress.getAddress();
            sib = new ServerInfo();
            sib.setIP(serverAddress.getHostAddress());
            sib.setHostName(serverAddress.getHostName());
            sib.setPort(Port. SERVER_PORT);

            System.out.println("Server IP is:" + (ipAddress[0] & 0xff) + "."
                    + (ipAddress[1] & 0xff) + "." + (ipAddress[2] & 0xff) + "."
                    + (ipAddress[3] & 0xff));
        } catch (Exception e) {
            System.out.println("###Cound not get Server IP." + e);
        }
        return sib;

    }
    public static void main(String[] args)
    {
        new ChatServer();
    }
}

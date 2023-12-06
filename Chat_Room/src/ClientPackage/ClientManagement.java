package ClientPackage;

import Server.Entity.ChatStatus;
import Server.Entity.FontStyle;
import Server.Entity.TransferInfo;
import Server.Entity.UserInformation;
import Ulist.Renderer;
import Ulist.ListModel;
import Util.FontSupport;
import IO.IOStream;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.List;

/**
 * 客户端开辟取消息的线程
 * */



public class ClientManagement extends Thread{
    Socket socket;
    Login loginFrame;
    Chat chatFrame;
    public ClientManagement(Socket socket, Login loginFrame)
    {
        this.socket=socket;
        this.loginFrame=loginFrame;
    }

    public void run()
    {
        while(true)
        {
            System.out.println("客户端的循环");
            try {
                Object obj= IOStream.readObject(socket);
                if(obj instanceof TransferInfo){
                    TransferInfo tif=(TransferInfo) obj;
                    if(tif.getStatusEnum()== ChatStatus.LOGIN){
                        //这是登录消息
                        loginResult(tif);
                        System.out.println(tif.getSender()+" "+tif.getUserName());
                    }
                    else if(tif.getStatusEnum()==ChatStatus.CHAT){
                        //这是聊天消息
                        chatResult(tif);
                    }
                    else if(tif.getStatusEnum()==ChatStatus.NOTICE) {
                        //这是系统消息
                        noticeResult(tif);
                    }
                    else if(tif.getStatusEnum()==ChatStatus.ULIST){
                        //刷新当前用户列表
                        onlineUserResult(tif);
                    }
                    else if(tif.getStatusEnum()==ChatStatus.SHAKE)
                    {
                        shakeResult(tif);
                    }
                    else if (tif.getStatusEnum()==ChatStatus.REMOVE)
                    {
                        removeUser(tif);
                        System.out.println(tif.getSender()+" "+tif.getUserName());
                        return;
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 接收抖动信息
     * */
    private void shakeResult(TransferInfo tif)
    {
        Shake cfs=new Shake(chatFrame);
        cfs.start();
    }


    /**
     * 登录消息处理
     **/
    private void loginResult(TransferInfo tif)
    {
        if(tif.getLoginSucceessFlag())
        {
            String username=tif.getUserName();
            //登录成功
            chatFrame=new Chat(username,socket,this);
            loginFrame.dispose();   //关闭登录窗口
        }
        else
        {
            //登录失败
            System.out.println("客户端接收到登录失败");
        }
    }

    /**
     * 聊天消息处理
     * */
    public void chatResult(TransferInfo tif)
    {
        String sender=tif.getSender();
        List<FontStyle> content=tif.getContent();
        FontSupport.fontDecode(chatFrame.acceptPane,content,sender,tif.getReciver());

    }
    /**
     * 系统消息处理
     * */
    public void noticeResult(TransferInfo tif)
    {
        FontSupport.contentAppend(chatFrame.acceptPane, "\n"+tif.getNotice());
//        String text=chatFrame.acceptPane.getText();
//        chatFrame.acceptPane.setText(text+"\n"+tif.getNotice());
    }
    /**
     * 当前在线用户列表
     * */
    public void onlineUserResult(TransferInfo tif)
    {
        String[] userOnlineArray=tif.getUserOnlineArray();
        ListModel model=new ListModel();
        for (String uname:userOnlineArray){
            UserInformation user=new UserInformation();
            user.setUsername(uname);
            user.setUiconPath("images/uicon/"+uname+".png");
            model.addElement(user);
        }

        //Jlist 的模型，存放数据的
        chatFrame.lstUser.setModel(model);
        //提供自定义样式

        chatFrame.lstUser.setCellRenderer(new Renderer());
        // chatFrame.lstUser.setListData(userOnlineArray);
    }

    public void removeUser(TransferInfo tif)
    {
        if(tif.getServerContent().equals("you are removed3352$%&234"))
        {
            try {
                System.out.println(tif.getUserName() + "窗口关闭");
                TransferInfo tfi = new TransferInfo();
                tfi.setStatusEnum(ChatStatus.REMOVE);
                tfi.setSender(tif.getSender());
                tfi.setUserName(tif.getUserName());
                tfi.setNotice(tif.getUserName() + "已被踢出聊天室.....");
                IOStream.writeMessage(socket, tfi);
                System.out.println(tfi.getUserName());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            chatFrame.addWindowListener(new WindowAdapter() {
                                            @Override
                                            public void windowClosing(WindowEvent e) {

                                            }
                                        }

            );
            chatFrame.dispose();


        }
    }
    public void out(){
        chatFrame.dispose();
    }
}

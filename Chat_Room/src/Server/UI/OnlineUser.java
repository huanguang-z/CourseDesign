package Server.UI;

import Server.ChatServer;
import Server.Server;
import Server.ServerManagement;
import Server.Entity.ChatStatus;
import Server.Entity.TransferInfo;
import IO.IOStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

/**服务端第二个选项卡，在线用户*/
public class OnlineUser {

    //用户列表
    public JList lstUser;
    public JTextPane sendPane;
    Socket socket;
    public JComboBox remove_box;

    Server serverFrame;
    public JLabel getUserPanel()
    {
        JPanel pnlUser = new JPanel();
        pnlUser.setLayout(null);
        pnlUser.setBackground(new Color(52, 130, 203));
        pnlUser.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        pnlUser.setBounds(50, 5, 300, 400);
        pnlUser.setOpaque(false);//设置透明

        JLabel lblUser = new JLabel("[在线用户列表]");
        lblUser.setFont(new Font("宋体", 0, 16));
        lblUser.setBounds(50, 10, 200, 30);
        pnlUser.add(lblUser);



        lstUser = new JList();
        lstUser.setFont(new Font("宋体", 0, 14));
        lstUser.setVisibleRowCount(17);
        lstUser.setFixedCellWidth(180);
        lstUser.setFixedCellHeight(18);
        lstUser.setOpaque(false);


        /**
         * 踢出用户
         * */
        JPopupMenu popupMenu=new JPopupMenu();
        JMenuItem remove=new JMenuItem("踢出");
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object removeObj=lstUser.getSelectedValue();
                TransferInfo tif=new TransferInfo();
                if(removeObj!=null)
                {
                    String removee=String.valueOf(removeObj);

                    for(String s:ChatServer.userSocketMap.keySet()){
                    if(s.equals(removee)){
                            Socket so=ChatServer.userSocketMap.get(s);
                            String remove="you are removed3352$%&234";
                            tif.setServerContent(remove);
                            tif.setUserName(removee);
                            tif.setStatusEnum(ChatStatus.REMOVE);
                            System.out.println("rferwer");
                            IOStream.writeMessage(so,tif);
                    }

                    }
                }


                }

        });
        popupMenu.add(remove);
        lstUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //监听左键还是右键
                if(e.isMetaDown()){
                    //弹出菜单
                    popupMenu.show(lstUser,e.getX(),e.getY());
                }
            }
        });

        JScrollPane spUser = new JScrollPane(lstUser);
        spUser.setFont(new Font("宋体", 0, 14));
        spUser.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        spUser.setBounds(50, 35, 200, 360);
        spUser.setOpaque(false);
        pnlUser.add(spUser);

        ImageIcon imageIcon=new ImageIcon("images/Server.png");
        JLabel lblBackground=new JLabel(imageIcon);
        lblBackground.setBounds(0,200,300,300);
        lblBackground.add(pnlUser);


        lblBackground.add(jb_send());

        JLabel ServerInfo= new JLabel("[发送系统消息]");
        ServerInfo.setFont(new Font("宋体",0,16));
        ServerInfo.setBounds(360,-80,200,300);
        lblBackground.add(ServerInfo);

        sendPane = new JTextPane();
        sendPane.setOpaque(false);
        sendPane.setFont(new Font("宋体", 0, 16));

        JScrollPane scoPane = new JScrollPane(sendPane);// 设置滚动条
        scoPane.setBounds(360, 80, 150, 100);
        scoPane.setOpaque(false);
        scoPane.getViewport().setOpaque(false);
        lblBackground.add(scoPane);

        return lblBackground;
    }




    //发送系统信息
    public JButton jb_send()
    {
        JButton jb_send=new JButton("发 送");
        jb_send.setBackground(Color.white);
        jb_send.setFont(new Font("宋体", 0, 14));
        jb_send.setBounds(380, 200, 110, 30);
        jb_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransferInfo tif=new TransferInfo();
                tif.setNotice(">>> 系统消息:  "+sendPane.getText());
                String reciever="所有人";
                tif.setStatusEnum(ChatStatus.NOTICE);
                sendALL(tif);
                sendPane.setText("");
            }
        });
        return jb_send;
    }

    private void sendALL(TransferInfo tif) {
        for (int i = 0; i< ServerManagement.onlineSockets.size(); i++) {
            Socket tempSocket = ServerManagement.onlineSockets.get(i);
            IOStream.writeMessage(tempSocket,tif);
        }
    }
}

package ClientPackage;

import Server.Entity.ChatStatus;
import Server.Entity.FontStyle;
import Server.Entity.TransferInfo;
import Server.Entity.UserInformation;
import Ulist.Renderer;
import Ulist.ListModel;
import Util.FontSupport;
import IO.IOStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

public class Chat extends JFrame {
    private static final long serialVersionUID=-234234234234L;

    /**
     * 服务器窗体宽度
     */
    public static final Integer FRAME_WIDTH = 750;

    /**
     * 服务器窗体高度
     */
    public static final Integer FRAME_HEIGHT = 600;
    ClientManagement temp;

    //接收框
    public JTextPane acceptPane;

    //当前在线用户列表
    public JList lstUser;

    Chat chatFrame;
    public JComboBox reciverBox;
    public JComboBox fontFamilyCmb;

    String Username;
    Socket socket;

    public Chat(String Username, Socket socket, ClientManagement t) {
        temp=t;
        chatFrame=this;
        this.socket=socket;
        this.Username=Username;
        this.setTitle("聊天室主界面byZwr");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        //窗体不可扩大
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //获取屏幕
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        //屏幕居中处理
        setLocation((width-FRAME_WIDTH)/2, (height-FRAME_HEIGHT)/2);

        //加载窗体的背景图片
        ImageIcon imageIcon = new ImageIcon("images/bk.png");
        //创建一个标签并将图片添加进去
        JLabel frameBg = new JLabel(imageIcon);
        //设置图片的位置和大小
        frameBg.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        this.add(frameBg);

        // 接收框
        acceptPane = new JTextPane();
        acceptPane.setOpaque(false);//设置透明
        acceptPane.setText("");
        acceptPane.setFont(new Font("宋体", 0, 16));

        // 设置接收框滚动条
        JScrollPane scoPaneOne = new JScrollPane(acceptPane);
        scoPaneOne.setBounds(15, 20, 500, 332);
        //设置背景透明
        scoPaneOne.setOpaque(false);
        scoPaneOne.getViewport().setOpaque(false);
        frameBg.add(scoPaneOne);


        //当前在线用户列表
        lstUser = new JList();
        lstUser.setFont(new Font("宋体", 0, 16));
        lstUser.setVisibleRowCount(17);
        lstUser.setFixedCellWidth(180);
        lstUser.setFixedCellHeight(60);

        ListModel model=new ListModel();
        UserInformation user=new UserInformation();
        user.setUsername(Username);
        user.setUiconPath("images/uicon/zwr.png");
        model.addElement(user);
        //Jlist 的模型，存放数据的
        lstUser.setModel(model);
        //提供自定义样式

        lstUser.setCellRenderer(new Renderer());

        //声明菜单
        JPopupMenu popupMenu=new JPopupMenu();    //私聊按钮
        //声明菜单项
        JMenuItem privateChat=new JMenuItem("私聊");
        privateChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //做一系列动作
                Object recieverObj=lstUser.getSelectedValue();
                if(recieverObj instanceof UserInformation){
                    UserInformation user=(UserInformation)recieverObj;
                    String reciever=user.getUsername();
                    reciverBox.removeAllItems();
                    reciverBox.addItem("所有人");
                    reciverBox.addItem(reciever);
                    reciverBox.setSelectedItem(reciever);
                }
            }
        });
        popupMenu.add(privateChat);
        //声明菜单项
        JMenuItem AddFriend=new JMenuItem("添加好友");
        privateChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //做一系列动作
                Object receiverObj =lstUser.getSelectedValue();
                if(receiverObj instanceof UserInformation){
                    UserInformation user=(UserInformation) receiverObj;
                    String receiver =user.getUsername();
                    reciverBox.removeAllItems();
                    reciverBox.addItem("所有人");
                    reciverBox.addItem(receiver);
                    reciverBox.setSelectedItem(receiver);
                }
            }
        });
        popupMenu.add(AddFriend);
        //添加点击事件（确认是右键）
        lstUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //监听左键还是右键
                if(e.isMetaDown()){
                    //弹出菜单
                    if(lstUser.getSelectedIndex() >= 0){
                        popupMenu.show(lstUser,e.getX(),e.getY());
                    }
                }

            }
        });

        JScrollPane spUser = new JScrollPane(lstUser);
        spUser.setFont(new Font("宋体", 0, 14));
        spUser.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        spUser.setBounds(530, 17, 200, 507);
        frameBg.add(spUser);


        // 输入框
        JTextPane sendPane = new JTextPane();
        sendPane.setOpaque(false);
        sendPane.setFont(new Font("宋体", 0, 16));

        JScrollPane scoPane = new JScrollPane(sendPane);// 设置滚动条
        scoPane.setBounds(15, 400, 500, 122);
        scoPane.setOpaque(false);
        scoPane.getViewport().setOpaque(false);
        frameBg.add(scoPane);

        // 添加表情选择
        JLabel lblface = new JLabel(new ImageIcon("images/face.png"));
        lblface.setBounds(14, 363, 25, 25);
        lblface.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Expression face=new Expression(sendPane);
            }
        });
        frameBg.add(lblface);

        // 添加抖动效果
        JLabel lblshake = new JLabel(new ImageIcon("images/shake.png"));
        lblshake.setBounds(43, 363, 25, 25);
        lblshake.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Shake(chatFrame).start();
                TransferInfo tif=new TransferInfo();
                tif.setStatusEnum(ChatStatus.SHAKE);
                tif.setSender(Username);
                String reciver="所有人";
                Object recieverObj=reciverBox.getSelectedItem();
                if(recieverObj!=null)
                {
                    reciver=String .valueOf(recieverObj);
                }
                //获取当前发送给谁
                //接收人
                tif.setReciver(reciver);
                IOStream.writeMessage(socket,tif);
            }

        });
        frameBg.add(lblshake);

        // 设置字体选择
        JLabel lblfontChoose = new JLabel(new ImageIcon("images/character.png"));
        lblfontChoose.setBounds(44, 363, 80, 25);
        lblfontChoose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JColorChooser colorChooser = new JColorChooser();
                Color color = colorChooser.showDialog(Chat.this, "字体颜色", Color.BLACK);
                FontSupport.setFont(sendPane,color,fontFamilyCmb.getSelectedItem().toString(), Font.BOLD, 60);
            }
        });
        frameBg.add(lblfontChoose);

        //字体下拉选项
        fontFamilyCmb = new JComboBox();
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        String[] str = graphicsEnvironment.getAvailableFontFamilyNames();
        for (String string : str) {
            fontFamilyCmb.addItem(string);
        }
        fontFamilyCmb.setSelectedItem("楷体");
        fontFamilyCmb.setBounds(104, 363, 150, 25);
        frameBg.add(fontFamilyCmb);



        //label标签
        JLabel reciverLabel = new JLabel("聊天对象");
        reciverLabel.setBounds(304, 363, 80, 25);
        frameBg.add(reciverLabel);

        //下拉选择框
        reciverBox = new JComboBox();
        reciverBox.setSelectedItem("全体成员");
        reciverBox.addItem("全体成员");
        reciverBox.setBounds(374, 363, 150, 25);
        frameBg.add(reciverBox);
        /*
         * 发送按钮
         */
        JButton send = new JButton("发 送");
        send.setBounds(15, 533, 125, 25);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransferInfo tif=new TransferInfo();
                //包装了所有文字对应的属性
                java.util.List<FontStyle> fontStyles=FontSupport.fontEncode(sendPane);
                tif.setContent(fontStyles);
                //发送人
                tif.setSender(Username);
                String reciver="全体成员";
                Object recieverObj=reciverBox.getSelectedItem();
                if(recieverObj!=null)
                {
                    reciver=String .valueOf(recieverObj);
                }
                //获取当前发送给谁
                //接收人
                tif.setReciver(reciver);
                tif.setStatusEnum(ChatStatus.CHAT);
                IOStream.writeMessage(socket,tif);
                sendPane.setText("");
            }
        });
        frameBg.add(send);
        //客户端关闭窗体退出

        quit();

        setVisible(true);
    }

    //退出登录
    public void quit()
    {
        chatFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    System.out.println(Username + "窗口关闭");
                    TransferInfo tfi = new TransferInfo();
                    tfi.setStatusEnum(ChatStatus.QUIT);
                    tfi.setUserName(Username);
                    tfi.setNotice(Username + "已离开聊天室.....");

                    IOStream.writeMessage(socket, tfi);
                    chatFrame.dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
                temp.out();
            }
        });
    }


}

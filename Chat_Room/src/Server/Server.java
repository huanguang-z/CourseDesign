package Server;

import Server.UI.OnlineUser;
import Server.UI.ServerPanel;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class Server extends JFrame{

    public static final Integer FRAME_WIDTH = 550;

    public static final Integer FRAME_HEIGHT = 500;

    @Serial
    private static final long serialVersionUID = 7531383040094642338L;

    public JTextField txtServerName;

    public JTextField txtIP;

    public JTextField txtNumber;
    //用户列表界面
    public OnlineUser onlineUserPanel;
    public ServerPanel serverInfoPanel;

    public Server()
    {
        this.setTitle("Zwr网络聊天室服务器");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        //窗体不可扩大
        setResizable(false);
        //获取屏幕
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        //屏幕居中处理
        setLocation((width-FRAME_WIDTH)/2, (height-FRAME_HEIGHT)/2);

        //设置窗体关闭，程序退出
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //选项卡
        JTabbedPane tpServer = new JTabbedPane(JTabbedPane.TOP);
        tpServer.setBackground(Color.WHITE);
        tpServer.setFont(new Font("宋体", 0, 18));

        serverInfoPanel=new ServerPanel();
        tpServer.add("服务器信息", serverInfoPanel.getServeInfoPanel());

        onlineUserPanel =new OnlineUser();
        tpServer.add("当前在线用户",onlineUserPanel.getUserPanel());
        add(tpServer);

        setVisible(true);
    }


}

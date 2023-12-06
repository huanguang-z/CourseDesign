package Server.UI;
/**  服务器面板*/
import Constants.Port;
import Server.Server;

import javax.swing.*;
import java.awt.*;

public class ServerPanel {
    public static final Integer FRAME_WIDTH = 550;

    public static final Integer FRAME_HEIGHT = 500;
    public JTextField txtServerName;

    public JTextField txtIP;

    public JTextField txtNumber;
    public JTextPane txtLog;
    public JLabel getServeInfoPanel()
    {
        //整个第一个选项卡
        JPanel pnlServer = new JPanel();
        pnlServer.setOpaque(false);
        pnlServer.setLayout(null);
        pnlServer.setBounds(0, 0, Server.FRAME_WIDTH, Server.FRAME_HEIGHT);

        //服务器信息

        //日志区域
        JLabel lblLog = new JLabel("[服务器日志]");
        lblLog.setForeground(Color.BLACK);
        lblLog.setFont(new Font("宋体", 0, 16));
        lblLog.setBounds(130, 5, 100, 30);
        pnlServer.add(lblLog);

        txtLog = new JTextPane();
        txtLog.setOpaque(false);
        txtLog.setFont(new Font("宋体", 0, 12));

        JScrollPane scoPaneOne = new JScrollPane(txtLog);// 设置滚动条
        scoPaneOne.setBounds(130, 35, 340, 360);
        scoPaneOne.setOpaque(false);
        scoPaneOne.getViewport().setOpaque(false);
        pnlServer.add(scoPaneOne);


        pnlServer.add(getServerParam());   //添加服务器信息

        ImageIcon imageIcon=new ImageIcon("images/Server.png");
        JLabel lblBackground=new JLabel(imageIcon);
        lblBackground.setBounds(0,200,300,300);
        lblBackground.add(pnlServer);


        return lblBackground;
    }

    public JPanel getServerParam()           //服务器信息，不包括日志
    {
        JPanel serverParamPanel = new JPanel();
        serverParamPanel.setOpaque(false);
        serverParamPanel.setBounds(5, 5, 100, 400);
        serverParamPanel.setFont(new Font("宋体", 0, 14));
        serverParamPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));

        JLabel lblNumber = new JLabel("当前在线人数:");
        lblNumber.setFont(new Font("宋体", 0, 14));
        serverParamPanel.add(lblNumber);

        txtNumber = new JTextField("0 人", 10);
        txtNumber.setFont(new Font("宋体", 0, 14));
        txtNumber.setEditable(false);
        serverParamPanel.add(txtNumber);

        JLabel lblServerName = new JLabel("服务器名称:");
        lblServerName.setFont(new Font("宋体", 0, 14));
        serverParamPanel.add(lblServerName);

        txtServerName = new JTextField(10);
        txtServerName.setFont(new Font("宋体", 0, 14));
        txtServerName.setEditable(false);
        serverParamPanel.add(txtServerName);

        JLabel lblIP = new JLabel("服务器IP:");
        lblIP.setFont(new Font("宋体", 0, 14));
        serverParamPanel.add(lblIP);

        txtIP = new JTextField(10);
        txtIP.setFont(new Font("宋体", 0, 14));
        txtIP.setEditable(false);
        serverParamPanel.add(txtIP);

        JLabel lblPort = new JLabel("服务器端口:");
        lblPort.setFont(new Font("宋体", 0, 14));
        serverParamPanel.add(lblPort);

        JTextField txtPort = new JTextField(Port.SERVER_PORT +"" , 10);
        txtPort.setFont(new Font("宋体", 0, 14));
        txtPort.setEditable(false);
        serverParamPanel.add(txtPort);


        return serverParamPanel;
    }


}

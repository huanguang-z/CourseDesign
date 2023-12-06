package ClientPackage;


import Server.Entity.ChatStatus;
import Server.Entity.TransferInfo;
import IO.IOStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Login extends JFrame {
    private static final long serialVersionUID= -1341235413245342L;
    /**
     * 登录窗体宽度
     */
    private static final Integer FRAME_WIDTH = 400;

    /**
     * 登录窗体高度
     */
    private static final Integer FRAME_HEIGHT = 300;

    Login(){
        this.setTitle("登 录");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //获取屏幕
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        setLocation((width-FRAME_WIDTH)/2, (height-FRAME_HEIGHT)/2);

        //加载窗体的背景图片
        ImageIcon imageIcon = new ImageIcon("images/login.png");
        JLabel lblBackground=new JLabel(imageIcon);
        lblBackground.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        lblBackground.setLayout(null);
        this.add(lblBackground);

        //创建一个标签
        JLabel lblUid=new JLabel("账 号: ");
        lblUid.setBounds(80, 40, 120, 30);
        lblUid.setFont(new Font("宋体" , 0 , 16));
        lblUid.setForeground(Color.WHITE);
        lblBackground.add(lblUid);

        //账号文本框
        JTextField textUid = new JTextField();
        textUid.setBounds(150, 40, 160, 30);
        lblBackground.add(textUid);

        //创建一个的标签
        JLabel lblPsw=new JLabel("密 码: ");
        lblPsw.setBounds(80, 80, 120, 30);
        lblPsw.setFont(new Font("宋体" , 0 , 16));
        lblPsw.setForeground(Color.WHITE);
        lblBackground.add(lblPsw);

        //创建一个密码框，用于输入用户密码
        JPasswordField textPsw = new JPasswordField();
        textPsw.setBounds(150, 80, 160, 30);
        lblBackground.add(textPsw);


        //创建一个文字按钮
        JButton enter = new JButton("登 录");
        enter.setBounds(110, 170, 80, 25);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName=textUid.getText();
                String psw=textPsw.getText();
                TransferInfo tif=new TransferInfo();
                tif.setUserName(userName);
                tif.setPassword(psw);
                //本次处理的消息类型为登录
                tif.setStatusEnum(ChatStatus.LOGIN);
                connectionServer(tif);
            }
        });
        lblBackground.add(enter);


        //创建一个取消按钮
        JButton cancel=new JButton("注册");
        //设置按钮的位置、大小
        cancel.setBounds(215, 170, 80, 25);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = textUid.getText();
                String password = new String(textPsw.getPassword());

                // 将注册信息保存到文本文件
                saveRegistrationInfo(userName, password);

                // 清空输入框
                textUid.setText("");
                textPsw.setText("");

                // 跳转到注册页面
                openRegistrationPage();
            }
        });
        //添加到背景图片上
        lblBackground.add(cancel);

        //设置布局为空布局
        setLayout(null);

        setVisible(true);
    }

    // 连接服务器的方法


    private void saveRegistrationInfo(String userName, String password) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt", true));
            writer.write(userName + "|" + password);
            writer.newLine();
            writer.close();
            System.out.println("注册信息已保存到user.txt文件。");
        } catch (IOException e) {
            System.out.println("保存注册信息时发生错误：" + e.getMessage());
        }
    }

    private boolean isUserExists(String userName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split("\\|");
                if (userInfo.length >= 2 && userInfo[0].equals(userName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 打开注册页面
    private void openRegistrationPage() {
        // 创建一个新的注册窗口
        JFrame registrationFrame = new JFrame();
        registrationFrame.setTitle("注册");
        registrationFrame.setSize(300, 200);
        registrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registrationFrame.setResizable(false);
        registrationFrame.setLayout(null);

        // 创建账号标签
        JLabel lblAccount = new JLabel("账号:");
        lblAccount.setBounds(20, 20, 60, 30);
        registrationFrame.add(lblAccount);

        // 创建账号文本框
        JTextField txtAccount = new JTextField();
        txtAccount.setBounds(90, 20, 180, 30);
        registrationFrame.add(txtAccount);

        // 创建密码标签
        JLabel lblPassword = new JLabel("密码:");
        lblPassword.setBounds(20, 60, 60, 30);
        registrationFrame.add(lblPassword);

        // 创建密码文本框
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(90, 60, 180, 30);
        registrationFrame.add(txtPassword);

        // 创建密码标签
        JLabel lblPassword1 = new JLabel("确认密码:");
        lblPassword1.setBounds(20, 100, 60, 30);
        registrationFrame.add(lblPassword1);

        // 创建密码文本框
        JPasswordField txtPassword1 = new JPasswordField();
        txtPassword1.setBounds(90, 100, 180, 30);
        registrationFrame.add(txtPassword1);

        // 创建注册按钮
        JButton btnRegister = new JButton("注册");
        btnRegister.setBounds(110, 120, 80, 30);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = txtAccount.getText();
                String password = new String(txtPassword.getPassword());
                String password2 = new String(txtPassword1.getPassword());
                if (isUserExists(userName)) {
                    JOptionPane.showMessageDialog(registrationFrame, "该用户已存在！", "错误", JOptionPane.ERROR_MESSAGE);
                } else  if (!password.equals(password2)) {
                    JOptionPane.showMessageDialog(registrationFrame, "两次输入的密码不一致，请重新输入密码。", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    // 将注册信息保存到文本文件
                    saveRegistrationInfo(userName, password);

                    // 清空输入框
                    txtAccount.setText("");
                    txtPassword.setText("");

                    System.out.println("注册信息已保存到user.txt文件。");
                }
            }
        });
        registrationFrame.add(btnRegister);

        // 设置注册窗口可见
        registrationFrame.setVisible(true);
    }
    public void connectionServer(TransferInfo tif)
    {
        try {
            Socket socket=new Socket("127.0.0.1",8888);

            IOStream.writeMessage(socket,tif);

            ClientManagement clientHandler=new ClientManagement(socket,this);
            clientHandler.start();
            System.out.println("客户端线程启动之后");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new Login();
    }


}

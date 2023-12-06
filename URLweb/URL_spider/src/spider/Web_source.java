package spider;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;


//点击打开按钮之后会显示文件夹，可以选择文件
class CheckFile extends JFrame implements ActionListener {
    private JButton btnOpen = new JButton("  打开    ");
    JEditorPane txtInfo=new JEditorPane();
    private JScrollPane sp = new JScrollPane(txtInfo);
    private JPanel btnPane = new JPanel();
    private JFileChooser fileDlg = null;
    static String line,lineNo;
    CheckFile(String title) {

        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        btnPane.add(btnOpen);
        btnOpen.setActionCommand("open");
        btnOpen.addActionListener(this);

        txtInfo.setFont( new Font( "宋体", Font.PLAIN, 16 ) );
        txtInfo.setBackground( Color.WHITE);
        txtInfo.setForeground( Color.black);

        Container cp = getContentPane();
        cp.add(btnPane, BorderLayout.NORTH);
        cp.add(sp, BorderLayout.CENTER);

        setPreferredSize(new Dimension(500, 400));
        setLocation(400,200);
        setVisible(true);
        pack();
        txtInfo.setContentType("text/html");
        txtInfo.setEditable(false);

    }

    public static void main(String []args) {
        new CheckFile("打开文件");
    }
    //将指定目录下文件的内容显示在txtinfo中
    private void showFileContent(String fileName) {
        try {

            txtInfo.setText("" );
            File file = new File(fileName);
            String path = file.getAbsolutePath();
            path="file:"+path;
            txtInfo.setPage(path);
            JOptionPane.showMessageDialog(
                    btnPane, "打开成功");
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "open") {
            try {
                fileDlg = new JFileChooser("C:\\URLweb\\URL_spider");
                fileDlg.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
                //弹出的提示框的标题
                int n= fileDlg.showDialog(new JLabel(), "确定");
                //用户选择的路径或文件
                if(n==JOptionPane.YES_OPTION) {
                    String filePath = fileDlg.getSelectedFile().getAbsolutePath();
                    setTitle( fileDlg.getSelectedFile().getName() );//同时改变窗口的名称
                    showFileContent( filePath );//调用函数，在窗口中显示文件内容
                }
            }catch(Exception ee) {}
        }
    }
}


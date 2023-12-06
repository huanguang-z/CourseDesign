 package ClientPackage;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Expression extends JFrame {
    private static final long serialVersionUID = 8603409755227097846L;

    /**
     * 表情图片摆放
     */
    public Expression(JTextPane textPane){
        JPanel panel=(JPanel)getContentPane();
        panel.setLayout(null);
        //用双重循环来摆图片
        for (int row = 0; row <10; row++) {
            for (int col = 0; col < 6; col++) {
                //得到图片
                ImageIcon icon=new ImageIcon("images/face/"+(6*row+col+1)+".gif");
                //将图片放在JLable里
                JLabel lblIcon=new JLabel(icon);
                lblIcon.setSize(50,50);
                lblIcon.setLocation(0+col*50, 0+row*50);
                //为lbl添加鼠标事件
                lblIcon.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JLabel jLabel = (JLabel)e.getSource();
                        Icon icon2 = jLabel.getIcon();
                        //插入选中图片
                        textPane.insertIcon(icon2);
                        //关闭当前图片框
                        Expression.this.dispose();
                    }
                });
                panel.add(lblIcon);
            }
        }
        setSize(320, 300);
        setLocation(800, 400);
        setTitle("嘻哈猴");
        setVisible(true);
    }
}

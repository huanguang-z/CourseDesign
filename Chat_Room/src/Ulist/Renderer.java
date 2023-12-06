package Ulist;

import Server.Entity.UserInformation;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

//DefaultListCellRenderer 是JList的渲染器，继承JLable

public class Renderer extends DefaultListCellRenderer{


    @Serial
    private static final long serialVersionUID = -7359333753554764017L;

    /**
    * value就是模型数据
    * index 当前选择单元格下标
    * isSelected  单元格选中状态，你选择A再选择A，返回false
    * */

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        if(value instanceof UserInformation){
            UserInformation user=(UserInformation) value;
            String userIcon=user.getUiconPath();
            String Username=user.getUsername();
            ImageIcon icon=new ImageIcon(userIcon);
            icon.setImage(icon.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
            setIcon(icon);
            String text="<html><body><span color='gray' style='font-size:15px;'>" + Username + "</span><br/></body></html>";
            setText(text);
            setForeground(Color.BLUE);
            setHorizontalTextPosition(SwingConstants.RIGHT);
            setVerticalTextPosition(SwingConstants.TOP);
        }

        return this;
    }
}

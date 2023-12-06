package Ulist;

import Server.Entity.UserInformation;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 构成Jlist的模型元素
 * 这个模型类最终被调用
 * */

public  class ListModel extends AbstractListModel<UserInformation> implements Serializable {

    @Serial
    private static final long serialVersionUID = -2443732124383889041L;
    //   private static final long serialVersionUID = 1872503035565377979L;
    private List<UserInformation> list = new ArrayList<UserInformation>();

    public void addElement(UserInformation user)
    {
        list.add(user);
    }
    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public UserInformation getElementAt(int index) {
        return list.get(index);
    }
}

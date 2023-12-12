package com.pony.db;

import java.util.Observable;

/**
 * 步数被观察者
 * 
 * Created by waka on 2016/3/2.
 */
public class ShowObservable extends Observable {

    //单例
    private static ShowObservable instance = null;

    public static ShowObservable getInstance() {

        if (null == instance) {
            instance = new ShowObservable();
        }
        return instance;
    }

    //通知观察者更新数据
    public void notifyStepChange(String msg) {
        //关键方法，必须写，具体实现可以查看源码
        setChanged();//设置changeFlag
        notifyObservers(msg);//通知观察者
    }

}
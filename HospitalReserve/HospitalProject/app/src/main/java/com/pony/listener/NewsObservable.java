package com.pony.listener;

import java.util.Observable;

/**
 * 步数被观察者
 * 
 * Created by waka on 2016/3/2.
 */
public class NewsObservable extends Observable {

    //单例
    private static NewsObservable instance = null;

    public static NewsObservable getInstance() {

        if (null == instance) {
            instance = new NewsObservable();
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
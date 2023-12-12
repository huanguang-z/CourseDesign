package com.pony.db;

import java.util.Observable;

public class ReviewObservable extends Observable {

    //单例
    private static ReviewObservable instance = null;

    public static ReviewObservable getInstance() {

        if (null == instance) {
            instance = new ReviewObservable();
        }
        return instance;
    }

    //通知观察者更新数据
    public void notifyStepChange(String msg) {
        setChanged();//设置changeFlag
        notifyObservers(msg);//通知观察者
    }

}
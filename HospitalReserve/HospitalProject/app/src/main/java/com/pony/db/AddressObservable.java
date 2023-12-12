package com.pony.db;

import java.util.Observable;


public class AddressObservable extends Observable {


    private static AddressObservable instance = null;

    public static AddressObservable getInstance() {

        if (null == instance) {
            instance = new AddressObservable();
        }
        return instance;
    }


    public void notifyStepChange(String msg) {
        setChanged();
        notifyObservers(msg);
    }

}
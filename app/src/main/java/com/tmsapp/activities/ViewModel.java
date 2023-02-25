package com.tmsapp.activities;

import com.tmsapp.model.Driver;

public class ViewModel {

    private static ViewModel single_instance = null;

    public static ViewModel getInstance()
    {
        if (single_instance == null)
            single_instance = new ViewModel();

        return single_instance;
    }
    private Driver currentDriver;

    public Driver getCurrentDriver() {
        return currentDriver;
    }

    public void setCurrentDriver(Driver currentDriver) {
        this.currentDriver = currentDriver;
    }
}


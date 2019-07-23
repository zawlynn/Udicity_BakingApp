package com.zawlynn.udacity.bakingapp;

import android.app.Application;

import com.zawlynn.udacity.bakingapp.di.component.DaggerDataComponent;
import com.zawlynn.udacity.bakingapp.di.component.DataComponent;
import com.zawlynn.udacity.bakingapp.di.module.ContextModule;

public class BakingApplication extends Application {
    DataComponent dataComponent;
    static BakingApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        dataComponent= DaggerDataComponent.builder().contextModule(new ContextModule(this)).build();
    }
    public static BakingApplication getInstance(){
        return instance;
    }
    public DataComponent getDataComponent() {
        return dataComponent;
    }

}

package com.zawlynn.udacity.bakingapp.di.module;

import android.app.Application;

import com.zawlynn.udacity.bakingapp.BakingApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    Application application;
    public ContextModule(Application application){
        this.application=application;
    }
    @Provides
    public Application getAppliation(){
        return application;
    }
}

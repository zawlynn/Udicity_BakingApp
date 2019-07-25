package com.zawlynn.udacity.bakingapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.test.espresso.IdlingResource;

import com.zawlynn.udacity.bakingapp.di.component.DaggerDataComponent;
import com.zawlynn.udacity.bakingapp.di.component.DataComponent;
import com.zawlynn.udacity.bakingapp.di.module.ContextModule;
import com.zawlynn.udacity.bakingapp.utils.BakingIdlingResource;

import java.util.logging.Logger;

public class BakingApplication extends Application {
    DataComponent dataComponent;
    static BakingApplication instance;
    @Nullable
    private BakingIdlingResource mIdlingResource;

    @VisibleForTesting
    @NonNull
    private IdlingResource initializeIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new BakingIdlingResource();
        }
        return mIdlingResource;
    }

    public BakingApplication() {
        // The IdlingResource will be null in production.
        if (BuildConfig.DEBUG) {
            initializeIdlingResource();
        }

    }

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

    public void setIdleState(boolean state) {
        if (mIdlingResource != null)
            mIdlingResource.setIdleState(state);
    }

    @Nullable
    public BakingIdlingResource getIdlingResource() {
        return mIdlingResource;
    }

}

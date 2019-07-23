package com.zawlynn.udacity.bakingapp.di.component;

import com.zawlynn.udacity.bakingapp.di.module.ContextModule;
import com.zawlynn.udacity.bakingapp.di.module.RetrofitModule;
import com.zawlynn.udacity.bakingapp.ui.main.viewmodel.MainActivityViewmodel;

import dagger.Component;

@Component(modules = {RetrofitModule.class, ContextModule.class})
public interface DataComponent {
     void inject(MainActivityViewmodel viewmodel);
}

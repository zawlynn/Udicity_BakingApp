package com.zawlynn.udacity.bakingapp.ui.main.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.zawlynn.udacity.bakingapp.BakingApplication;
import com.zawlynn.udacity.bakingapp.R;
import com.zawlynn.udacity.bakingapp.data.network.ApiService;
import com.zawlynn.udacity.bakingapp.pojo.Recipe;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewmodel extends AndroidViewModel {
    private Disposable disposable;
    private static final String TAG = "MainActivityViewmodel";
    @Inject
    ApiService service;
    MutableLiveData<String> error=new MutableLiveData<>();
    private MutableLiveData<List<Recipe>> live_recipes=new MutableLiveData<>();
    public MainActivityViewmodel(@NonNull Application application) {
        super(application);
        disposable= new CompositeDisposable();
        BakingApplication.getInstance().getDataComponent().inject(this);
        disposable = service.getRecipes().observeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
                .doOnError(Throwable::getMessage)
                .subscribe(recipes -> {
                    live_recipes.postValue(recipes);
                },throwable -> {
                    error.postValue(application.getResources().getString(R.string.error));
                });
    }
    public MutableLiveData<String> getMessage(){
        return error;
    }
    public MutableLiveData<List<Recipe>> getLive_recipes() {
        return live_recipes;
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}

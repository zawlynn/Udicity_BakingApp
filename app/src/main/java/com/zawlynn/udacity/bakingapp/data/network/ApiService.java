package com.zawlynn.udacity.bakingapp.data.network;

import com.zawlynn.udacity.bakingapp.pojo.Recipe;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("topher/2017/May/59121517_baking/baking.json")
    public Flowable<List<Recipe>> getRecipes();
}

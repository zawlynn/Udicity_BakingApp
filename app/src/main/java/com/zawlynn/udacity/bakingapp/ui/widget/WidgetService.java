package com.zawlynn.udacity.bakingapp.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.zawlynn.udacity.bakingapp.constants.Constants;
import com.zawlynn.udacity.bakingapp.pojo.Recipe;


public class WidgetService extends RemoteViewsService {



    public static Intent getIntent(Context context, Recipe recipe) {
        Intent intent = new Intent(context, WidgetService.class);
        String recipeJson = new Gson().toJson(recipe);
        intent.putExtra(Constants.DATA, recipeJson);
        return intent;
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetRemoteViewsFactory(getApplicationContext(), intent);
    }

}


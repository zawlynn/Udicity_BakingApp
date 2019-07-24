package com.zawlynn.udacity.bakingapp.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.zawlynn.udacity.bakingapp.R;
import com.zawlynn.udacity.bakingapp.constants.Constants;
import com.zawlynn.udacity.bakingapp.pojo.Recipe;


public class WidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {



    private Context context;
    private Recipe recipe;

    WidgetRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
        if (intent != null && intent.hasExtra(Constants.DATA)) {
            String recipeJson = intent.getStringExtra(Constants.DATA);
            recipe = new Gson().fromJson(recipeJson, Recipe.class);
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {
        this.recipe.getIngredients().clear();
        this.recipe = null;
    }

    @Override
    public int getCount() {
        return recipe.getIngredients().size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_item_ingredient);
        views.setTextViewText(R.id.tv_ingredient_measure,
                context.getString(R.string.ingredient_measure_label,
                        recipe.getIngredients().get(position).getMeasure()));
        views.setTextViewText(R.id.tv_ingredient_quantity,
                context.getString(R.string.ingredient_quantity_label,
                        String.valueOf(recipe.getIngredients().get(position).getQuantity())));
        views.setTextViewText(R.id.tv_ingredient_name,
                context.getString(R.string.ingredient_name_label,
                        recipe.getIngredients().get(position).getIngredient()));
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}

package com.zawlynn.udacity.bakingapp.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import com.zawlynn.udacity.bakingapp.databinding.RecipeItem;
import com.zawlynn.udacity.bakingapp.pojo.Recipe;

public class RecipesAdapter extends ListAdapter<Recipe,RecipeViewHolder> {
    private OnRecipeClick recipeClick;
    public RecipesAdapter(OnRecipeClick onRecipeClick) {
        super(new RecipeItemCallback());
        this.recipeClick=onRecipeClick;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecipeItem itemBinding = RecipeItem.inflate(layoutInflater, parent, false);
        return new RecipeViewHolder(itemBinding,recipeClick);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}

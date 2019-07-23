package com.zawlynn.udacity.bakingapp.ui.main.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zawlynn.udacity.bakingapp.databinding.RecipeItem;
import com.zawlynn.udacity.bakingapp.pojo.Recipe;

class RecipeViewHolder extends RecyclerView.ViewHolder{
    private RecipeItem binding;
    private OnRecipeClick onRecipeClick;
    RecipeViewHolder(RecipeItem binding,OnRecipeClick onRecipeClick) {
        super(binding.getRoot());
        this.binding=binding;
        this.onRecipeClick=onRecipeClick;
    }
    void bind(Recipe recipe){
        binding.tvContent.setText(recipe.getName());
        binding.tvContent.setOnClickListener(v -> {
            onRecipeClick.onClick(recipe);
        });
    }
}

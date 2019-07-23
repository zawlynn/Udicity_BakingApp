package com.zawlynn.udacity.bakingapp.ui.main.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.zawlynn.udacity.bakingapp.pojo.Recipe;

public class RecipeItemCallback extends DiffUtil.ItemCallback<Recipe> {

    @Override
    public boolean areItemsTheSame(@NonNull Recipe oldItem, @NonNull Recipe newItem) {
        return  oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Recipe oldItem, @NonNull Recipe newItem) {
        return oldItem.equals(newItem);
    }
}

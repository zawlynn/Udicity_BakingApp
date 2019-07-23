package com.zawlynn.udacity.bakingapp.ui.detail.adapter.ingredient;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.zawlynn.udacity.bakingapp.pojo.Ingredient;

public class IngredientItemCallback extends DiffUtil.ItemCallback<Ingredient> {

    @Override
    public boolean areItemsTheSame(@NonNull Ingredient oldItem, @NonNull Ingredient newItem) {
        return  oldItem.getIngredient() == newItem.getIngredient();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Ingredient oldItem, @NonNull Ingredient newItem) {
        return oldItem.equals(newItem);
    }
}

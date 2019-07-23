package com.zawlynn.udacity.bakingapp.ui.detail.adapter.ingredient;

import android.annotation.SuppressLint;

import androidx.recyclerview.widget.RecyclerView;

import com.zawlynn.udacity.bakingapp.databinding.IngredientItem;
import com.zawlynn.udacity.bakingapp.pojo.Ingredient;

class IngredientViewHolder extends RecyclerView.ViewHolder{
    private IngredientItem binding;
    IngredientViewHolder(IngredientItem binding) {
        super(binding.getRoot());
        this.binding=binding;
    }
    @SuppressLint("SetTextI18n")
    void bind(Ingredient ingredient){
        binding.tvIngredient.setText(ingredient.getIngredient()
                +" ( "+ingredient.getQuantity()+" / "+ingredient.getMeasure()+" )");
    }
}

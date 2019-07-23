package com.zawlynn.udacity.bakingapp.ui.detail.adapter.ingredient;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.zawlynn.udacity.bakingapp.databinding.IngredientItem;
import com.zawlynn.udacity.bakingapp.pojo.Ingredient;

public class IngredientAdapter extends ListAdapter<Ingredient, IngredientViewHolder> {

    public IngredientAdapter() {
        super(new IngredientItemCallback());
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        IngredientItem itemBinding = IngredientItem.inflate(layoutInflater, parent, false);
        return new IngredientViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}

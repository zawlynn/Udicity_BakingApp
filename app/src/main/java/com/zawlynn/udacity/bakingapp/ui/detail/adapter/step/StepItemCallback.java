package com.zawlynn.udacity.bakingapp.ui.detail.adapter.step;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.zawlynn.udacity.bakingapp.pojo.Ingredient;
import com.zawlynn.udacity.bakingapp.pojo.Step;

public class StepItemCallback extends DiffUtil.ItemCallback<Step> {

    @Override
    public boolean areItemsTheSame(@NonNull Step oldItem, @NonNull Step newItem) {
        return  oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Step oldItem, @NonNull Step newItem) {
        return oldItem.equals(newItem);
    }
}

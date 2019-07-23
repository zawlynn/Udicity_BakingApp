package com.zawlynn.udacity.bakingapp.ui.detail.adapter.step;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.zawlynn.udacity.bakingapp.databinding.IngredientItem;
import com.zawlynn.udacity.bakingapp.databinding.StepItem;
import com.zawlynn.udacity.bakingapp.pojo.Ingredient;
import com.zawlynn.udacity.bakingapp.pojo.Step;
import com.zawlynn.udacity.bakingapp.ui.detail.adapter.OnInstructionClick;

public class StepAdapter extends ListAdapter<Step, StepViewHolder> {
    private OnInstructionClick onInstructionClick;
    public StepAdapter(OnInstructionClick instructionClick) {
        super(new StepItemCallback());
        this.onInstructionClick=instructionClick;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        StepItem itemBinding = StepItem.inflate(layoutInflater, parent, false);
        return new StepViewHolder(itemBinding,onInstructionClick);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}

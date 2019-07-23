package com.zawlynn.udacity.bakingapp.ui.detail.adapter.step;

import androidx.recyclerview.widget.RecyclerView;

import com.zawlynn.udacity.bakingapp.databinding.IngredientItem;
import com.zawlynn.udacity.bakingapp.databinding.StepItem;
import com.zawlynn.udacity.bakingapp.pojo.Ingredient;
import com.zawlynn.udacity.bakingapp.pojo.Step;
import com.zawlynn.udacity.bakingapp.ui.detail.adapter.OnInstructionClick;

class StepViewHolder extends RecyclerView.ViewHolder{
    private StepItem binding;
    private OnInstructionClick onInstructionClick;
    StepViewHolder(StepItem binding, OnInstructionClick instructionClick) {
        super(binding.getRoot());
        this.binding=binding;
        this.onInstructionClick=instructionClick;
    }
    void bind(Step step){
        binding.tvStep.setText(step.getId()+" - "+step.getShortDescription());
        binding.tvStep.setOnClickListener(v -> {
            this.onInstructionClick.onClick(step);
        });
    }
}

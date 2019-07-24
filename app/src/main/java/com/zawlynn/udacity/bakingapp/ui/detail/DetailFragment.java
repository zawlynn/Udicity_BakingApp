package com.zawlynn.udacity.bakingapp.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zawlynn.udacity.bakingapp.R;
import com.zawlynn.udacity.bakingapp.constants.Constants;
import com.zawlynn.udacity.bakingapp.databinding.DetailFragmentBinding;
import com.zawlynn.udacity.bakingapp.pojo.Recipe;
import com.zawlynn.udacity.bakingapp.pojo.Step;
import com.zawlynn.udacity.bakingapp.ui.detail.adapter.OnInstructionClick;
import com.zawlynn.udacity.bakingapp.ui.detail.adapter.ingredient.IngredientAdapter;
import com.zawlynn.udacity.bakingapp.ui.detail.adapter.step.StepAdapter;
import com.zawlynn.udacity.bakingapp.ui.step.StepDetailActivity;
import com.zawlynn.udacity.bakingapp.ui.step.StepDetailFragment;
import com.zawlynn.udacity.bakingapp.utils.ActivityUtils;

public class DetailFragment extends Fragment implements OnInstructionClick {
    private Recipe recipe;
    private DetailFragmentBinding binding;
    private IngredientAdapter ingredientAdapter;
    private StepAdapter stepAdapter;
    private OnRecipeClick onRecipeClick;
    static DetailFragment newInstance(Recipe recipe, OnRecipeClick listener) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.DATA, recipe);
        fragment.setArguments(args);
        fragment.setListener(listener);
        return fragment;
    }
    private void setListener(OnRecipeClick listener) {
        this.onRecipeClick = listener;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(Constants.DATA)) {
            this.recipe = getArguments().getParcelable(Constants.DATA);
        }
    }
    private void init(){
        binding.recIngredient.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientAdapter=new IngredientAdapter();
        binding.recIngredient.setAdapter(ingredientAdapter);
        binding.recStep.setLayoutManager(new LinearLayoutManager(getContext()));
        stepAdapter=new StepAdapter(this);
        binding.recStep.setAdapter(stepAdapter);
    }


    @Override
    public void onClick(Step step) {
        if(getResources().getBoolean(R.bool.isTable)){
           onRecipeClick.onClick(step);
        }else {
            Intent i =new Intent(getActivity(), StepDetailActivity.class);
            i.putExtra(Constants.DATA,step);
            startActivity(i);
        }

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DetailFragmentBinding.inflate(inflater, container, false);
        init();
        ingredientAdapter.submitList(recipe.getIngredients());
        stepAdapter.submitList(recipe.getSteps());
        return binding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

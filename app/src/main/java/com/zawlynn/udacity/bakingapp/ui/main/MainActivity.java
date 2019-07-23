package com.zawlynn.udacity.bakingapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;

import com.google.android.material.snackbar.Snackbar;
import com.zawlynn.udacity.bakingapp.R;
import com.zawlynn.udacity.bakingapp.constants.Constants;
import com.zawlynn.udacity.bakingapp.databinding.MainActivityBinding;
import com.zawlynn.udacity.bakingapp.pojo.Recipe;
import com.zawlynn.udacity.bakingapp.ui.detail.DetailActivity;
import com.zawlynn.udacity.bakingapp.ui.main.adapter.OnRecipeClick;
import com.zawlynn.udacity.bakingapp.ui.main.adapter.RecipesAdapter;
import com.zawlynn.udacity.bakingapp.ui.main.viewmodel.MainActivityViewmodel;

public class MainActivity extends AppCompatActivity implements OnRecipeClick {
    MainActivityViewmodel viewmodel;
    private static final String TAG = "MainActivity";
    private MainActivityBinding binding;
    RecipesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewmodel = ViewModelProviders.of(this).get(MainActivityViewmodel.class);
        viewmodel.getLive_recipes().observe(this, recipes -> {
            if (recipes != null) {
                adapter.submitList(recipes);
            }
        });
        viewmodel.getMessage().observe(this,s -> {
            if(s!=null){
                Snackbar.make(binding.container,s,Snackbar.LENGTH_SHORT).show();
            }
        });
        init();
    }
    private void init(){
        if(getResources().getBoolean(R.bool.isTable)){
            binding.recRecipe.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        }else {
            binding.recRecipe.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }
        adapter=new RecipesAdapter(this);
        binding.recRecipe.setAdapter(adapter);
    }

    @Override
    public void onClick(Recipe recipe) {
        Intent i =new Intent(MainActivity.this, DetailActivity.class);
        i.putExtra(Constants.DATA,recipe);
        startActivity(i);
    }
}

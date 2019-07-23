package com.zawlynn.udacity.bakingapp.ui.step;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zawlynn.udacity.bakingapp.R;
import com.zawlynn.udacity.bakingapp.constants.Constants;
import com.zawlynn.udacity.bakingapp.pojo.Step;
import com.zawlynn.udacity.bakingapp.utils.ActivityUtils;


public class StepDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (getIntent() != null && getIntent().hasExtra(Constants.DATA)) {
            Step step = getIntent().getParcelableExtra(Constants.DATA);
            setTitle(step.getShortDescription());
            if(!getResources().getBoolean(R.bool.isTable)){
                ActivityUtils.addFragment(this, R.id.container,
                        StepDetailFragment.newInstance(step));
            }
        }
    }
}

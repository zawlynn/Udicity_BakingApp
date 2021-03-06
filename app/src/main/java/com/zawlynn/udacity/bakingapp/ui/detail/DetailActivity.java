package com.zawlynn.udacity.bakingapp.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.zawlynn.udacity.bakingapp.R;
import com.zawlynn.udacity.bakingapp.constants.Constants;
import com.zawlynn.udacity.bakingapp.pojo.Recipe;

import com.zawlynn.udacity.bakingapp.pojo.Step;
import com.zawlynn.udacity.bakingapp.ui.step.StepDetailFragment;
import com.zawlynn.udacity.bakingapp.ui.widget.WidgetProvider;
import com.zawlynn.udacity.bakingapp.utils.ActivityUtils;


public class DetailActivity extends AppCompatActivity implements OnRecipeClick {
    private static final String TAG = "DetailActivity";
    Recipe recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (getIntent() != null && getIntent().hasExtra(Constants.DATA)) {
            recipe = getIntent().getParcelableExtra(Constants.DATA);
            setTitle(recipe.getName());
            ActivityUtils.replaceFragment(this, R.id.container,
                    DetailFragment.newInstance(recipe,this));
            if(getResources().getBoolean(R.bool.isTable)){
                ActivityUtils.addFragment(this, R.id.recipe_step_detail_fragment,
                        StepDetailFragment.newInstance(recipe.getSteps().get(0)));
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.widget_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.widget:
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, WidgetProvider.class));
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);
                WidgetProvider.updateRecipeWidgets(this, recipe, appWidgetManager, appWidgetIds);
        }
        return true;
    }
    @Override
    public void onClick(Step step) {
        ActivityUtils.replaceFragment(this, R.id.recipe_step_detail_fragment,
                StepDetailFragment.newInstance(step));
    }
}

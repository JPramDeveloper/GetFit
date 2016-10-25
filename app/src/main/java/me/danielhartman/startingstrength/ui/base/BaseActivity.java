package me.danielhartman.startingstrength.ui.base;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.databinding.BaseActivityBinding;

public abstract class BaseActivity extends AppCompatActivity {

    BaseActivityBinding binding;
    boolean displayFab = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.base_activity);
        setSupportActionBar(getBaseToolbar());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!displayFab) hideFab();
    }

    public boolean displayFab(){
        return displayFab;
    }

    private void hideFab(){
        binding.fab.setVisibility(View.GONE);
    }

    public FloatingActionButton getFab(){
        return binding.fab;
    }

    public Toolbar getBaseToolbar(){
        return binding.toolbar;
    }
}

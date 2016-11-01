package me.danielhartman.startingstrength.ui;

import android.os.Bundle;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new MainMenu_Fragment())
                .addToBackStack(null)
                .commit();
    }
}

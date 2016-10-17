package me.danielhartman.startingstrength.ui.base;


import android.databinding.tool.Binding;
import android.view.View;

import java.util.ArrayList;

public abstract class Presenter {

    public abstract void onResume();
    public abstract void onPause();
    public abstract void onDestroy();
}

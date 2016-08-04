package me.danielhartman.startingstrength.ui.accountManagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;


public class AccountActivity extends AppCompatActivity{

//    @Inject
//    LoginPresenter LoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHolder.getInstance().component().inject(this);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new Login_Fragment())
                .addToBackStack(null)
                .commit();

    }
}

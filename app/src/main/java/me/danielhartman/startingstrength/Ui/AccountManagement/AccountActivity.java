package me.danielhartman.startingstrength.ui.accountManagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import me.danielhartman.startingstrength.Interfaces.LoginCallback;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.ui.MainActivity;


public class AccountActivity extends AppCompatActivity implements LoginCallback {

    private static final String TAG = AccountActivity.class.getSimpleName();
    @Inject
    LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHolder.getInstance().component().inject(this);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new LoginFragment())
                .commit();
        mLoginPresenter.attachUserListener(this);
    }

    @Override
    public void successfulLogin() {
        Log.d(TAG, "successfulLogin: ");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void failedLogin(String message) {
        Log.d(TAG, "failedLogin: Something went Wrong");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        mLoginPresenter.detatchUserListener();
        super.onStop();
    }
}

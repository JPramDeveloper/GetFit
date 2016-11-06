package me.danielhartman.startingstrength.ui.accountManagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.network.LoginManager;
import me.danielhartman.startingstrength.ui.MainActivity;
import me.danielhartman.startingstrength.ui.base.BaseActivity;


public class LoginActivity extends BaseActivity implements LoginManager.LoginCallback{

    private static final String TAG = LoginActivity.class.getSimpleName();
    LoginManager.Login loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginManager = DaggerHolder.getInstance().component().getLoginManager();
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new LoginFragment())
                .commit();
        isSignedIn();
    }

    public void isSignedIn(){
        loginManager.checkIfUserLoggedIn(this);
    }

    @Override
    public void successfulLogin() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void failedLogin(String error) {

    }
}

package me.danielhartman.startingstrength.ui.accountManagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.network.LoginManager;
import me.danielhartman.startingstrength.ui.MainActivity;

public class LoginFragment extends Fragment implements LoginManager.FailedLoginCallback{

    private static final String TAG = LoginFragment.class.getSimpleName();

    LoginManager.Login loginManger;
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.password)
    EditText password;
    private View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.login_frag, container, false);
        ButterKnife.bind(this, rootView);
        loginManger = DaggerHolder.getInstance().component().getLoginManager();
        return rootView;
    }

    @OnClick(R.id.login)
    public void clickLogin() {
        loginManger.login(userName.getText().toString(), password.getText().toString(), this);
    }

    @OnClick(R.id.newAccount)
    public void clickCreateAccount() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new CreateAccountFragment(), null)
                .addToBackStack(TAG)
                .commit();
    }

    @Override
    public void failedLogin(String error) {
        Log.d(TAG, "failedLogin: " + error);
        Toast.makeText(getContext(), "Login Failed", Toast.LENGTH_SHORT).show();
    }
}

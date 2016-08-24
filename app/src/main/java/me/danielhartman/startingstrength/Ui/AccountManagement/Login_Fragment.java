package me.danielhartman.startingstrength.ui.accountManagement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.Interfaces.LoginCallback;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.ui.MainMenu_Fragment;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class Login_Fragment extends Fragment{

    @Inject
    LoginPresenter mLoginPresenter;

    private View rootView;

    @BindView(R.id.userName)EditText userName;
    @BindView(R.id.password)EditText password;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.login_frag, container, false);
        ButterKnife.bind(this, rootView);
        DaggerHolder.getInstance().component().inject(this);
        return rootView;
    }

    @OnClick(R.id.login)
    public void clickLogin(){
        mLoginPresenter.signIn(userName.getText().toString(),password.getText().toString());
    }

    @OnClick(R.id.newAccount)
    public void clickCreateAccount(){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,new CreateAccountFragment(),null)
                .commit();
    }
}

package me.danielhartman.startingstrength.Ui.AccountManagement;

import com.google.firebase.auth.FirebaseUser;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.Interfaces.FirebaseUserCallback;
import me.danielhartman.startingstrength.Network.LoginNetworkCalls;
import me.danielhartman.startingstrength.Interfaces.LoginCallback;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.Ui.MyApplication;
import me.danielhartman.startingstrength.Ui.MainMenu_Fragment;

public class Login_Fragment extends Fragment implements LoginCallback, FirebaseUserCallback {

    @Inject
    LoginNetworkCalls mLoginNetworkCalls;

    private View rootView;

    private LoginCallback mLoginCallback;
    private FirebaseUserCallback mFirebaseUserCallback;

    @Bind(R.id.userName)EditText userName;
    @Bind(R.id.password)EditText password;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.login_frag, container, false);
        ButterKnife.bind(this, rootView);
        ((MyApplication)(getActivity().getApplication())).getViewWorkoutComponent().inject(this);
        mLoginCallback = (LoginCallback)(getActivity().getSupportFragmentManager().findFragmentById(R.id.container));
        mFirebaseUserCallback = (FirebaseUserCallback) (getActivity().getSupportFragmentManager().findFragmentById(R.id.container));
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mLoginNetworkCalls.getFirebaseUser(mFirebaseUserCallback);
    }

    @OnClick(R.id.login)
    public void clickLogin(){

    }
    @OnClick(R.id.newAccount)
    public void clickCreateAccount(){
       replaceFragment(new CreateAccount_Fragment(), R.id.container);
    }

    public void replaceFragment(Fragment fragment, Integer view){
     getActivity().getSupportFragmentManager().beginTransaction().replace(view, fragment).commit();
    }

    @Override
    public void successfulLogin() {
        Toast.makeText(getActivity().getApplicationContext(), "Login was successful", Toast.LENGTH_LONG).show();
        replaceFragment(new MainMenu_Fragment(), R.id.container);
    }

    @Override
    public void failedLogin() {
        Toast.makeText(getActivity().getApplicationContext(), "Something went  wrong, please try again!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void returnUserSuccess(FirebaseUser user) {
        Toast.makeText(getActivity().getApplicationContext(),user.getEmail(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void returnNullUser(FirebaseUser user) {
        Toast.makeText(getActivity().getApplicationContext(),"Null",Toast.LENGTH_LONG).show();
    }
}

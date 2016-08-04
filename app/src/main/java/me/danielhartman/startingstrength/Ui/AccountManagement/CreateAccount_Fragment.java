package me.danielhartman.startingstrength.Ui.AccountManagement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.Interfaces.LoginCallback;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.Ui.MyApplication;
import rx.Observable;

/**
 * Created by dphart9 on 10/12/2015.
 */
public class CreateAccount_Fragment extends Fragment implements LoginCallback {
    @Inject
    LoginPresenter mLoginPresenter;
    @Bind(R.id.username) EditText username;
    @Bind(R.id.password) EditText password;
    @Bind(R.id.verifyUsernameImage) ImageView verifyUsernameImage;
    @Bind(R.id.verifyPasswordImage) ImageView verifyPasswordImage;
    @Bind(R.id.btnSignIn) Button mSignInButton;

    private LoginCallback mLoginCallback;
    private View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.createaccount_frag, container, false);
        ((MyApplication)getActivity().getApplication()).getViewWorkoutComponent().inject(this);
        mLoginCallback = (LoginCallback)getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
        ButterKnife.bind(this, rootView);
        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();
        mSignInButton.setVisibility(View.INVISIBLE);
        mSignInButton.setEnabled(false);
        checkInputs();

    }

    @OnClick(R.id.btnSignIn)
    public void signUpUser(){
        mLoginPresenter.createAccount(username.getText().toString(),password.getText().toString(), mLoginCallback);
    }

    public void checkInputs(){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Observable<Boolean> userNameValid = RxTextView.textChangeEvents(username)
                .map(r->r.text())
                .map(l -> pattern.matcher(l).matches());
        userNameValid.distinctUntilChanged()
                .map(b->b? R.drawable.check: R.drawable.cancel)
                .subscribe(resource-> verifyUsernameImage.setBackgroundResource(resource));
        Observable<Boolean> passwordValid = RxTextView.textChangeEvents(password)
                .map(r->r.text())
                .map(l -> l.toString().length() >= 6);
        passwordValid.distinctUntilChanged()
                .map(b -> b ? R.drawable.check : R.drawable.cancel)
                .subscribe(resource -> verifyPasswordImage.setBackgroundResource(resource));
        Observable<Boolean> buttonEnabled = Observable.combineLatest(userNameValid, passwordValid, (a, b) -> a & b);
        buttonEnabled.distinctUntilChanged()
                .map(validInput -> validInput ? View.VISIBLE : View.INVISIBLE)
                .subscribe(visibility -> mSignInButton.setVisibility(visibility));
        buttonEnabled.distinctUntilChanged()
                .subscribe(buttonStatus -> mSignInButton.setEnabled(buttonStatus));

    }

    @Override
    public void successfulLogin() {
        Toast.makeText(getActivity().getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void failedLogin(String message) {
        Toast.makeText(getActivity().getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();

    }
}

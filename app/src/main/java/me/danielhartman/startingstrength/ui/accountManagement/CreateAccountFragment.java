package me.danielhartman.startingstrength.ui.accountManagement;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.Interfaces.LoginCallback;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.network.LoginManager;
import me.danielhartman.startingstrength.ui.MyApplication;
import rx.Observable;

public class CreateAccountFragment extends Fragment {

    LoginManager.Login loginManager;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.passwordConfirmation)
    EditText passwordConfirmation;
    @BindView(R.id.emailTextInput)
    TextInputLayout emailTextInput;
    @BindView(R.id.passwordTextInput)
    TextInputLayout passwordTextInput;
    @BindView(R.id.confirmPasswordTextInput)
    TextInputLayout confirmPasswordTextInput;
    @BindView(R.id.btnSignIn)
    Button signInButton;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    boolean validInputs;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.createaccount_frag, container, false);
       loginManager = DaggerHolder.getInstance().component().getLoginManager();
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.btnSignIn)
    public void signUpUser() {
        checkInputs();
        if (validInputs) {
            progressBar.setVisibility(View.VISIBLE);
            loginManager.createAccount(username.getText().toString(), password.getText().toString());
        }
    }

    public void checkInputs() {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        Observable<Boolean> emailIsValid = RxTextView.textChangeEvents(username)
                .map(r -> r.text())
                .map(l -> pattern.matcher(l).matches());
        Observable<Boolean> passwordIsValid = RxTextView.textChangeEvents(password)
                .map(input -> input.text().toString())
                .map(input -> input.length() >= 6);
        Observable<Boolean> passwordsMatch = RxTextView.textChangeEvents(passwordConfirmation)
                .map(input -> input.text().toString())
                .map(input -> input.equalsIgnoreCase(password.getText().toString()));
        emailIsValid.distinctUntilChanged()
                .subscribe(validUsername -> {
                    if (!validUsername) {
                        emailTextInput.setError("Invalid email format");
                    } else {
                        emailTextInput.setError(null);
                    }
                });
        passwordIsValid.distinctUntilChanged()
                .subscribe(validPassword -> {
                    if (!validPassword) {
                        passwordTextInput.setError("Invalid email format");
                    } else {
                        passwordTextInput.setError(null);
                    }
                });
        passwordsMatch.distinctUntilChanged()
                .subscribe(validPassword -> {
                    if (!validPassword) {
                        confirmPasswordTextInput.setError("Passwords must match");
                    } else {
                        confirmPasswordTextInput.setError(null);
                    }
                });

        Observable<Boolean> validEmailAndPassword = Observable.combineLatest(emailIsValid, passwordIsValid, (a, b) -> a & b);
        Observable<Boolean> enabledButton = Observable.combineLatest(validEmailAndPassword, passwordsMatch, (a, b) -> a & b);
        enabledButton.distinctUntilChanged()
                .subscribe(enabled -> validInputs = (enabled));
    }
}

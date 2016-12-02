package me.danielhartman.startingstrength.ui.accountManagement;

import android.databinding.DataBindingUtil;
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
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.Interfaces.LoginCallback;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.databinding.CreateaccountFragBinding;
import me.danielhartman.startingstrength.network.LoginManager;
import me.danielhartman.startingstrength.ui.MyApplication;
import me.danielhartman.startingstrength.ui.base.BaseFragment;
import me.danielhartman.startingstrength.ui.base.BasePresenter;
import rx.Observable;

public class CreateAccountFragment extends BaseFragment {

    LoginManager.Login loginManager;

    boolean validInputs;

    CreateaccountFragBinding binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.createaccount_frag, container, false);
        setSignupOnClick();
        return binding.getRoot();
    }

    public void signUpUser() {
        checkInputs();
        if (validInputs) {
            binding.progressBar.setVisibility(View.VISIBLE);
            loginManager.createAccount(binding.username.getText().toString(), binding.password.getText().toString());
        }
    }

    public void setSignupOnClick(){
        binding.btnSignIn.setOnClickListener(v -> signUpUser());
    }

    public void checkInputs() {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        Observable<Boolean> emailIsValid = RxTextView.textChangeEvents(binding.username)
                .map(TextViewTextChangeEvent::text)
                .map(l -> pattern.matcher(l).matches());
        Observable<Boolean> passwordIsValid = RxTextView.textChangeEvents(binding.password)
                .map(input -> input.text().toString())
                .map(input -> input.length() >= 6);
        Observable<Boolean> passwordsMatch = RxTextView.textChangeEvents(binding.passwordConfirmation)
                .map(input -> input.text().toString())
                .map(input -> input.equalsIgnoreCase(binding.password.getText().toString()));
        emailIsValid.distinctUntilChanged()
                .subscribe(validUsername -> {
                    if (!validUsername) {
                        binding.emailTextInput.setError("Invalid email format");
                    } else {
                        binding.emailTextInput.setError(null);
                    }
                });
        passwordIsValid.distinctUntilChanged()
                .subscribe(validPassword -> {
                    if (!validPassword) {
                        binding.passwordTextInput.setError("Invalid email format");
                    } else {
                        binding.passwordTextInput.setError(null);
                    }
                });

        passwordsMatch.distinctUntilChanged()
                .subscribe(validPassword -> {
                    if (validPassword) {
                        binding.confirmPasswordTextInput.setError(null);
                    } else {
                        binding.confirmPasswordTextInput.setError("Passwords must match");
                    }
                });

        Observable<Boolean> validEmailAndPassword = Observable.combineLatest(emailIsValid, passwordIsValid, (a, b) -> a & b);
        Observable<Boolean> enabledButton = Observable.combineLatest(validEmailAndPassword, passwordsMatch, (a, b) -> a & b);
        enabledButton.distinctUntilChanged()
                .subscribe(enabled -> validInputs = (enabled));
    }

    @Override
    public void initDagger() {
        loginManager = DaggerHolder.getInstance().component().getLoginManager();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public Object getContractView() {
        return null;
    }
}

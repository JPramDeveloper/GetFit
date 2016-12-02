package me.danielhartman.startingstrength.ui.accountManagement;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.databinding.LoginFragBinding;
import me.danielhartman.startingstrength.network.LoginManager;
import me.danielhartman.startingstrength.ui.base.BaseFragment;
import me.danielhartman.startingstrength.ui.base.BasePresenter;

public class LoginFragment extends BaseFragment implements LoginManager.FailedLoginCallback {

    private static final String TAG = LoginFragment.class.getSimpleName();

    LoginManager.Login loginManger;
    LoginFragBinding binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_frag, container, false);
        setupLoginClick();
        setupNewAccountClick();
        return binding.getRoot();
    }

    public void setupLoginClick() {
        binding.login.setOnClickListener(v ->
                loginManger.login(binding.userName.getText().toString(), binding.password.getText().toString(), this));
    }

    public void setupNewAccountClick() {
        binding.newAccount.setOnClickListener(v -> startCreateAccount());
    }

    public void startCreateAccount() {
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

    @Override
    public void initDagger() {
        loginManger = DaggerHolder.getInstance().component().getLoginManager();
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

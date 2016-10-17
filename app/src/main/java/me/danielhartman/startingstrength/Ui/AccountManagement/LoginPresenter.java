package me.danielhartman.startingstrength.ui.accountManagement;


import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import me.danielhartman.startingstrength.Interfaces.LoginCallback;
import me.danielhartman.startingstrength.util.AlertUtil;

public class LoginPresenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private FirebaseUser user;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private AlertUtil alertUtil;

    public LoginPresenter(AlertUtil alertUtil) {
        this.alertUtil = alertUtil;
    }

    public FirebaseAuth getmFirebaseAuth() {
        if (mFirebaseAuth != null) {
            return mFirebaseAuth;
        } else {
            mFirebaseAuth = FirebaseAuth.getInstance();
            return mFirebaseAuth;
        }
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }

    public void createAccount(String email, String password, AccountActivity activity, LoginCallback callback) {
        mFirebaseAuth = getmFirebaseAuth();
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((task) -> {
                    if (task.isSuccessful()) {
                        callback.successfulLogin();
                        signIn(email, password);
                        Log.d("login", "createAccount: success");
                    } else {
                        if (task.getException() != null) {
                            callback.failedLogin("");
                            Log.d(TAG, task.getException().toString());
                            alertUtil.showErrorAlert(activity, task.getException().toString()).show();
                        }
                    }
                });
    }

    public void attachUserListener(LoginCallback callback) {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    setUser(user);
                    callback.successfulLogin();
                } else {
                    setUser(null);
                    callback.failedLogin("No user is signed in");
                }
            }
        };
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    public void detatchUserListener() {
        getmFirebaseAuth();
        mFirebaseAuth.removeAuthStateListener(mAuthListener);
    }


    public void signIn(String email, String password) {
        mFirebaseAuth = getmFirebaseAuth();
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((task) -> {
                    if (!task.isSuccessful()) {
                    }
                });
    }


    public void logout() {
        getmFirebaseAuth();
        mFirebaseAuth.signOut();
    }

    public String getUserUID(Activity activity){
        if (getUser()!=null){
            return getUser().getUid();
        }else{
            Intent i = new Intent(activity.getApplicationContext(), AccountActivity.class);
            activity.startActivity(i);
            return null;
        }
    }
}

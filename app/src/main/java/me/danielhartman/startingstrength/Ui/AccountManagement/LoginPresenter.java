package me.danielhartman.startingstrength.ui.accountManagement;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import me.danielhartman.startingstrength.Interfaces.LoginCallback;

public class LoginPresenter {
    private FirebaseUser user;
    private FirebaseAuth mFirebaseAuth;
    private Boolean loginSuccess;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public LoginPresenter() {
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

    public void createAccount(String email, String password, LoginCallback callback) {
        mFirebaseAuth = getmFirebaseAuth();
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((task) -> {
                    if (task.isSuccessful()) {
                        signIn(email, password);
                        Log.d("login", "createAccount: success");
                    } else {
                        //add error catching eventually
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
}

package me.danielhartman.startingstrength.network;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;

public class FirebaseLoginManager implements LoginManager.Login {
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private FirebaseUser user;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseAuth getmFirebaseAuth() {
        if (mFirebaseAuth != null) {
            return mFirebaseAuth;
        } else {
            mFirebaseAuth = FirebaseAuth.getInstance();
            return mFirebaseAuth;
        }
    }

    private FirebaseUser getUser() {
        return user;
    }

    private void setUser(FirebaseUser user) {
        this.user = user;
    }

    private void createNewAccount(String email, String password,
                                  LoginManager.CreateAccountCallback callback) {
        mFirebaseAuth = getmFirebaseAuth();
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((task) -> {
                    if (task.isSuccessful()) {
                        callback.successfulAccountCreation();
                        signIn(email, password);
                        Log.d("login", "createNewAccount: success");
                    } else {
                        if (task.getException() != null) {
                            callback.failedAccountCreation("");
                            Log.d(TAG, task.getException().toString());
                        }
                    }
                });
    }

    private void attachUserListener(LoginManager.LoginCallback LoginCallback) {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user1 = firebaseAuth.getCurrentUser();
            if (user1 != null) {
                setUser(user1);
                LoginCallback.successfulLogin();
                detatchUserListener();
            } else {
                setUser(null);
                LoginCallback.failedLogin("Failed");
//                detatchUserListener();
            }
        };
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    private void detatchUserListener() {
        getmFirebaseAuth();
        mFirebaseAuth.removeAuthStateListener(mAuthListener);
    }


    private void signIn(String email, String password) {
        mFirebaseAuth = getmFirebaseAuth();
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((task) -> {
                    if (!task.isSuccessful()) {
                    }
                });
    }

    @Override
    public void login(LoginManager.LoginCallback LoginCallback, String email, String password) {
        attachUserListener(LoginCallback);
        signIn(email, password);
    }

    @Override
    public String getUserId() {
        if (getUser() != null) {
            return getUser().getUid();
        } else {
            return null;
        }
    }

    @Override
    public void logOut(LoginManager.LoginCallback LoginCallback) {
        getmFirebaseAuth();
        mFirebaseAuth.signOut();
    }

    @Override
    public void createAccount(LoginManager.CreateAccountCallback callback, String email, String password) {
        createNewAccount(email, password, callback);
    }
}

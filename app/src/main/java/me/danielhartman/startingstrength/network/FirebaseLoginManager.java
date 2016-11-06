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

    private void createNewAccount(String email, String password) {
        mFirebaseAuth = getmFirebaseAuth();
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((task) -> {
                    if (task.isSuccessful()) {
                        signIn(email, password, null);
                        Log.d("login", "createNewAccount: success");
                    } else {
                        if (task.getException() != null) {
                            //// TODO: 11/5/2016 Add message for users to know what the error was 
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
            }
        };
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    private void detatchUserListener() {
        getmFirebaseAuth();
        mFirebaseAuth.removeAuthStateListener(mAuthListener);
    }


    private void signIn(String email, String password, LoginManager.FailedLoginCallback callback) {
        mFirebaseAuth = getmFirebaseAuth();
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((task) -> {
                    if (!task.isSuccessful()) {
                        if (callback!=null) {
                            callback.failedLogin(task.getException().toString());
                        }
                    }
                });
    }

    @Override
    public void login(String email, String password, LoginManager.FailedLoginCallback callback) {
        signIn(email, password, callback);
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
    public void logOut() {
        getmFirebaseAuth();
        mFirebaseAuth.signOut();
    }

    @Override
    public void createAccount(String email, String password) {
        createNewAccount(email, password);
    }

    @Override
    public void checkIfUserLoggedIn(LoginManager.LoginCallback callback) {
        attachUserListener(callback);
    }
}

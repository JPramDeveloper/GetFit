package me.danielhartman.startingstrength.Network;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.util.Log;

import me.danielhartman.startingstrength.Interfaces.FirebaseUserCallback;
import me.danielhartman.startingstrength.Interfaces.LoginCallback;

public class LoginNetworkCalls {
    private FirebaseUser user;
    private FirebaseAuth mFirebaseAuth;

    public LoginNetworkCalls() {
    }

    public void loginUser(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                } else {
                    e.printStackTrace();
                }
            }
        });
    }
    public FirebaseAuth getmFirebaseAuth(){
        if (mFirebaseAuth!=null){
            return mFirebaseAuth;
        }else {
            mFirebaseAuth=FirebaseAuth.getInstance();
            return mFirebaseAuth;
        }
    }

    public ParseUser getCurrentUser() {
        return ParseUser.getCurrentUser();
    }

    public void getFirebaseUser(FirebaseUserCallback mCallback) {

        mFirebaseAuth = getmFirebaseAuth();
        mFirebaseAuth.addAuthStateListener((firebaseAuth) -> {
            user = firebaseAuth.getCurrentUser();
            if (user!=null) {
                mCallback.returnUserSuccess(user);
            }else{
                mCallback.returnNullUser(user);
            }
        });
    }
    public void createAccount(String email, String password, LoginCallback mCallback) {
        mFirebaseAuth = getmFirebaseAuth();
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((task)-> {
                    if (task.isSuccessful()){
                        signIn(email,password, mCallback);
                        Log.d("login", "createAccount: success");
                    }else {
                        mCallback.failedLogin();
                    }
                });
    }

    private void signIn(String email, String password, LoginCallback mCallback) {
        mFirebaseAuth = getmFirebaseAuth();
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((task)-> {
                        if (!task.isSuccessful()) {
                            mCallback.successfulLogin();
                    }
                });
    }
}

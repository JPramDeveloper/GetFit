package me.danielhartman.startingstrength.network;

public class LoginManager {
    public interface Login {
        void login(LoginCallback LoginCallback, String email, String password);

        String getUserId();

        void logOut(LoginCallback LoginCallback);

        void createAccount(CreateAccountCallback callback, String email, String password);
    }

    public interface LoginCallback {
        void successfulLogin();

        void failedLogin(String error);

        void loggedOut();
    }

    public interface CreateAccountCallback {
        void successfulAccountCreation();

        void failedAccountCreation(String error);
    }


}

package me.danielhartman.startingstrength.network;

public class LoginManager {
    public interface Login {
        void login(String email, String password, FailedLoginCallback callback);

        String getUserId();

        void logOut();

        void createAccount(String email, String password);

        void checkIfUserLoggedIn(LoginCallback callback);
    }

    public interface LoginCallback {
        void successfulLogin();

        void failedLogin(String error);

    }

    public interface CreateAccountCallback {
        void successfulAccountCreation();

        void failedAccountCreation(String error);
    }

    public interface FailedLoginCallback {
        void failedLogin(String error);
    }

}

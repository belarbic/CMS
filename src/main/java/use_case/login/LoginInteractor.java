package use_case.login;

import entity.User;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private static final String API_KEY = "AIzaSyBqkrvRoPFfXPg7eU2_bZk6l_lDcUgZav0";
    private static final String SIGNIN_URL =
            "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY;
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();

        // Check if username or password is empty
        if (username == null || username.trim().isEmpty()) {
            loginPresenter.prepareFailView("Username cannot be empty.");
            return;
        }

        if (password == null || password.trim().isEmpty()) {
            loginPresenter.prepareFailView("Password cannot be empty.");
            return;
        }

        // Check if the username exists
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
            return;
        }

        final String pwd = userDataAccessObject.get(username).getPassword();

        // Check if the password matches
        if (!password.equals(pwd)) {
            loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            return;
        }

        // Successful login logic
        final User user = userDataAccessObject.get(username);
        userDataAccessObject.setCurrentUsername(user.getName());
        final LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
        loginPresenter.prepareSuccessView(loginOutputData);
    }

    @Override
    public void switchToSignUpView() {
        loginPresenter.switchToSignUpView();
    }
}


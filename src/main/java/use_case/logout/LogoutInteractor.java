package use_case.logout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private final LogoutUserDataAccessInterface userDataAccessObject;
    private final LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutUserDataAccessInterface userDataAccessInterface,
                            LogoutOutputBoundary logoutOutputBoundary) {
        // Which parameter is the DAO and which is the presenter?
        // DAO Parameter.
        this.userDataAccessObject = userDataAccessInterface;
        // Presenter Parameter.
        this.logoutPresenter = logoutOutputBoundary;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        // * get the username out of the input data,
        // * set the username to null in the DAO
        // * instantiate the `LogoutOutputData`, which needs to contain the username.
        // * tell the presenter to prepare a success view.
        final String username = logoutInputData.getUsername();
        userDataAccessObject.setCurrentUsername(null);
        final LogoutOutputData logoutOutputData = new LogoutOutputData(username, false);
        try {
            final String userUid = userDataAccessObject.getUidByUsername(username);
            FirebaseAuth.getInstance().revokeRefreshTokens(userUid);
            System.out.println("Successfully Logged Out");
        }
        catch (FirebaseAuthException exception) {
            throw new RuntimeException(exception);
        }
        logoutPresenter.prepareSuccessView(logoutOutputData);
    }
}


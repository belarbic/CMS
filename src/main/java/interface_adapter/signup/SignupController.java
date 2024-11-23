package interface_adapter.signup;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;
// import com.google.firebase.auth.FirebaseAuth;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

// import java.io.FileInputStream;
// import com.google.firebase.auth.UserRecord;
// import com.google.firebase.auth.UserRecord.CreateRequest;

/**
 * Controller for the Signup Use Case.
 */
public class SignupController {
    private final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * @param username the username to sign up
     * @param password1 the password
     * @param password2 the password repeated
     */
    public void execute(String username, String password1, String password2) {
        final SignupInputData signupInputData = new SignupInputData(
                username, password1, password2);

        userSignupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        userSignupUseCaseInteractor.switchToLoginView();
    }
}

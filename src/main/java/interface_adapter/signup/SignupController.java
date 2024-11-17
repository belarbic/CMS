package interface_adapter.signup;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

import java.io.FileInputStream;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;


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
        initializeFirebase();
        signUpUser(username, password1);

        userSignupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        userSignupUseCaseInteractor.switchToLoginView();
    }
    public static void initializeFirebase() {
        try (FileInputStream fileInputStream = new FileInputStream("cmspractice-uoft-710dcd9b775c.json")) {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(fileInputStream))
                    .build();
            FirebaseApp app = FirebaseApp.initializeApp(options);
            System.out.println("Firebase initialized Successfully");
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void signUpUser(String email, String password) {
        try {
            CreateRequest request = new CreateRequest()
                    .setEmail(email)
                    .setPassword(password);
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            System.out.println("Successfully created user: " + userRecord.getUid());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

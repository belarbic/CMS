package interface_adapter.login;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * The controller for the Login Use Case.
 */
public class LoginController {

    private static final String API_KEY = "AIzaSyBqkrvRoPFfXPg7eU2_bZk6l_lDcUgZav0";
    private static final String SIGNIN_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY;
    private final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the Login Use Case.
     * @param username the username of the user logging in
     * @param password the password of the user logging in
     */
    public void execute(String username, String password) {
        final LoginInputData loginInputData = new LoginInputData(
                username, password);
//        initializeFirebase();
        boolean result = signInUser(username, password);
        System.out.println("result: " + result);

        loginUseCaseInteractor.execute(loginInputData);
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
    public boolean signInUser(String email, String password) {
        try {
            URL url = new URL(SIGNIN_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email, password);
            try (OutputStream outputStream = connection.getOutputStream()) {
                byte [] payloadBytes = jsonInputString.getBytes(StandardCharsets.UTF_8);
                outputStream.write(payloadBytes, 0, payloadBytes.length);
            }
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (Scanner scanner = new Scanner(connection.getInputStream())) {
                    StringBuilder response = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        response.append(scanner.nextLine());
                    }
                    System.out.println(response.toString());
                    return true;
                }
            }
            else {
                System.out.println(connection.getResponseCode());
                try (Scanner scanner = new Scanner(connection.getErrorStream())) {
                    StringBuilder errorResponse = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        errorResponse.append(scanner.nextLine());
                    }
                    System.out.println(errorResponse.toString());
                    return false;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

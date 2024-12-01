package use_case.login;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {
//                final boolean result = signInFirebaseUser(username, password);
//                System.out.println("result: " + result);
                final User user = userDataAccessObject.get(loginInputData.getUsername());

                userDataAccessObject.setCurrentUsername(user.getName());
                final LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    /**
     * Sends the JSON (email and password) to Firebase.
     * @param email username of user.
     * @param password password.
     * @return If the server responds with HTTP status 200 (HTTP_OK) it returns true.
     */
    public boolean signInFirebaseUser(String email, String password) {
        boolean result = false;
        try {
            final URL url = new URL(SIGNIN_URL);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            final String jsonInputString =
                    String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email, password);
            try (OutputStream outputStream = connection.getOutputStream()) {
                final byte[] payloadBytes = jsonInputString.getBytes(StandardCharsets.UTF_8);
                outputStream.write(payloadBytes, 0, payloadBytes.length);
            }
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (Scanner scanner = new Scanner(connection.getInputStream())) {
                    final StringBuilder response = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        response.append(scanner.nextLine());
                    }
                    System.out.println(response.toString());
                    result = true;
                }
            }
            else {
                System.out.println(connection.getResponseCode());
                try (Scanner scanner = new Scanner(connection.getErrorStream())) {
                    final StringBuilder errorResponse = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        errorResponse.append(scanner.nextLine());
                    }
                    System.out.println(errorResponse.toString());
                    result = false;
                }
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        return result;
    }
}

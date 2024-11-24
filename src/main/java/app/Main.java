package app;

import javax.swing.JFrame;
import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     */

    public static void initializeFirebase() {
        try (FileInputStream fileInputStream = new FileInputStream("cmspractice-uoft-710dcd9b775c.json")) {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(fileInputStream))    // Load Firebase credentials.
                    .setDatabaseUrl("https://cmspractice-uoft-default-rtdb.firebaseio.com/")
                    .build();
            FirebaseApp app = FirebaseApp.initializeApp(options);    // Initialize Firebase app.
            System.out.println("Firebase initialized Successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        chatService.addMessageListener("groupId1");
//        chatService.sendMessage("groupId1", "Hello World!", "TestUser");
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addChangePasswordUseCase()
                                            .addLogoutUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
        initializeFirebase();
        ChatService chatService = new ChatService();
        chatService.getChatList();
    }
}

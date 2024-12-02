package app;

import java.io.FileInputStream;

import javax.swing.JFrame;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

/**
 * The Main class of our application.
 */
public class Main {

    static final int WIDTH = 600;
    static final int HEIGHT = 550;

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
        initializeFirebase();
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addViewChatRoomsView()
                                            .addViewChatRoomsUseCase()
                                            .addChatRoomView()
                                            .addChatRoomUseCase()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addChangePasswordView()
                                            .addChangePasswordUseCase()
                                            .addLogoutUseCase()
                                            .addCreateChatRoomView()
                                            .addCreateChatRoomUseCase()
                                            .addSearchMessageView()
                                            .addSearchMessageUseCase()
                                            .addEditMessageView()
                                            .addEditMessageUseCase()
                                            .build();

        application.pack();
        application.setSize(WIDTH, HEIGHT);
        application.setVisible(true);
        ChatService chatService = new ChatService();
        chatService.getChatList();
    }
}

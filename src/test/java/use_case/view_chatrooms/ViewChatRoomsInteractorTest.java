package use_case.view_chatrooms;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.internal.FirebaseService;
import entity.ChatRoom;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.create_chatroom.CreateChatRoomUserDataAccessInterface;
import use_case.login.LoginInputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.login.LoginUserDataAccessInterface;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ViewChatRoomsInteractorTest {
    private ViewChatRoomsUserDataAccessInterface userDataAccessObject;
    private ViewChatRoomsOutputBoundary userPresenter;
    private UserFactory userFactory;
    private ViewChatRoomsInteractor viewChatRoomsInteractor;

    @Test
    void successTest() {
        ChatRoom inputData = new ChatRoom("New", "hi");
        ViewChatRoomsUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        ViewChatRoomsOutputBoundary successPresenter = new ViewChatRoomsOutputBoundary() {
            @Override
            public void prepareSuccessView(ViewChatRoomsOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals(inputData, user.getClass());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoggedInView() {
            }

            @Override
            public void openChatRoom() {

            }
        };

        ViewChatRoomsInputBoundary interactor = new ViewChatRoomsInteractor(userRepository, successPresenter);
        interactor.execute(new ViewChatRoomsInputData());
    }
}
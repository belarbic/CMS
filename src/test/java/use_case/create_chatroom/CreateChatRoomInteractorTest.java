package use_case.create_chatroom;

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

class CreateChatRoomInteractorTest {
    private CreateChatRoomUserDataAccessInterface userDataAccessObject;
    private CreateChatRoomOutputBoundary userPresenter;
    private UserFactory userFactory;
    private CreateChatRoomInteractor createChatRoomInteractor;

    @Test
    void successTest() {
        CreateChatRoomInputData inputData = new CreateChatRoomInputData("New", "hi");
        CreateChatRoomUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        CreateChatRoomOutputBoundary successPresenter = new CreateChatRoomOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateChatRoomOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("New", user.getName());
                assertEquals("hi", user.getFirstMessage());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoggedInView() {
            }
        };

        CreateChatRoomInputBoundary interactor = new CreateChatRoomInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void testEmptyName() {
        CreateChatRoomInputData inputData = new CreateChatRoomInputData("", "hi");
        CreateChatRoomUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        CreateChatRoomOutputBoundary failPresenter = new CreateChatRoomOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateChatRoomOutputData user) {
                fail("Expected failure but got success");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Name cannot be empty", error);  // Assuming validation gives this error message
            }

            @Override
            public void switchToLoggedInView() {
            }
        };

        CreateChatRoomInputBoundary interactor = new CreateChatRoomInteractor(userRepository, failPresenter);
        interactor.execute(inputData);
    }

    @Test
    void testEmptyMessage() {
        CreateChatRoomInputData inputData = new CreateChatRoomInputData("New", "");
        CreateChatRoomUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        CreateChatRoomOutputBoundary failPresenter = new CreateChatRoomOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateChatRoomOutputData user) {
                fail("Expected failure but got success");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Message cannot be empty", error);  // Assuming validation gives this error message
            }

            @Override
            public void switchToLoggedInView() {
            }
        };

        CreateChatRoomInputBoundary interactor = new CreateChatRoomInteractor(userRepository, failPresenter);
        interactor.execute(inputData);
    }

    @Test
    void testNullName() {
        CreateChatRoomInputData inputData = new CreateChatRoomInputData(null, "hi");
        CreateChatRoomUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        CreateChatRoomOutputBoundary failPresenter = new CreateChatRoomOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateChatRoomOutputData user) {
                fail("Expected failure but got success");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Name cannot be null", error);  // Assuming validation gives this error message
            }

            @Override
            public void switchToLoggedInView() {
            }
        };

        CreateChatRoomInputBoundary interactor = new CreateChatRoomInteractor(userRepository, failPresenter);
        interactor.execute(inputData);
    }

    @Test
    void testDuplicateRoomName() {
        CreateChatRoomUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        CreateChatRoomInputData inputData1 = new CreateChatRoomInputData("New", "hi");
        CreateChatRoomInputData inputData2 = new CreateChatRoomInputData("New", "hello");

        CreateChatRoomOutputBoundary successPresenter = new CreateChatRoomOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateChatRoomOutputData user) {
                assertEquals("New", user.getName());
                assertEquals("hi", user.getFirstMessage());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Unexpected failure");
            }

            @Override
            public void switchToLoggedInView() {
            }
        };

        CreateChatRoomInputBoundary interactor1 = new CreateChatRoomInteractor(userRepository, successPresenter);
        interactor1.execute(inputData1);

        // Now try to create a second room with the same name, expecting failure
        CreateChatRoomOutputBoundary failPresenter = new CreateChatRoomOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateChatRoomOutputData user) {
                fail("Expected failure but got success");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Room with name 'New' already exists", error);
            }

            @Override
            public void switchToLoggedInView() {
            }
        };

        CreateChatRoomInputBoundary interactor2 = new CreateChatRoomInteractor(userRepository, failPresenter);
        interactor2.execute(inputData2);
    }

    @Test
    void testNullPresenter() {
        CreateChatRoomInputData inputData = new CreateChatRoomInputData("New", "hi");
        CreateChatRoomUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        CreateChatRoomInputBoundary interactor = new CreateChatRoomInteractor(userRepository, null);

        try {
            interactor.execute(inputData);
            fail("Expected exception due to null presenter");
        } catch (NullPointerException e) {
            assertEquals("Presenter cannot be null", e.getMessage());
        }
    }
}

package use_case.edit_message;

import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EditMessageInteractorTest {

    private InMemoryUserDataAccessObject userRepository;
    private EditMessageInteractor interactor;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserDataAccessObject();
    }

    @Test
    void successTest() {
        EditMessageInputData inputData = new EditMessageInputData("message-1", "updated content", "Paul");

        // Set up test data
        ChatRoom chatRoom = new ChatRoom("TestRoom", "first message");
        Message message = new Message("original content", "Paul");
        message.setId("message-1");
        chatRoom.addMessage(message);
        User user = new CommonUser("Paul", "password", new ArrayList<>());

        userRepository.save(user);
        userRepository.save(chatRoom);

        EditMessageOutputBoundary successPresenter = new EditMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(EditMessageOutputData outputData) {
                Message updatedMessage = userRepository.getMessage("message-1");
                assertEquals("updated content", outputData.getNewContent());
                // Assuming 'isEdited' is updated internally after editing
                assertNotNull(updatedMessage, "Updated message should not be null");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        interactor = new EditMessageInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureDeletedMessageTest() {
        EditMessageInputData inputData = new EditMessageInputData("message-1", "updated content", "Paul");

        // Set up test data with deleted message
        ChatRoom chatRoom = new ChatRoom("TestRoom", "first message");
        Message message = new Message("original content", "Paul");
        message.setId("message-1");
        message.delete();  // Mark as deleted
        chatRoom.addMessage(message);
        User user = new CommonUser("Paul", "password", new ArrayList<>());

        userRepository.save(user);
        userRepository.save(chatRoom);

        EditMessageOutputBoundary failurePresenter = new EditMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(EditMessageOutputData outputData) {
                fail("Use case success is unexpected for deleted message.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Cannot edit a deleted message", error);
            }
        };

        interactor = new EditMessageInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureNullContentTest() {
        EditMessageInputData inputData = new EditMessageInputData("message-1", null, "Paul");

        // Set up basic user data
        User user = new CommonUser("Paul", "password", new ArrayList<>());
        userRepository.save(user);

        EditMessageOutputBoundary failurePresenter = new EditMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(EditMessageOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Message content cannot be empty", error);
            }
        };

        interactor = new EditMessageInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureEmptyContentTest() {
        EditMessageInputData inputData = new EditMessageInputData("message-1", "", "Paul");

        // Set up basic user data
        User user = new CommonUser("Paul", "password", new ArrayList<>());
        userRepository.save(user);

        EditMessageOutputBoundary failurePresenter = new EditMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(EditMessageOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Message content cannot be empty", error);
            }
        };

        interactor = new EditMessageInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureWhitespaceContentTest() {
        EditMessageInputData inputData = new EditMessageInputData("message-1", "   ", "Paul");

        // Set up basic user data
        User user = new CommonUser("Paul", "password", new ArrayList<>());
        userRepository.save(user);

        EditMessageOutputBoundary failurePresenter = new EditMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(EditMessageOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Message content cannot be empty", error);
            }
        };

        interactor = new EditMessageInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureUserNotFoundTest() {
        EditMessageInputData inputData = new EditMessageInputData("message-1", "new content", "Paul");

        EditMessageOutputBoundary failurePresenter = new EditMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(EditMessageOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User not found", error);
            }
        };

        interactor = new EditMessageInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureMessageNotFoundTest() {
        EditMessageInputData inputData = new EditMessageInputData("nonexistent-message", "new content", "Paul");

        // Set up user but no message
        ChatRoom chatRoom = new ChatRoom("TestRoom", "first message");
        User user = new CommonUser("Paul", "password", new ArrayList<>());
        userRepository.save(user);
        userRepository.save(chatRoom);

        EditMessageOutputBoundary failurePresenter = new EditMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(EditMessageOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Message not found", error);
            }
        };

        interactor = new EditMessageInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureNotMessageOwnerTest() {
        EditMessageInputData inputData = new EditMessageInputData("message-1", "new content", "Paul");

        // Set up message owned by different user
        ChatRoom chatRoom = new ChatRoom("TestRoom", "first message");
        Message message = new Message("original content", "John");  // Different owner
        message.setId("message-1");
        //message.setEdited(false); // Explicitly set edited to false
        chatRoom.addMessage(message);
        User user = new CommonUser("Paul", "password", new ArrayList<>());
        userRepository.save(user);
        userRepository.save(chatRoom);

        EditMessageOutputBoundary failurePresenter = new EditMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(EditMessageOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("You can only edit your own messages", error);
            }
        };

        interactor = new EditMessageInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}

package use_case.delete_message;

import data_access.InMemoryUserDataAccessObject;
import entity.ChatRoom;
import entity.CommonUser;
import entity.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.delete_message.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeleteMessageInteractorTest {
    private InMemoryUserDataAccessObject userRepository;
    private DeleteMessageInteractor interactor;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserDataAccessObject();
    }

    @Test
    void successTest() {
        // Arrange
        DeleteMessageInputData inputData = new DeleteMessageInputData("message-1", "TestRoom");

        // Create and set up the chat room, message, and user
        ChatRoom chatRoom = new ChatRoom("TestRoom", "Welcome message");
        Message message = new Message("Hello World", "Paul");
        message.setId("message-1"); // Set the message ID
        chatRoom.addMessage(message);
        userRepository.save(chatRoom);

        DeleteMessageOutputBoundary successPresenter = new DeleteMessageOutputBoundary() {
            @Override
            public void presentSuccessView(DeleteMessageOutputData outputData) {
                // Assert success output
                assertEquals("message-1", outputData.getMessageId());
                assertEquals("This message has been deleted", outputData.getContent());

                // Verify the message is marked as deleted
                Message updatedMessage = userRepository.getMessageById("message-1", "TestRoom");
                assertEquals("This message has been deleted", updatedMessage.getContent());
                assertTrue(updatedMessage.deleted());
            }

            @Override
            public void presentFailView(String errorMessage) {
                fail("Expected success but got failure: " + errorMessage);
            }
        };

        interactor = new DeleteMessageInteractor(userRepository, successPresenter);

        // Act
        interactor.execute(inputData);
    }

    @Test
    void failureChatRoomNotFoundTest() {
        // Arrange
        DeleteMessageInputData inputData = new DeleteMessageInputData("message-1",
                "NonexistentRoom");

        DeleteMessageOutputBoundary failurePresenter = new DeleteMessageOutputBoundary() {
            @Override
            public void presentSuccessView(DeleteMessageOutputData outputData) {
                fail("Expected failure but got success");
            }

            @Override
            public void presentFailView(String errorMessage) {
                // Assert failure output
                assertEquals("Chat room not found", errorMessage);
            }
        };

        interactor = new DeleteMessageInteractor(userRepository, failurePresenter);

        // Act
        interactor.execute(inputData);
    }

    @Test
    void failureMessageNotFoundTest() {
        // Arrange
        DeleteMessageInputData inputData = new DeleteMessageInputData("nonexistent-message",
                "TestRoom");

        // Create and set up the chat room
        ChatRoom chatRoom = new ChatRoom("TestRoom", "Welcome message");
        userRepository.save(chatRoom);

        DeleteMessageOutputBoundary failurePresenter = new DeleteMessageOutputBoundary() {
            @Override
            public void presentSuccessView(DeleteMessageOutputData outputData) {
                fail("Expected failure but got success");
            }

            @Override
            public void presentFailView(String errorMessage) {
                // Assert failure output
                assertEquals("Message not found", errorMessage);
            }
        };

        interactor = new DeleteMessageInteractor(userRepository, failurePresenter);

        // Act
        interactor.execute(inputData);
    }

    @Test
    void failureNullMessageIdTest() {
        // Arrange
        DeleteMessageInputData inputData = new DeleteMessageInputData(null, "TestRoom");

        // Create and set up the chat room
        ChatRoom chatRoom = new ChatRoom("TestRoom", "Welcome message");
        userRepository.save(chatRoom);

        DeleteMessageOutputBoundary failurePresenter = new DeleteMessageOutputBoundary() {
            @Override
            public void presentSuccessView(DeleteMessageOutputData outputData) {
                fail("Expected failure but got success");
            }

            @Override
            public void presentFailView(String errorMessage) {
                // Assert failure output
                assertEquals("Message not found", errorMessage);
            }
        };

        interactor = new DeleteMessageInteractor(userRepository, failurePresenter);

        // Act
        interactor.execute(inputData);
    }

    @Test
    void failureNullChatRoomNameTest() {
        // Arrange
        DeleteMessageInputData inputData = new DeleteMessageInputData("message-1", null);

        DeleteMessageOutputBoundary failurePresenter = new DeleteMessageOutputBoundary() {
            @Override
            public void presentSuccessView(DeleteMessageOutputData outputData) {
                fail("Expected failure but got success");
            }

            @Override
            public void presentFailView(String errorMessage) {
                // Assert failure output
                assertEquals("Chat room not found", errorMessage);
            }
        };

        interactor = new DeleteMessageInteractor(userRepository, failurePresenter);

        // Act
        interactor.execute(inputData);
    }
}

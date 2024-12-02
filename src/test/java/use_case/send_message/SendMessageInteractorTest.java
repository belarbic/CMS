package use_case.send_message;

import data_access.InMemoryUserDataAccessObject;
import entity.ChatRoom;
import entity.CommonUser;
import entity.Message;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SendMessageInteractorTest {
    private InMemoryUserDataAccessObject userRepository;
    private SendMessageInteractor interactor;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserDataAccessObject();
    }

    @Test
    void successTest() {
        SendMessageInputData inputData = new SendMessageInputData("Hello", "Paul",
                "TestRoom");
        ChatRoom chatRoom = new ChatRoom("TestRoom", "Welcome message");
        User user = new CommonUser("Paul", "password", new ArrayList<>());
        chatRoom.addParticipants(user);
        userRepository.save(user);
        userRepository.save(chatRoom);

        SendMessageOutputBoundary successPresenter = new SendMessageOutputBoundary() {
            @Override
            public void presentSuccessView(SendMessageOutputData outputData) {
                // Assert success outcome
                assertEquals("Hello", outputData.getContent());
                assertEquals("Paul", outputData.getSenderUsername());
                assertNotNull(outputData.getTimestamp());

                ChatRoom updatedChatRoom = userRepository.getChatRoomByName("TestRoom");
                assertEquals(2, updatedChatRoom.getMessages().size()); // Includes initial message
                Message sentMessage = updatedChatRoom.getMessages().get(1);
                assertEquals("Hello", sentMessage.getContent());
                assertEquals("Paul", sentMessage.getSender());
            }

            @Override
            public void presentFailView(String errorMessage) {
                fail("Expected success but got failure: " + errorMessage);
            }
        };

        interactor = new SendMessageInteractor(userRepository, successPresenter);

        interactor.execute(inputData);
    }

    @Test
    void failureEmptyContentTest() {
        SendMessageInputData inputData = new SendMessageInputData("", "Paul",
                "TestRoom");
        ChatRoom chatRoom = new ChatRoom("TestRoom", "Welcome message");
        User user = new CommonUser("Paul", "password", new ArrayList<>());
        chatRoom.addParticipants(user);
        userRepository.save(user);
        userRepository.save(chatRoom);

        SendMessageOutputBoundary failurePresenter = new SendMessageOutputBoundary() {
            @Override
            public void presentSuccessView(SendMessageOutputData outputData) {
                fail("Expected failure but got success");
            }

            @Override
            public void presentFailView(String errorMessage) {
                // Assert failure outcome
                assertEquals("Message content cannot be empty", errorMessage);
            }
        };

        interactor = new SendMessageInteractor(userRepository, failurePresenter);

        interactor.execute(inputData);
    }

    @Test
    void failureChatRoomNotFoundTest() {
        SendMessageInputData inputData = new SendMessageInputData("Hello", "Paul",
                "NonexistentRoom");
        User user = new CommonUser("Paul", "password", new ArrayList<>());
        userRepository.save(user);

        SendMessageOutputBoundary failurePresenter = new SendMessageOutputBoundary() {
            @Override
            public void presentSuccessView(SendMessageOutputData outputData) {
                fail("Expected failure but got success");
            }

            @Override
            public void presentFailView(String errorMessage) {
                // Assert failure outcome
                assertEquals("Chat room not found", errorMessage);
            }
        };

        interactor = new SendMessageInteractor(userRepository, failurePresenter);

        interactor.execute(inputData);
    }

    @Test
    void failureSenderNotInChatRoomTest() {
        SendMessageInputData inputData = new SendMessageInputData("Hello", "Paul", "TestRoom");
        ChatRoom chatRoom = new ChatRoom("TestRoom", "Welcome message");
        userRepository.save(chatRoom);

        SendMessageOutputBoundary failurePresenter = new SendMessageOutputBoundary() {
            @Override
            public void presentSuccessView(SendMessageOutputData outputData) {
                fail("Expected failure but got success");
            }

            @Override
            public void presentFailView(String errorMessage) {
                // Assert failure outcome
                assertEquals("Sender not found in chat room", errorMessage);
            }
        };

        interactor = new SendMessageInteractor(userRepository, failurePresenter);

        interactor.execute(inputData);
    }

    @Test
    void failureNullContentTest() {
        SendMessageInputData inputData = new SendMessageInputData(null, "Paul", "TestRoom");
        ChatRoom chatRoom = new ChatRoom("TestRoom", "Welcome message");
        User user = new CommonUser("Paul", "password", new ArrayList<>());
        chatRoom.addParticipants(user);
        userRepository.save(user);
        userRepository.save(chatRoom);

        SendMessageOutputBoundary failurePresenter = new SendMessageOutputBoundary() {
            @Override
            public void presentSuccessView(SendMessageOutputData outputData) {
                fail("Expected failure but got success");
            }

            @Override
            public void presentFailView(String errorMessage) {
                // Assert failure outcome
                assertEquals("Message content cannot be empty", errorMessage);
            }
        };

        interactor = new SendMessageInteractor(userRepository, failurePresenter);

        interactor.execute(inputData);
    }
}

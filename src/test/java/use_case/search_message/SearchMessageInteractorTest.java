
package use_case.search_message;

import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchMessageInteractorTest {

    @Test
    void successTest() {
        SearchMessageInputData inputData = new SearchMessageInputData("hello", "Paul");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Set up test data
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        ChatRoom chatRoom = new ChatRoom("TestRoom", "first message");
        Message message = new Message("hello world", "Paul");
        chatRoom.addMessage(message);
        user.getChatRooms().add(chatRoom);
        userRepository.save(user);

        SearchMessageOutputBoundary successPresenter = new SearchMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchMessageOutputData messages) {
                ArrayList<Message> foundMessages = messages.getMessages();
                assertEquals(1, foundMessages.size());
                assertEquals("hello world", foundMessages.get(0).getContent());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchMessageInputBoundary interactor = new SearchMessageInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void successMultipleMatchesTest() {
        SearchMessageInputData inputData = new SearchMessageInputData("hello", "Paul");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Set up test data with multiple matches
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");

        ChatRoom chatRoom1 = new ChatRoom("Room1", "first message");
        chatRoom1.addMessage(new Message("hello world", "Paul"));
        chatRoom1.addMessage(new Message("hello there", "Paul"));

        ChatRoom chatRoom2 = new ChatRoom("Room2", "first message");
        chatRoom2.addMessage(new Message("saying hello", "Paul"));

        user.getChatRooms().add(chatRoom1);
        user.getChatRooms().add(chatRoom2);
        userRepository.save(user);

        SearchMessageOutputBoundary successPresenter = new SearchMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchMessageOutputData messages) {
                ArrayList<Message> foundMessages = messages.getMessages();
                assertEquals(3, foundMessages.size());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchMessageInputBoundary interactor = new SearchMessageInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureNullKeywordTest() {
        SearchMessageInputData inputData = new SearchMessageInputData(null, "Paul");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        SearchMessageOutputBoundary failurePresenter = new SearchMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchMessageOutputData messages) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Search keyword cannot be empty", error);
            }
        };

        SearchMessageInputBoundary interactor = new SearchMessageInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureEmptyKeywordTest() {
        SearchMessageInputData inputData = new SearchMessageInputData("", "Paul");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        SearchMessageOutputBoundary failurePresenter = new SearchMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchMessageOutputData messages) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Search keyword cannot be empty", error);
            }
        };

        SearchMessageInputBoundary interactor = new SearchMessageInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureWhitespaceKeywordTest() {
        SearchMessageInputData inputData = new SearchMessageInputData("   ", "Paul");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        SearchMessageOutputBoundary failurePresenter = new SearchMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchMessageOutputData messages) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Search keyword cannot be empty", error);
            }
        };

        SearchMessageInputBoundary interactor = new SearchMessageInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureUserNotFoundTest() {
        SearchMessageInputData inputData = new SearchMessageInputData("hello", "Paul");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        SearchMessageOutputBoundary failurePresenter = new SearchMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchMessageOutputData messages) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User not found", error);
            }
        };

        SearchMessageInputBoundary interactor = new SearchMessageInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureNoMatchingMessagesTest() {
        SearchMessageInputData inputData = new SearchMessageInputData("xyz", "Paul");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Set up test data
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        ChatRoom chatRoom = new ChatRoom("TestRoom", "first message");
        Message message = new Message("hello world", "Paul");
        chatRoom.addMessage(message);
        user.getChatRooms().add(chatRoom);
        userRepository.save(user);

        SearchMessageOutputBoundary failurePresenter = new SearchMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchMessageOutputData messages) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("No messages found containing: xyz", error);
            }
        };

        SearchMessageInputBoundary interactor = new SearchMessageInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void successCaseInsensitiveSearchTest() {
        SearchMessageInputData inputData = new SearchMessageInputData("HELLO", "Paul");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Set up test data
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        ChatRoom chatRoom = new ChatRoom("TestRoom", "first message");
        Message message = new Message("hello world", "Paul");
        chatRoom.addMessage(message);
        user.getChatRooms().add(chatRoom);
        userRepository.save(user);

        SearchMessageOutputBoundary successPresenter = new SearchMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchMessageOutputData messages) {
                ArrayList<Message> foundMessages = messages.getMessages();
                assertEquals(1, foundMessages.size());
                assertEquals("hello world", foundMessages.get(0).getContent());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchMessageInputBoundary interactor = new SearchMessageInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
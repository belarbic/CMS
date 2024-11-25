
package use_case.search_message;

import java.util.ArrayList;

import entity.ChatRoom;
import entity.Message;
import entity.User;

/**
 * The Search Message Interactor.
 */
public class SearchMessageInteractor implements SearchMessageInputBoundary {

    private final SearchMessageUserDataAccessInterface userDataAccessObject;
    private final SearchMessageOutputBoundary searchMessagePresenter;

    /**
     * Creates a new SearchMessageInteractor.
     * @param userDataAccessInterface the data access interface
     * @param searchMessageOutputBoundary the output boundary
     */
    public SearchMessageInteractor(SearchMessageUserDataAccessInterface userDataAccessInterface,
                                   SearchMessageOutputBoundary searchMessageOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.searchMessagePresenter = searchMessageOutputBoundary;
    }

    @Override
    public void execute(SearchMessageInputData searchMessageInputData) {
        final String keyword = searchMessageInputData.getKeyword();
        final String username = searchMessageInputData.getUsername();

        if (isInvalidKeyword(keyword)) {
            prepareFailureView("Search keyword cannot be empty");
        }
        else if (!userDataAccessObject.existsByName(username)) {
            prepareFailureView("User not found");
        }
        else {
            searchAndPresentMessages(username, keyword);
        }
    }

    private boolean isInvalidKeyword(String keyword) {
        return keyword == null || keyword.trim().isEmpty();
    }

    private void searchAndPresentMessages(String username, String keyword) {
        final User user = userDataAccessObject.get(username);
        final ArrayList<Message> matchedMessages = searchMessages(user, keyword);

        if (matchedMessages.isEmpty()) {
            prepareFailureView("No messages found containing: " + keyword);
        }
        else {
            prepareSuccessView(matchedMessages);
        }
    }

    private ArrayList<Message> searchMessages(User user, String keyword) {
        final ArrayList<Message> matchedMessages = new ArrayList<>();

        for (ChatRoom chatRoom : user.getChatRooms()) {
            for (Message message : chatRoom.getMessages()) {
                if (message.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                    matchedMessages.add(message);
                }
            }
        }

        return matchedMessages;
    }

    private void prepareSuccessView(ArrayList<Message> matchedMessages) {
        final SearchMessageOutputData searchMessageOutputData =
                new SearchMessageOutputData(matchedMessages, false);
        searchMessagePresenter.prepareSuccessView(searchMessageOutputData);
    }

    private void prepareFailureView(String errorMessage) {
        searchMessagePresenter.prepareFailView(errorMessage);
    }
}

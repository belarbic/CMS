
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

        if (keyword == null || keyword.trim().isEmpty()) {
            searchMessagePresenter.prepareFailView("Search keyword cannot be empty");
            return;
        }

        if (!userDataAccessObject.existsByName(username)) {
            searchMessagePresenter.prepareFailView("User not found");
            return;
        }

        try {
            final User user = userDataAccessObject.get(username);
            final ArrayList<Message> matchedMessages = new ArrayList<>();

            for (ChatRoom chatRoom : user.getChatRooms()) {
                for (Message message : chatRoom.getMessages()) {
                    if (message.getContent().toLowerCase()
                            .contains(keyword.toLowerCase())) {
                        matchedMessages.add(message);
                    }
                }
            }

            if (matchedMessages.isEmpty()) {
                searchMessagePresenter.prepareFailView(
                        "No messages found containing: " + keyword);
                return;
            }

            final SearchMessageOutputData searchMessageOutputData =
                    new SearchMessageOutputData(matchedMessages, false);
            searchMessagePresenter.prepareSuccessView(searchMessageOutputData);

        }
        catch (Exception e) {
            searchMessagePresenter.prepareFailView(
                    "Error searching messages: " + e.getMessage());
        }
    }
}
package interface_adapter.search_message;

import java.util.ArrayList;

import entity.Message;
import view.MessageView;

/**
 * The SearchMessagesPresenter formats and presents the search results to the user.
 * It transforms the raw message data into a user-friendly format.
 */
public class SearchMessagePresenter {

    private final MessageView messageView;
    private final SearchMessageViewModel viewModel;

    /**
     * Constructs a new SearchMessagePresenter with the specified view and view model.
     *
     * @param messageView The view responsible for displaying search results.
     * @param viewModel   The view model for preparing search result data.
     */
    public SearchMessagePresenter(MessageView messageView, SearchMessageViewModel viewModel) {
        this.messageView = messageView;
        this.viewModel = viewModel;
    }

    /**
     * Formats and presents the search results to the user.
     * If no results are found, a message is displayed.
     * Otherwise, it lists the found messages with relevant details.
     *
     * @param searchResults The list of messages returned from the search.
     * @throws IllegalArgumentException if the input is null.
     */
    public void presentSearchResults(ArrayList<Message> searchResults) {
        if (searchResults == null || searchResults.isEmpty()) {
            throw new IllegalArgumentException("Search results cannot be null or empty");
        }

        viewModel.updateMessages(searchResults);

        if (viewModel.getFormattedMessages().isEmpty()) {
            messageView.showNoResults();
        }
        else {
            messageView.showSearchResults(viewModel.getFormattedMessages());
        }
    }
}


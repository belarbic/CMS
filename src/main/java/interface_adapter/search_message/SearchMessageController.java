package interface_adapter.search_message;

import java.util.ArrayList;

import entity.Message;
import use_case.search_message.SearchMessageInteractor;

/**
 * The SearchMessagesController is responsible for processing user input for the search.
 * It invokes the interactor to search for messages and passes the results to the presenter for display.
 */
public class SearchMessageController {
    private SearchMessageInteractor searchMessageInteractor;
    private SearchMessagePresenter searchMessagePresenter;

    /**
     * Initializes the SearchMessageController with the given interactor and presenter.
     *
     * @param searchMessagesInteractor The interactor that handles searching for messages.
     * @param searchMessagesPresenter  The presenter that displays the search results.
     */
    public SearchMessageController(SearchMessageInteractor searchMessagesInteractor,
                                   SearchMessagePresenter searchMessagesPresenter) {
        this.searchMessageInteractor = searchMessagesInteractor;
        this.searchMessagePresenter = searchMessagesPresenter;
    }

    /**
     * Processes the search input from the user.
     * Retrieves the search results from the interactor and passes them to the presenter.
     *
     * @param keyword The search keyword entered by the user.
     * @throws IllegalArgumentException If the keyword is null or empty.
     */
    public void handleSearchInput(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Search keyword cannot be null or empty.");
        }

        final ArrayList<Message> searchResults = searchMessageInteractor.searchMessage(keyword);
        searchMessagePresenter.presentSearchResults(searchResults);
    }
}


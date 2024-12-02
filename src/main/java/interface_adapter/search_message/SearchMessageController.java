package interface_adapter.search_message;

import use_case.search_message.SearchMessageInputBoundary;
import use_case.search_message.SearchMessageInputData;

/**
 * The controller for the Search Message Use Case.
 */
public class SearchMessageController {

    private final SearchMessageInputBoundary searchMessageUseCaseInteractor;

    /**
     * Creates a new SearchMessageController.
     * @param searchMessageUseCaseInteractor the interactor for search message use case
     */
    public SearchMessageController(SearchMessageInputBoundary searchMessageUseCaseInteractor) {
        this.searchMessageUseCaseInteractor = searchMessageUseCaseInteractor;
    }

    /**
     * Executes the Search Message Use Case.
     * @param keyword the search term to look for
     * @param username the username of the user performing the search
     */
    public void execute(String keyword, String username) {
        final SearchMessageInputData searchMessageInputData = new SearchMessageInputData(
                keyword, username);

        searchMessageUseCaseInteractor.execute(searchMessageInputData);
    }
    public void switchToLoggedInView() {
        searchMessageUseCaseInteractor.switchToLoggedInView();
    }
    public void switchToSearchMessageView() {
        searchMessageUseCaseInteractor.switchToSearchMessageView();
    }
}

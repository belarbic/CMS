
package interface_adapter.search_message;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.search_message.SearchMessageOutputBoundary;
import use_case.search_message.SearchMessageOutputData;

/**
 * The Presenter for the Search Message Use Case.
 */
public class SearchMessagePresenter implements SearchMessageOutputBoundary {

    private final SearchMessageViewModel searchMessageViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    /**
     * Creates a new SearchMessagePresenter.
     * @param viewManagerModel manages the views in the application
     * @param loggedInViewModel the view model for the logged in state
     * @param searchMessageViewModel the view model for search message functionality
     */
    public SearchMessagePresenter(ViewManagerModel viewManagerModel,
                                  LoggedInViewModel loggedInViewModel,
                                  SearchMessageViewModel searchMessageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.searchMessageViewModel = searchMessageViewModel;
    }

    @Override
    public void prepareSuccessView(SearchMessageOutputData response) {
        // On success, update the search results
        final SearchMessageState searchMessageState = searchMessageViewModel.getState();
        searchMessageState.setMessages(response.getMessages());

        // Keep the current logged in user information
        final LoggedInState loggedInState = loggedInViewModel.getState();
        searchMessageState.setUsername(loggedInState.getUsername());

        this.searchMessageViewModel.setState(searchMessageState);
        this.searchMessageViewModel.firePropertyChanged();

        // Switch to the search message view
        this.viewManagerModel.setState(searchMessageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final SearchMessageState searchMessageState = searchMessageViewModel.getState();
        searchMessageState.setSearchError(error);
        this.searchMessageViewModel.setState(searchMessageState);
        this.searchMessageViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoggedInView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSearchMessageView() {
        viewManagerModel.setState(searchMessageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

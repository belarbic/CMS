
package interface_adapter.search_message;

import interface_adapter.ViewModel;

/**
 * The View Model for the Search Message View.
 */
public class SearchMessageViewModel extends ViewModel<SearchMessageState> {

    /**
     * Creates a new SearchMessageViewModel.
     */
    public SearchMessageViewModel() {
        super("search message");
        setState(new SearchMessageState());
    }
}

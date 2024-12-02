
package interface_adapter.edit_message;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.edit_message.EditMessageOutputBoundary;
import use_case.edit_message.EditMessageOutputData;

/**
 * The Presenter for the Edit Message Use Case.
 */
public class EditMessagePresenter implements EditMessageOutputBoundary {

    private final EditMessageViewModel editMessageViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    /**
     * Creates a new EditMessagePresenter.
     * @param viewManagerModel manages the views in the application
     * @param loggedInViewModel the view model for the logged in state
     * @param editMessageViewModel the view model for edit message functionality
     */
    public EditMessagePresenter(ViewManagerModel viewManagerModel,
                                LoggedInViewModel loggedInViewModel,
                                EditMessageViewModel editMessageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.editMessageViewModel = editMessageViewModel;
    }

    @Override
    public void prepareSuccessView(EditMessageOutputData response) {
        // On success, update the message
        final EditMessageState editMessageState = editMessageViewModel.getState();
        editMessageState.setContent(response.getNewContent());

        // Keep the current logged-in user information
        final LoggedInState loggedInState = loggedInViewModel.getState();
        editMessageState.setUsername(loggedInState.getUsername());

        this.editMessageViewModel.setState(editMessageState);
        this.editMessageViewModel.firePropertyChanged();

        // Return to previous view
        this.viewManagerModel.setState(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final EditMessageState editMessageState = editMessageViewModel.getState();
        editMessageState.setEditError(error);
        this.editMessageViewModel.setState(editMessageState);
        this.editMessageViewModel.firePropertyChanged();
    }
    @Override
    public void switchToLoggedInView() {
        viewManagerModel.setState(editMessageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToEditMessageView() {
        viewManagerModel.setState(editMessageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

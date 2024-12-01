package interface_adapter.view_chatrooms;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.view_chatrooms.ViewChatRoomsOutputBoundary;
import use_case.view_chatrooms.ViewChatRoomsOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class ViewChatRoomsPresenter implements ViewChatRoomsOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ViewChatRoomsViewModel viewChatRoomsViewModel;

    public ViewChatRoomsPresenter(ViewManagerModel viewManagerModel,
                                   LoggedInViewModel loggedInViewModel,
                                  ViewChatRoomsViewModel viewChatRoomsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewChatRoomsViewModel = viewChatRoomsViewModel;
    }

    @Override
    public void prepareSuccessView(ViewChatRoomsOutputData response) {
        // We need to switch to the login view, which should have
        // an empty username and password.

        // We also need to set the username in the LoggedInState to
        // the empty string.

        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername("");
        loggedInViewModel.setState(loggedInState);
        loggedInViewModel.firePropertyChanged();

        final ViewChatRoomsState viewChatRoomsState = viewChatRoomsViewModel.getState();
        viewChatRoomsViewModel.setState(viewChatRoomsState);
        viewChatRoomsViewModel.firePropertyChanged();

        // This code tells the View Manager to switch to the LoginView.
        this.viewManagerModel.setState(viewChatRoomsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // No need to add code here. We'll assume that logout can't fail.
        // Thought question: is this a reasonable assumption?
    }

    @Override
    public void switchToLoggedInView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

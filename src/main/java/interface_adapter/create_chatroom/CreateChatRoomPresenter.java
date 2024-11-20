package interface_adapter.create_chatroom;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.create_chatroom.CreateChatRoomOutputBoundary;
import use_case.create_chatroom.CreateChatRoomOutputData;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class CreateChatRoomPresenter implements CreateChatRoomOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final CreateChatRoomViewModel createChatRoomViewModel;

    public CreateChatRoomPresenter(ViewManagerModel viewManagerModel,
                           LoggedInViewModel loggedInViewModel,
                                   CreateChatRoomViewModel createChatRoomViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.createChatRoomViewModel = createChatRoomViewModel;
    }

    @Override
    public void prepareSuccessView(CreateChatRoomOutputData response) {
        // We need to switch to the login view, which should have
        // an empty username and password.

        // We also need to set the username in the LoggedInState to
        // the empty string.

        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername("");
        loggedInViewModel.setState(loggedInState);
        loggedInViewModel.firePropertyChanged();

        final CreateChatRoomState createChatRoomState = createChatRoomViewModel.getState();
        createChatRoomState.setName("x");
        createChatRoomViewModel.setState(createChatRoomState);
        createChatRoomViewModel.firePropertyChanged();

        // This code tells the View Manager to switch to the LoginView.
        this.viewManagerModel.setState(createChatRoomViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // No need to add code here. We'll assume that logout can't fail.
        // Thought question: is this a reasonable assumption?
    }
}

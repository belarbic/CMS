package interface_adapter.create_chatroom;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.create_chatroom.CreateChatRoomOutputBoundary;
import use_case.create_chatroom.CreateChatRoomOutputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Create ChatRoom Use Case.
 */
public class CreateChatRoomPresenter implements CreateChatRoomOutputBoundary {

    private final CreateChatRoomViewModel createChatRoomViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreateChatRoomPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                                   CreateChatRoomViewModel createChatRoomViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.createChatRoomViewModel = createChatRoomViewModel;
    }

    @Override
    public void prepareSuccessView(CreateChatRoomOutputData response) {
        // On success, switch to the logged in view. todo switch to chatroom screen when implemented

        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(response.getName());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setState(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final CreateChatRoomState createChatRoomState = createChatRoomViewModel.getState();
        createChatRoomState.setCreateChatRoomError(error);
        createChatRoomViewModel.firePropertyChanged();
    }
}

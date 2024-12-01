package interface_adapter.chat_room;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.chat_room.ChatRoomOutputBoundary;
import use_case.chat_room.ChatRoomOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class ChatRoomPresenter implements ChatRoomOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ChatRoomViewModel chatRoomViewModel;

    public ChatRoomPresenter(ViewManagerModel viewManagerModel,
                                   LoggedInViewModel loggedInViewModel,
                                   ChatRoomViewModel chatRoomViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.chatRoomViewModel = chatRoomViewModel;
    }

    @Override
    public void prepareSuccessView(ChatRoomOutputData response) {
        // We need to switch to the login view, which should have
        // an empty username and password.

        // We also need to set the username in the LoggedInState to
        // the empty string.

        final LoggedInState loggedInState = loggedInViewModel.getState();
//        loggedInState.setUsername("");
        loggedInViewModel.setState(loggedInState);
        loggedInViewModel.firePropertyChanged();

        final ChatRoomState chatRoomState = chatRoomViewModel.getState();
        chatRoomState.setName("x");
        chatRoomViewModel.setState(chatRoomState);
        chatRoomViewModel.firePropertyChanged();
        if (response.getName() != "" && response.getFirstMessage() != "") {
            this.viewManagerModel.setState(loggedInViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
        else {
            // This code tells the View Manager to switch to the LoginView.
            this.viewManagerModel.setState(chatRoomViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
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


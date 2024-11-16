/*
package interface_adapter.create_chatroom;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.create_chatroom.CreateChatRoomOutputBoundary;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
*/
/*
 * The Presenter for the Create ChatRoom Use Case.
 */
/*
public class CreateChatRoomPresenter implements CreateChatRoomOutputBoundary {

    private final CreateChatRoomViewModel createChatRoomViewModel;
    private final ChatRoomViewModel chatRoomViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          ChatRoomViewModel chatRoomViewModel,
                          CreateChatRoomViewModel createChatRoomViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.chatRoomViewModel = chatRoomViewModel;
        this.createChatRoomViewModel = createChatRoomViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        final LoggedInState loggedInState = chatRoomViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        this.chatRoomViewModel.setState(loggedInState);
        this.chatRoomViewModel.firePropertyChanged();

        this.viewManagerModel.setState(chatRoomViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = createChatRoomViewModel.getState();
        loginState.setLoginError(error);
        createChatRoomViewModel.firePropertyChanged();
    }
}
*/
// waiting on the creation of the chat room view model
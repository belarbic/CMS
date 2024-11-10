/*
package interface_adapter.create_chatroom;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.create_chatroom.CreateChatRoomOutputBoundary;
import use_case.create_chatroom.CreateChatRoomOutputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
*/

/*
 * The Presenter for the Create ChatRoom Use Case.
 */
/*
public class CreateChatRoomPresenter implements CreateChatRoomOutputBoundary {

    private final CreateChatRoomViewModel createChatRoomViewModel;
    // private final ChatRoomViewModel chatRoomViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreateChatRoomPresenter(ViewManagerModel viewManagerModel,
                                   // ChatRoomViewModel chatRoomViewModel,
                                   CreateChatRoomViewModel createChatRoomViewModel) {
        this.viewManagerModel = viewManagerModel;
        // this.chatRoomViewModel = chatRoomViewModel;
        this.createChatRoomViewModel = createChatRoomViewModel;
    }

    @Override
    public void prepareSuccessView(CreateChatRoomOutputData response) {
        // On success, switch to the chat room view.

        // final ChatRoomState chatRoomState = chatRoomViewModel.getState();
        // fix this
        // chatRoomState.setUsername(response.getUsername());
        // this.chatRoomViewModel.setState(chatRoomState);
        // this.chatRoomViewModel.firePropertyChanged();

        // this.viewManagerModel.setState(chatRoomViewModel.getViewName());
        // this.viewManagerModel.firePropertyChanged();
    }

    @Override
     public void prepareFailView(String error) {
        final CreateChatRoomState createChatRoomState = createChatRoomViewModel.getState();
        createChatRoomState.setLoginError(error);
        createChatRoomViewModel.firePropertyChanged();
    }
}
*/
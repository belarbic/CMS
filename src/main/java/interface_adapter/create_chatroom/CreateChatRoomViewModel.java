package interface_adapter.create_chatroom;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class CreateChatRoomViewModel extends ViewModel<CreateChatRoomState> {

    public CreateChatRoomViewModel() {
        super("create chat room");
        setState(new CreateChatRoomState());
    }

}

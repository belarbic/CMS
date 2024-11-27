package interface_adapter.create_chatroom;

import interface_adapter.ViewModel;

/**
 * The View Model for the Create ChatRoom View.
 */
public class CreateChatRoomViewModel extends ViewModel<CreateChatRoomState> {

    public CreateChatRoomViewModel() {
        super("Create Chatroom");
        setState(new CreateChatRoomState());
    }

}

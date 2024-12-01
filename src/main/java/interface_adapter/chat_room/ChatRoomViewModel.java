package interface_adapter.chat_room;

import interface_adapter.ViewModel;

/**
 * The View Model for the ChatRoom View.
 */
public class ChatRoomViewModel extends ViewModel<ChatRoomState> {

    public ChatRoomViewModel() {
        super("Chatroom Screen");
        setState(new ChatRoomState());
    }

}


package interface_adapter.view_chatrooms;

import interface_adapter.ViewModel;

/**
 * The View Model for the Create ChatRoom View.
 */
public class ViewChatRoomsViewModel extends ViewModel<ViewChatRoomsState> {

    public ViewChatRoomsViewModel() {
        super("View Chatrooms");
        setState(new ViewChatRoomsState());
    }

}

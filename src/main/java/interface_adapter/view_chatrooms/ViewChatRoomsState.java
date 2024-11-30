package interface_adapter.view_chatrooms;

/**
 * The state for the Create ChatRoom View Model.
 */
public class ViewChatRoomsState {
    private String viewChatRoomsError;

    public void viewChatRoomsError(String nameError) {
        this.viewChatRoomsError = nameError;
    }

}

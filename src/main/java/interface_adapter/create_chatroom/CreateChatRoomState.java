package interface_adapter.create_chatroom;

/**
 * The state for the Create ChatRoom View Model.
 */
public class CreateChatRoomState {
    private String name = "";
    private String firstMessage = "";
    private String createChatRoomError;

    public String getName() {
        return name;
    }

    public String getFirstMessage() {
        return firstMessage;
    }

    public String getCreateChatRoomError() {
        return createChatRoomError;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstMessage(String firstMessage) {
        this.firstMessage = firstMessage;
    }

    public void setCreateChatRoomError(String nameError) {
        this.createChatRoomError = nameError;
    }

}

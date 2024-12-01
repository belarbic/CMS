package use_case.delete_message;

/**
 * The Input Data for the delete message use case.
 */
public class DeleteMessageInputData {
    private final String messageId;
    private final String chatRoomName;

    public DeleteMessageInputData(String messageId, String chatRoomName) {
        this.messageId = messageId;
        this.chatRoomName = chatRoomName;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }
}

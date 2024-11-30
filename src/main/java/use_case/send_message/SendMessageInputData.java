package use_case.send_message;

/**
 * Input Data for the Send Message Use case.
 */
public class SendMessageInputData {
    private final String content;
    private final String senderUsername;
    private final String chatRoomName;

    public SendMessageInputData(String content, String senderUsername, String chatRoomName) {
        this.content = content;
        this.senderUsername = senderUsername;
        this.chatRoomName = chatRoomName;
    }

    public String getContent() {
        return content;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }
}

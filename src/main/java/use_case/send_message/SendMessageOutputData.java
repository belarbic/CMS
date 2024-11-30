package use_case.send_message;

/**
 * Output Data for the SendMessage Use Case.
 */
public class SendMessageOutputData {
    private final String content;
    private final String senderUsername;
    private final String timestamp;

    public SendMessageOutputData(String content, String senderUsername, String timestamp) {
        this.content = content;
        this.senderUsername = senderUsername;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

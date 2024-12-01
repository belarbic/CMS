package use_case.delete_message;

/**
 * Output Data for the delete message use case.
 */
public class DeleteMessageOutputData {
    private final String messageId;
    private final String content;

    public DeleteMessageOutputData(String messageId, String content) {
        this.messageId = messageId;
        this.content = content;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getContent() {
        return content;
    }
}

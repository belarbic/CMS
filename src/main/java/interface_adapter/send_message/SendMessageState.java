package interface_adapter.send_message;

/**
 * The state for the Send Message View Model.
 */
public class SendMessageState {
    private String content = "";
    private String senderUsername = "";
    private String sendError;
    private String timestamp = "";
    private boolean messageSent;

    public String getContent() {
        return content;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getSendError() {
        return sendError;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isMessageSent() {
        return messageSent;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public void setSendError(String sendError) {
        this.sendError = sendError;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessageSent(boolean messageSent) {
        this.messageSent = messageSent;
    }
}

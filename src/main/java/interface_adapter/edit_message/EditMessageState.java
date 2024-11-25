package interface_adapter.edit_message;

/**
 * The state for the Edit Message View Model.
 */
public class EditMessageState {
    private String messageId = "";
    private String content = "";
    private String username = "";
    private String editError;
    private String timestamp = "";
    private String originalContent = "";

    public String getMessageId() {
        return messageId;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }

    public String getEditError() {
        return editError;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEditError(String error) {
        this.editError = error;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getOriginalContent() {
        return originalContent;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }
}



package use_case.edit_message;

/**
 * Input Data for the Edit Message Use Case.
 */
public class EditMessageInputData {

    private final String messageId;
    private final String newContent;
    private final String username;

    /**
     * Creates a new EditMessageInputData.
     * @param messageId the ID of the message to edit
     * @param newContent the new content for the message
     * @param username the username of the user performing the edit
     */
    public EditMessageInputData(String messageId, String newContent, String username) {
        this.messageId = messageId;
        this.newContent = newContent;
        this.username = username;
    }

    String getMessageId() {
        return messageId;
    }

    String getNewContent() {
        return newContent;
    }

    String getUsername() {
        return username;
    }
}


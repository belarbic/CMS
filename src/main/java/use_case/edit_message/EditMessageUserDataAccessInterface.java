
package use_case.edit_message;

import entity.Message;

/**
 * Data Access Interface for the Edit Message Use Case.
 */
public interface EditMessageUserDataAccessInterface {
    /**
     * Checks if a user exists by username.
     * @param username the username to check
     * @return true if the user exists, false otherwise
     */
    boolean existsByName(String username);

    /**
     * Gets a message by its ID.
     * @param messageId the ID of the message to retrieve
     * @return the message with the given ID
     */
    Message getMessage(String messageId);

    /**
     * Updates the content of a message.
     * @param messageId the ID of the message to update
     * @param newContent the new content for the message
     */
    void updateMessage(String messageId, String newContent);
}

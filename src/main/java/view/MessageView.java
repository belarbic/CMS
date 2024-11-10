package view;

/**
 * Interface for displaying message-related information to the user.
 * The view will implement these methods to show success or failure messages
 * to the user, depending on the result of the edit message use case.
 */
public interface MessageView {

    /**
     * Displays the updated message content after a successful edit.
     *
     * @param updatedMessage The message with updated content.
     */
    void showUpdatedMessage(String updatedMessage);

    /**
     * Displays an error message if the edit operation fails.
     *
     * @param errorMessage The error message to display.
     */
    void showError(String errorMessage);
}

package view;

import java.util.List;

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

    /**
     * Displays the search results to the user.
     *
     * @param formattedMessages A list of formatted messages from the search results.
     */
    void showSearchResults(List<String> formattedMessages);

    /**
     * Displays a message indicating that no search results were found.
     */
    void showNoResults();

    /**
     * Displays the status of the edit operation (success or failure).
     *
     * @param isSuccess Indicates whether the edit operation was successful.
     * @param message The message to display, describing the status.
     */
    void showEditStatus(boolean isSuccess, String message);
}

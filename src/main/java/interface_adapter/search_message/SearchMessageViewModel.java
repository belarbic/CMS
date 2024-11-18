package interface_adapter.search_message;

import java.util.ArrayList;
import java.util.List;

import entity.Message;

/**
 * ViewModel for storing and presenting search results and errors.
 */
public class SearchMessageViewModel {

    private List<String> formattedMessages = new ArrayList<>();
    private String error;

    /**
     * Updates the messages with formatted search results.
     *
     * @param messages The list of messages to format and store.
     */
    public void updateMessages(List<Message> messages) {
        formattedMessages.clear();
        for (Message message : messages) {
            formattedMessages.add(formatMessage(message));
        }
        error = null;
    }

    /**
     * Sets an error message for display.
     *
     * @param error The error message to store.
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Retrieves the current error message.
     *
     * @return The error message, or null if no error is set.
     */
    public String getError() {
        return error;
    }

    /**
     * Formats a message for display.
     *
     * @param message The message to format.
     * @return A formatted string representation of the message.
     */
    private String formatMessage(Message message) {
        return "Group: " + message.getChatRoom()
                + " | Sender: " + message.getSender()
                + " | Timestamp: " + message.getTimestamp()
                + " | Content: " + message.getContent();
    }

    /**
     * Retrieves the formatted search results.
     *
     * @return A list of formatted messages.
     */
    public List<String> getFormattedMessages() {
        return formattedMessages;
    }
}


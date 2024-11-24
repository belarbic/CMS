package interface_adapter.send_message;

import java.util.ArrayList;
import java.util.List;

import entity.Message;

/**
 * ViewModel for managing the state of messages in the view.
 */
public class SendMessageViewModel {
    private final List<String> messages;

    public SendMessageViewModel() {
        this.messages = new ArrayList<>();
    }

    public SendMessageViewModel(List<String> messages) {
        this.messages = messages;
    }

    /**
     * Adds a new formatted message to the ViewModel.
     *
     * @param message The message entity to be added.
     */
    public void addMessage(Message message) {
        messages.add(formatMessage(message));
    }

    /**
     * Replaces the entire list of messages with a new list.
     *
     * @param newMessages The new list of messages to display.
     */
    public void updateMessages(List<Message> newMessages) {
        messages.clear();
        for (Message message : newMessages) {
            messages.add(formatMessage(message));
        }
    }

    /**
     * Returns the list of formatted messages for display.
     *
     * @return A list of formatted message strings.
     */
    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    /**
     * Formats a message for display.
     *
     * @param message The message to format.
     * @return A formatted string for the message.
     */
    private String formatMessage(Message message) {
        return message.getSender() + ": " + message.getContent();
    }
}

package interface_adapter.edit_message;

import java.util.NoSuchElementException;

import entity.Message;
import use_case.edit_message.EditMessageInteractor;
import view.MessageView;

/**
 * Presenter responsible for managing the interaction between the EditMessage use case
 * and the user interface (view). The presenter receives results from the use case
 * and passes them to the view to display to the user.
 */
public class MessagePresenter {

    private final EditMessageInteractor editMessageInteractor;
    private final MessageView messageView;

    /**
     * Constructs a new MessagePresenter with the specified use case and view.
     *
     * @param editMessageInteractor The use case for editing messages.
     * @param messageView The view for displaying message-related information.
     */
    public MessagePresenter(EditMessageInteractor editMessageInteractor, MessageView messageView) {
        this.editMessageInteractor = editMessageInteractor;
        this.messageView = messageView;
    }

    /**
     * Handles the process of editing a message.
     * This method receives input (message ID and new content) from the user,
     * invokes the use case, and updates the view based on the result.
     *
     * @param id The ID of the message to edit.
     * @param content The new content to replace the old message content.
     * @throws IllegalArgumentException If the message ID or new content is invalid (null or empty).
     * @throws NoSuchElementException If the message with the specified ID does not exist.
     * @throws Exception If any unexpected errors occur during the process.
     */
    public void editMessage(String id, String content) throws Exception {
        final Message updatedMessage = editMessageInteractor.editMessage(id, content);
        messageView.showUpdatedMessage(updatedMessage.getContent());
    }
}

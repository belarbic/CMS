package interface_adapter.edit_message;

import entity.Message;
import use_case.edit_message.EditMessageInteractor;

/**
 * Controller for managing messages in the system.
 * This controller handles user interactions related to message editing, such as
 * invoking the use case to edit a message and handling any exceptions that might
 * occur during the process.
 * The {@link EditMessageInteractor} is used by this controller to edit messages.
 */
public class MessageController {
    private final EditMessageInteractor editMessageInteractor;

    /**
     * Constructs a new {@link MessageController} with the specified use case.
     *
     * @param editMessageInteractor The use case to edit a message. It contains the
     *                           business logic to update message content.
     */
    public MessageController(EditMessageInteractor editMessageInteractor) {
        this.editMessageInteractor = editMessageInteractor;
    }

    /**
     * Edits the content of an existing message.
     * This method is called when the user selects a message to edit.
     * It invokes the use case to edit the message, passing the new content.
     *
     * @param id  The ID of the message to edit. The ID must be valid.
     * @param content The new content to update the message with. The content
     *                   must not be empty or null.
     *
     * @throws IllegalArgumentException If the message ID or new content is invalid (null or empty).
     */
    public void editMessage(String id, String content) throws IllegalArgumentException {
        final Message updatedMessage = editMessageInteractor.editMessage(id, content);
        System.out.println("Message updated successfulyy: " + updatedMessage.getContent());
    }
}

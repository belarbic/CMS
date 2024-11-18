package interface_adapter.edit_message;

import entity.Message;
import use_case.edit_message.EditMessageInteractor;

/**
 * Controller for managing message editing in the system.
 * Handles user interactions related to message editing, such as
 * invoking the use case to edit a message and handling any exceptions that might
 * occur during the process.
 * Uses {@link EditMessageInteractor} to execute the message editing logic.
 */
public class EditMessageController {
    private final EditMessageInteractor editMessageInteractor;
    private final EditMessageViewModel editMessageViewModel;

    /**
     * Initializes the {@link EditMessageController} with the specified use case.
     *
     * @param editMessageInteractor The interactor containing the business logic for editing messages.
     * @param editMessageViewModel The viewModel for editing messages.
     */
    public EditMessageController(EditMessageInteractor editMessageInteractor,
                                 EditMessageViewModel editMessageViewModel) {
        this.editMessageInteractor = editMessageInteractor;
        this.editMessageViewModel = editMessageViewModel;
    }

    /**
     * Edits the content of an existing message and updates viewModel accordingly.
     * This method is called when the user selects a message to edit.
     * Validates inputs and invokes the use case to update the message content.
     *
     * @param id  The ID of the message to edit. The ID must be valid.
     * @param content The new content to update the message with. The content
     *                   must not be empty or null.
     *
     * @throws IllegalArgumentException If the message ID or new content is invalid (null or empty).
     */
    public void editMessage(String id, String content) throws IllegalArgumentException {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Message ID cannot be null or empty.");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be null or empty.");
        }
        final Message updatedMessage = editMessageInteractor.editMessage(id, content);
        System.out.println("Message updated successfully: " + updatedMessage.getContent());
    }
}


package interface_adapter.edit_message;

import use_case.edit_message.EditMessageInputBoundary;
import use_case.edit_message.EditMessageInputData;

/**
 * The controller for the Edit Message Use Case.
 */
public class EditMessageController {
    private final EditMessageInputBoundary editMessageUseCaseInteractor;

    /**
     * Creates a new EditMessageController.
     * @param editMessageUseCaseInteractor the interactor for edit message use case
     */
    public EditMessageController(EditMessageInputBoundary editMessageUseCaseInteractor) {
        this.editMessageUseCaseInteractor = editMessageUseCaseInteractor;
    }

    /**
     * Executes the Edit Message Use Case.
     * @param messageId the ID of message to edit
     * @param newContent the new content for the message
     * @param username the username of the user performing the edit
     */
    public void execute(String messageId, String newContent, String username) {
        final EditMessageInputData editMessageInputData = new EditMessageInputData(messageId, newContent, username);

        editMessageUseCaseInteractor.execute(editMessageInputData);
    }
    public void switchToLoggedInView() {
        editMessageUseCaseInteractor.switchToLoggedInView();
    }
    public void switchToEditMessageView() {
        editMessageUseCaseInteractor.switchToEditMessageView();
    }
}


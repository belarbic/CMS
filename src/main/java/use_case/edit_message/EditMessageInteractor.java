package use_case.edit_message;

import entity.Message;

/**
 * The Edit Message Interactor.
 */
public class EditMessageInteractor implements EditMessageInputBoundary {

    private final EditMessageUserDataAccessInterface userDataAccessObject;
    private final EditMessageOutputBoundary editMessagePresenter;

    /**
     * Creates a new EditMessageInteractor.
     * @param userDataAccessInterface the data access interface
     * @param editMessageOutputBoundary the output boundary
     */
    public EditMessageInteractor(EditMessageUserDataAccessInterface userDataAccessInterface,
                                 EditMessageOutputBoundary editMessageOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.editMessagePresenter = editMessageOutputBoundary;
    }

    @Override
    public void execute(EditMessageInputData editMessageInputData) {
        final String messageId = editMessageInputData.getMessageId();
        final String newContent = editMessageInputData.getNewContent();
        final String username = editMessageInputData.getUsername();

        // Validation checks
        if (isInvalidContent(newContent)) {
            prepareFailureView("Message content cannot be empty");
            return; // Stop further processing if validation fails
        }

        if (!userDataAccessObject.existsByName(username)) {
            prepareFailureView("User not found");
            return; // Stop further processing if user is not found
        }

        // Process the message edit if all validations pass
        processMessageEdit(messageId, newContent, username);
    }

    @Override
    public void switchToEditMessageView() {
        editMessagePresenter.switchToEditMessageView();
    }
    public void switchToLoggedInView() {
        editMessagePresenter.switchToLoggedInView();
    }

    private boolean isInvalidContent(String content) {
        return content == null || content.trim().isEmpty();
    }

    private void processMessageEdit(String messageId, String newContent, String username) {
        final Message message = userDataAccessObject.getMessage(messageId);

        // Check if message exists and if the user is authorized to edit
        if (message == null) {
            prepareFailureView("Message not found");
        } else if (!message.getSender().equals(username)) {
            prepareFailureView("You can only edit your own messages");
        } else {
            // Update message if validation passes
            userDataAccessObject.updateMessage(messageId, newContent);
            prepareSuccessView(newContent);
        }
    }

    private void prepareSuccessView(String newContent) {
        // Prepare success response
        final EditMessageOutputData editMessageOutputData =
                new EditMessageOutputData(newContent, false);
        editMessagePresenter.prepareSuccessView(editMessageOutputData);
    }

    private void prepareFailureView(String error) {
        // Prepare failure response with error message
        editMessagePresenter.prepareFailView(error);
    }
}


package use_case.edit_message;

import entity.Message;
import entity.User;

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

        if (isInvalidContent(newContent)) {
            prepareFailureView("Message content cannot be empty");
        }
        else if (!userDataAccessObject.existsByName(username)) {
            prepareFailureView("User not found");
        }
        else {
            processMessageEdit(messageId, newContent, username);
        }
    }

    private boolean isInvalidContent(String content) {
        return content == null || content.trim().isEmpty();
    }

    private void processMessageEdit(String messageId, String newContent, String username) {
        final Message message = userDataAccessObject.getMessage(messageId);

        if (message == null) {
            prepareFailureView("Message not found");
        }
        else if (!message.getSender().equals(username)) {
            prepareFailureView("You can only edit your own messages");
        }
        else {
            userDataAccessObject.updateMessage(messageId, newContent);
            prepareSuccessView(newContent);
        }
    }

    private void prepareSuccessView(String newContent) {
        final EditMessageOutputData editMessageOutputData =
                new EditMessageOutputData(newContent, false);
        editMessagePresenter.prepareSuccessView(editMessageOutputData);
    }

    private void prepareFailureView(String error) {
        editMessagePresenter.prepareFailView(error);
    }
}
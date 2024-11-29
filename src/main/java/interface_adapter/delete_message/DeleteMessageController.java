package interface_adapter.delete_message;

import use_case.delete_message.DeleteMessageInputBoundary;
import use_case.delete_message.DeleteMessageInputData;

/**
 * Controller for the delete message use case.
 */
public class DeleteMessageController {
    private final DeleteMessageInputBoundary deleteMessageUseCaseInteractor;

    /**
     * Creates a new DeleteMessageController.
     * @param deleteMessageUseCaseInteractor the interactor for delete message use case
     */
    public DeleteMessageController(DeleteMessageInputBoundary deleteMessageUseCaseInteractor) {
        this.deleteMessageUseCaseInteractor = deleteMessageUseCaseInteractor;
    }

    /**
     * Executes the delete message use case.
     * @param messageId the message id
     * @param chatRoomName the name of the chatroom
     */
    public void execute(String messageId, String chatRoomName) {
        DeleteMessageInputData inputData = new DeleteMessageInputData(messageId, chatRoomName);

        deleteMessageUseCaseInteractor.execute(inputData);
    }
}

package use_case.delete_message;

import entity.ChatRoom;
import entity.Message;

/**
 * Interactor for deleting messages.
 */
public class DeleteMessageInteractor implements DeleteMessageInputBoundary{
    private final DeleteMessageUserDataAccessInterface userDataAccessObject;
    private final DeleteMessageOutputBoundary outputBoundary;

    public DeleteMessageInteractor(DeleteMessageUserDataAccessInterface userDataAccessObject,
                                   DeleteMessageOutputBoundary outputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(DeleteMessageInputData inputData) {
        final String messageId = inputData.getMessageId();
        final String chatRoomName = inputData.getChatRoomName();

        if (!userDataAccessObject.chatRoomExists(chatRoomName)) {
            outputBoundary.presentFailView("Chat room not found");
            return;
        }

        ChatRoom chatRoom = userDataAccessObject.getChatRoomByName(chatRoomName);
        if (chatRoom == null) {
            outputBoundary.presentFailView("Chat room not found");
            return;
        }

        Message message = userDataAccessObject.getMessageById(messageId, chatRoomName);
        if (message == null) {
            outputBoundary.presentFailView("Message not found");
            return;
        }

        // Mark the message as deleted
        message.delete();

        // Update the message in the data store
        userDataAccessObject.updateMessage(message);

        // Prepare success output
        DeleteMessageOutputData outputData = new DeleteMessageOutputData(
                messageId,
                message.getContent()
        );
        outputBoundary.presentSuccessView(outputData);
    }
}

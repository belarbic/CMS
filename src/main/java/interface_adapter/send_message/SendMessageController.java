package interface_adapter.send_message;

import entity.ChatRoom;
import entity.User;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;

/**
 * The controller for the send message use case.
 */
public class SendMessageController {
    private final SendMessageInputBoundary sendMessageUseCaseInteractor;

    /**
     * Creates a new SendMessageController.
     * @param sendMessageUseCaseInteractor the interactor for send message use case
     */
    public SendMessageController(SendMessageInputBoundary sendMessageUseCaseInteractor) {
        this.sendMessageUseCaseInteractor = sendMessageUseCaseInteractor;
    }

    /**
     * Executes the send message use case.
     *
     * @param content  The message content.
     * @param sender   The user sending the message.
     * @param chatRoom The chat room where the message is sent.
     */
    public void execute(String content, User sender, ChatRoom chatRoom) {
        final SendMessageInputData inputData = new SendMessageInputData(content, sender.getName(), chatRoom.getName());

        // Pass input data to the input boundary
        sendMessageUseCaseInteractor.execute(inputData);
    }
}

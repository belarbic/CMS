package interface_adapter.send_message;

import entity.ChatRoom;
import entity.User;
import use_case.send_message.SendMessageInteractor;

/**
 * The controller for the send message use case.
 */
public class SendMessageController {
    private final SendMessageInteractor interactor;

    public SendMessageController(SendMessageInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Handles the send message request.
     *
     * @param content  The message content.
     * @param sender   The user sending the message.
     * @param chatRoom The chat room where the message is sent.
     */
    public void handleSendMessage(String content, User sender, ChatRoom chatRoom) {
        interactor.sendMessage(content, sender, chatRoom);
    }
}

package use_case.send_message;

import entity.ChatRoom;
import entity.Message;
import entity.User;

/**
 * Interactor for the send message use case.
 */
public class SendMessageInteractor {
    private final ChatMessageRepositoryInterface messageRepository;

    public SendMessageInteractor(ChatMessageRepositoryInterface messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Sends a message and stores it in the repository and chat room.
     *
     * @param content  The message content.
     * @param sender   The user sending the message.
     * @param chatRoom The chat room where the message is sent.
     * @throws IllegalArgumentException If any parameter is null or content is empty.
     */
    public void sendMessage(String content, User sender, ChatRoom chatRoom) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be null or empty.");
        }
        if (sender == null) {
            throw new IllegalArgumentException("Sender cannot be null.");
        }
        if (chatRoom == null) {
            throw new IllegalArgumentException("ChatRoom cannot be null.");
        }

        // Extract the sender's name from the User object
        final String senderName = sender.getName();

        final Message message = new Message(content, senderName);

        // Add the message to the chat room
        chatRoom.getMessages().add(message);
    }

}

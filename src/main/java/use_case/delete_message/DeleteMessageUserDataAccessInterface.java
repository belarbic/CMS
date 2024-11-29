package use_case.delete_message;

import entity.ChatRoom;
import entity.Message;

/**
 * Data Access Interface for the Delete Message Use Case.
 */
public interface DeleteMessageUserDataAccessInterface {
    /**
     * Checks if a chat room exists by name.
     *
     * @param chatRoomName the name of the chat room to check
     * @return true if the chat room exists, false otherwise
     */
    boolean chatRoomExists(String chatRoomName);

    /**
     * Retrieves a chat room by its name.
     *
     * @param chatRoomName the name of the chat room to retrieve
     * @return the ChatRoom object, or null if not found
     */
    ChatRoom getChatRoomByName(String chatRoomName);

    /**
     * Retrieves a message by its ID within a specific chat room.
     *
     * @param messageId the ID of the message to retrieve
     * @param chatRoomName the name of the chat room containing the message
     * @return the Message object, or null if not found
     */
    Message getMessageById(String messageId, String chatRoomName);

    /**
     * Updates a message in the data store after marking it as deleted.
     *
     * @param message the Message object to update
     */
    void updateMessage(Message message);
}

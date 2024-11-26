package use_case.send_message;

import entity.ChatRoom;
import entity.User;

/**
 * Data Access Interface for the Send Message Use Case.
 */
public interface SendMessageUserDataAccessInterface {
    /**
     * Checks if a chat room exists by name.
     *
     * @param chatRoomName the name of the chat room to check
     * @return true if the chat room exists, false otherwise
     */
    boolean chatRoomExists(String chatRoomName);

    /**
     * Gets a chat room by its name.
     *
     * @param chatRoomName the name of the chat room to retrieve
     * @return the chat room with the given name, or null if not found
     */
    ChatRoom getChatRoomByName(String chatRoomName);

    /**
     * Checks if a user exists in a specific chat room by username.
     *
     * @param username the username of the user
     * @param chatRoomName the name of the chat room
     * @return true if the user exists in the chat room, false otherwise
     */
    boolean userExistsInChatRoom(String username, String chatRoomName);

    /**
     * Gets a user by their username within a specific chat room.
     *
     * @param username the username of the user to retrieve
     * @param chatRoomName the name of the chat room
     * @return the user with the given username in the specified chat room, or null if not found
     */
    User getUserInChatRoom(String username, String chatRoomName);
}

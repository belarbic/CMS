package use_case.create_chatroom;

import entity.ChatRoom;

/**
 * DAO for the Create ChatRoom Use Case.
 */
public interface CreateChatRoomUserDataAccessInterface {

    /**
     * Returns the name of the chatRoom.
     * @return the name of the chatRoom.; null indicates that there isn't a name.
     */
    String getName();

    /**
     * Sets the name of the chatRoom.
     * @param name the name of the ChatRoom
     * @return the name of the chatRoom.; null indicates that there isn't a name.
     */
    void setName(String name);

    /**
     * Saves the ChatRoom.
     * @param  chatRoom the chatRoom to save
     */
    void save(ChatRoom chatRoom);

    /**
     * Returns the ChatRoom.
     */
    ChatRoom getChatRoom();
}

package use_case.create_chatroom;

import entity.ChatRoom;

/**
 * DAO for the Create ChatRoom Use Case.
 */
public interface CreateChatRoomUserDataAccessInterface {

    /**
     * Checks if the given chatRoom exists.
     * @param name the name to look for
     * @return true if a chatRoom with this name already exists.
     */
    boolean existsByName(String name);

    /**
     * Returns the name of the chatRoom.
     * @return the name of the chatRoom.; null indicates that there isn't a name.
     */
    String getName();

    /**
     * Saves the ChatRoom.
     * @param  chatRoom the chatRoom to save
     */
    void save(ChatRoom chatRoom);

}

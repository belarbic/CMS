package use_case.view_chatrooms;

import entity.ChatRoom;

/**
 * DAO for the Create ChatRoom Use Case.
 */
public interface ViewChatRoomsUserDataAccessInterface {

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

}

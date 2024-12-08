package use_case.chat_room;

import entity.ChatRoom;

public interface ChatRoomUserDataAccessInterface {

    /**
     * Returns the name of the chatRoom.
     * @return the name of the chatRoom.; null indicates that there isn't a name.
     */
    String getName();

    /**
     * Returns the name of the chatRoom.
     * @return the name of the chatRoom.; null indicates that there isn't a name.
     */
    String getFirstMessage();

    /**
     * Sets the name of the chatRoom.
     * @param firstMessage the name of the ChatRoom
     * @return the name of the chatRoom.; null indicates that there isn't a name.
     */
    void setFirstMessage(String firstMessage);

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

    String getCurrentUsername();
}

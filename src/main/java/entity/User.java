package entity;

import java.util.ArrayList;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns the onlineStatus of the user.
     * @return the onlineStatus of the user.
     */
    boolean getOnlineStatus();

    /**
     * Returns the chatRooms of the user.
     * @return the chatRooms of the user.
     */
    ArrayList<ChatRoom> getChatRooms();

}

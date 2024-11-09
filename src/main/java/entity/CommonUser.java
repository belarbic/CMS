package entity;

import java.util.ArrayList;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private boolean onlineStatus;
    private ArrayList<ChatRoom> chatRooms;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.onlineStatus = true;
        this.chatRooms = new ArrayList<ChatRoom>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean getOnlineStatus() {
        return onlineStatus;
    }

    @Override
    public ArrayList<ChatRoom> getChatRooms() {
        return chatRooms;
    }
}

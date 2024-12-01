package entity;

import java.util.ArrayList;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private ArrayList<ChatRoom> chatRooms;

    public CommonUser(String name, String password, ArrayList<ChatRoom> chatRooms) {
        this.name = name;
        this.password = password;
        this.chatRooms = chatRooms;
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
    public ArrayList<ChatRoom> getChatRooms() {
        return chatRooms;
    }
}

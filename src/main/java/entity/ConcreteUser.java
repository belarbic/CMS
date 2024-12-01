package entity;

import java.util.ArrayList;

public class ConcreteUser implements User {
    private String name;
    private String password;
    private ArrayList<ChatRoom> chatRooms;

    public ConcreteUser() {
        this.chatRooms = new ArrayList<>();
    }

    public ConcreteUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.chatRooms = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public ArrayList<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public void setChatRooms(ArrayList<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }
}


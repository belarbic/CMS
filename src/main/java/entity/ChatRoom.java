package entity;

import java.util.ArrayList;

/**
 * The representation of a chatRoom in our program.
 */
public class ChatRoom {

    private String name;
    private ArrayList<User> participants;
    private ArrayList<Message> messages;

    public ChatRoom(String name, ArrayList<User> participants, Message message) {
        this.name = name;
        this.participants = participants;
        this.messages = new ArrayList<Message>();
        this.messages.add(message);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    /**
     * A method that adds a participant to the chatRoom.
     * @param user the user we want to add.
     */
    public void addParticipant(User user) {
        participants.add(user);
    }

    /**
     * A method that removes a participant from the chatRoom.
     * @param user the user we want to remove.
     */
    public void removeParticipant(User user) {
        participants.remove(user);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

}

package entity;

import java.util.ArrayList;

/**
 * The representation of a chatRoom in our program.
 */
public class ChatRoom {

    private String name;
    private String firstMessage;
    private ArrayList<User> participants;
    private ArrayList<Message> messages;

    public ChatRoom(String name, String firstMessage) {
        this.name = name;
        // TODO participants added with calls to API
        this.participants = new ArrayList<User>();
        this.messages = new ArrayList<Message>();
        this.messages.add(new Message(firstMessage, null));
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
     * A method to add participants.
     * @param participant the participant we're adding.
     */
    public void addParticipants(User participant) {
        participants.add(participant);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    /**
     * A method to add a message.
     * @param message the message we're adding.
     */
    public void addMessage(Message message) {
        messages.add(message);
    }

}

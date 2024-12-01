package use_case.create_chatroom;

import java.util.ArrayList;

import entity.Message;
import entity.User;

/**
 * The Input Data for the Create ChatRoom Use Case.
 */
public class CreateChatRoomInputData {

    private String name;
    private ArrayList<User> participants;
    private ArrayList<Message> messages;
    private final String firstMessage;

    public CreateChatRoomInputData(String name, ArrayList<User> participants, Message message, String firstMessage) {
        this.name = name;
        this.participants = participants;
        this.firstMessage = firstMessage;
        this.messages = new ArrayList<Message>();
        this.messages.add(message);
    }

    public CreateChatRoomInputData(String name, String firstMessage) {
        this.name = name;
        this.firstMessage = firstMessage;
    }

    String getName() {
        return name;
    }

    String getFirstMessage() {
        return firstMessage;
    }

    ArrayList<User> getParticipants() {
        return participants;
    }

    ArrayList<Message> getMessages() {
        return messages;
    }
}

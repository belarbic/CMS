package use_case.create_chatroom;

import entity.Message;
import entity.User;

import java.util.ArrayList;

/**
 * The input data for the Create ChatRoom Use Case.
 */
public class CreateChatRoomInputData {

    private String name;
    private ArrayList<User> participants;
    private ArrayList<Message> messages;

    public CreateChatRoomInputData(String name, ArrayList<User> participants, Message message) {
        this.name = name;
        this.participants = participants;
        this.messages = new ArrayList<Message>();
        this.messages.add(message);
    }

    String getName() {
        return name;
    }

    ArrayList<User> getParticipants() {
        return participants;
    }

    ArrayList<Message> getMessages() {
        return messages;
    }
}

package use_case.chat_room;

import entity.Message;
import entity.User;

import java.util.ArrayList;

public class ChatRoomInputData {
    private String name;
    private ArrayList<User> participants;
    private ArrayList<Message> messages;
    private final String firstMessage;

    public ChatRoomInputData(String name, ArrayList<User> participants, Message message, String firstMessage) {
        this.name = name;
        this.participants = participants;
        this.firstMessage = firstMessage;
        this.messages = new ArrayList<Message>();
        this.messages.add(message);
    }

    public ChatRoomInputData(String name, String firstMessage) {
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

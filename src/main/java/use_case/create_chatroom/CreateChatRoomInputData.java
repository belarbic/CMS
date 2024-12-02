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

}

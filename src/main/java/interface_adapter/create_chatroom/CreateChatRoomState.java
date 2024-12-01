package interface_adapter.create_chatroom;

import java.util.ArrayList;

import entity.Message;
import entity.User;

/**
 * The state for the Create ChatRoom View Model.
 */
public class CreateChatRoomState {
    private String loginError;
    // need to make createChatRoomError
    private String name = "";
    private String firstMessage = "";
    private String createChatRoomError;
    private ArrayList<User> participants = new ArrayList<User>();
    private ArrayList<Message> messages = new ArrayList<Message>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<User> participants) {
        this.participants = participants;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    /**
     * Sets a Message.
     * @param message The message object.
     */
    public void setMessages(Message message) {
        this.messages.add(message);
    }

    // need to do this for createChatRoom.
    public String getLoginError() {
        return loginError;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public String getFirstMessage() {
        return firstMessage;

    }

    public String getCreateChatRoomError() {
        return createChatRoomError;
    }

    public void setFirstMessage(String firstMessage) {
        this.firstMessage = firstMessage;
    }

    public void setCreateChatRoomError(String nameError) {
        this.createChatRoomError = nameError;
    }
}

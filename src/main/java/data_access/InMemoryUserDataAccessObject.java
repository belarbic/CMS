package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.ChatRoom;
import entity.Message;
import entity.User;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.create_chatroom.CreateChatRoomUserDataAccessInterface;
import use_case.edit_message.EditMessageUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.search_message.SearchMessageUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.view_chatrooms.ViewChatRoomsUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        CreateChatRoomUserDataAccessInterface,
        ViewChatRoomsUserDataAccessInterface,
        SearchMessageUserDataAccessInterface,
        EditMessageUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, ChatRoom> chatRooms = new HashMap<>();

    private String currentUsername;
    private ChatRoom chatRoom;
    private String name;
    private String firstMessage;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getFirstMessage() {
        return this.firstMessage;
    }

    @Override
    public void setFirstMessage(String firstMessage) {
        this.firstMessage = firstMessage;
    }

    @Override
    public void save(ChatRoom chatRoom) {
        chatRooms.put(chatRoom.getName(), chatRoom);
    }

    @Override
    public ChatRoom getChatRoom() {
        return this.chatRoom;
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public Message getMessage(String messageId) {
        // Implementation to get message by ID
        // You might need to search through chat rooms to find the message
        Message foundMessage = null;
        for (ChatRoom room : chatRooms.values()) {
            for (Message message : room.getMessages()) {
                if (message.getId().equals(messageId)) {
                    foundMessage = message;
                }
            }
        }
        return foundMessage;
    }

    @Override
    public void updateMessage(String messageId, String newContent) {
        // Implementation to update message content
        final Message message = getMessage(messageId);
        if (message != null) {
            message.setContent(newContent);
        }
    }
}

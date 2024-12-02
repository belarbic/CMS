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
import use_case.send_message.SendMessageUserDataAccessInterface;
import use_case.delete_message.DeleteMessageUserDataAccessInterface;

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
        EditMessageUserDataAccessInterface,
        SendMessageUserDataAccessInterface,
        DeleteMessageUserDataAccessInterface{

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, ChatRoom> chatRooms = new HashMap<>();
    private final Map<String, String> usersUids = new HashMap<>();

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

    /**
     * Saves the user.
     *
     * @param username the user to save
     * @param uid      the user to save
     */
    @Override
    public void saveUid(String username, String uid) {
        usersUids.put(username, uid);
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

    /**
     * Sets the username indicating who is the current user of the application.
     *
     * @param username the new current username.
     * @return UID.
     */
    @Override
    public String getUidByUsername(String username) {
        return usersUids.get(username);
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

    @Override
    public boolean chatRoomExists(String chatRoomName) {
        return chatRooms.containsKey(chatRoomName);
    }

    @Override
    public ChatRoom getChatRoomByName(String chatRoomName) {
        return chatRooms.get(chatRoomName);
    }

    @Override
    public Message getMessageById(String messageId, String chatRoomName) {
        // Retrieve the chat room
        ChatRoom chatRoom = chatRooms.get(chatRoomName);
        if (chatRoom == null) {
            // Chat room doesn't exist
            return null;
        }
        // Search for the message in the chat room's message list
        return chatRoom.getMessages().stream()
                .filter(message -> message.getId().equals(messageId))
                .findFirst()
                // Return null if message not found
                .orElse(null);
    }

    @Override
    public void updateMessage(Message message) {
        // The messages are already marked as deleted and assumed to be updated in the memory
    }

    @Override
    public boolean userExistsInChatRoom(String username, String chatRoomName) {
        ChatRoom chatRoom = chatRooms.get(chatRoomName);
        if (chatRoom == null) {
            return false;
        }
        return chatRoom.getParticipants().stream()
                .anyMatch(user -> user.getName().equals(username));
    }

    @Override
    public User getUserInChatRoom(String username, String chatRoomName) {
        // Fetch the chat room
        ChatRoom chatRoom = chatRooms.get(chatRoomName);
        if (chatRoom == null) {
            // Chat room doesn't exist
            return null;
        }
        // Find the user in the participants list
        return chatRoom.getParticipants().stream()
                .filter(user -> user.getName().equals(username))
                .findFirst()
                .orElse(null);
    }
}

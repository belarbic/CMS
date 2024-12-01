package data_access;

import com.google.firebase.database.*;
import entity.ChatRoom;
import entity.ConcreteUser;
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

import java.util.concurrent.CompletableFuture;

public class FirebaseUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        CreateChatRoomUserDataAccessInterface,
        ViewChatRoomsUserDataAccessInterface,
        SearchMessageUserDataAccessInterface,
        EditMessageUserDataAccessInterface {

    private final DatabaseReference usersRef;
    private final DatabaseReference chatRoomsRef;
    private String currentUsername;
    private String name;
    private String firstMessage;


    public FirebaseUserDataAccessObject()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        this.usersRef = database.getReference("users");
        this.chatRoomsRef = database.getReference("chats");
    }
    /**
     * Updates the system to record this user's password.
     *
     * @param user the user whose password is to be updated
     */
    @Override
    public void changePassword(User user) {
        usersRef.child(user.getName()).child("password").setValueAsync(user.getPassword());

    }
    /**
     * Returns the name of the chatRoom.
     *
     * @return the name of the chatRoom.; null indicates that there isn't a name.
     */
    @Override
    public String getName() {
        return this.name;
    }
    /**
     * Returns the name of the chatRoom.
     *
     * @return the name of the chatRoom.; null indicates that there isn't a name.
     */
    @Override
    public String getFirstMessage() {
        return this.firstMessage;
    }
    /**
     * Sets the name of the chatRoom.
     *
     * @param firstMessage the name of the ChatRoom
     */
    @Override
    public void setFirstMessage(String firstMessage) {
        this.firstMessage = firstMessage;
    }
    /**
     * Sets the name of the chatRoom.
     *
     * @param name the name of the ChatRoom
     */
    @Override
    public void setName(String name) {
        CompletableFuture<ChatRoom> chatRoomFuture = new CompletableFuture<>();
        chatRoomsRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                ChatRoom chatRoom = dataSnapshot.getValue(ChatRoom.class);
//                chatRoomFuture.complete(chatRoom);
                if (dataSnapshot.exists()) {
                    System.out.println("Chat room exists");
                }
                else {
                    ChatRoom chatRoom = new ChatRoom(name, "");
                    chatRoom.setName(name);
                    chatRoomsRef.child(name).setValueAsync(chatRoom);
                    System.out.println("New chat room created");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                chatRoomFuture.completeExceptionally(new RuntimeException(databaseError.getMessage()));
            }
        });
    }
    /**
     * Saves the ChatRoom.
     *
     * @param chatRoom the chatRoom to save
     */
    @Override
    public void save(ChatRoom chatRoom) {
        chatRoomsRef.child(chatRoom.getName()).setValueAsync(chatRoom);
    }
    /**
     * Returns the ChatRoom.
     */
    @Override
    public ChatRoom getChatRoom() {
        CompletableFuture<ChatRoom> chatRoomFuture = new CompletableFuture<>();
        chatRoomsRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ChatRoom chatRoom = dataSnapshot.getValue(ChatRoom.class);
                chatRoomFuture.complete(chatRoom);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                chatRoomFuture.completeExceptionally(new RuntimeException(databaseError.getMessage()));
            }
        });
        try {
            return chatRoomFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Gets a message by its ID.
     *
     * @param messageId the ID of the message to retrieve
     * @return the message with the given ID
     */
    @Override
    public Message getMessage(String messageId) {
        CompletableFuture<Message> messageFuture = new CompletableFuture<>();
        chatRoomsRef.orderByChild("messages/" + messageId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Message message = dataSnapshot.getValue(Message.class);
                messageFuture.complete(message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                messageFuture.completeExceptionally(new RuntimeException(databaseError.getMessage()));
            }
        });
        try {
            return messageFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Updates the content of a message.
     *
     * @param messageId  the ID of the message to update
     * @param newContent the new content for the message
     */
    @Override
    public void updateMessage(String messageId, String newContent) {
        chatRoomsRef.orderByChild("messages/"+ messageId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()) {
                    child.getRef().child("content").setValueAsync(newContent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error updating message: " + databaseError.getMessage());
            }
        });
    }
    /**
     * Returns the user with the given username.
     *
     * @param username the username to look up
     * @return the user with the given username
     */
    @Override
    public User get(String username) {
        CompletableFuture<User> userFuture = new CompletableFuture<>();
        usersRef.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ConcreteUser user = dataSnapshot.getValue(ConcreteUser.class);
                    userFuture.complete(user);
                } else {
                    userFuture.complete(null); // User not found
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                userFuture.completeExceptionally(new RuntimeException(databaseError.getMessage()));
            }
        });

        try {
            return userFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Returns the username of the curren user of the application.
     *
     * @return the username of the current user; null indicates that no one is logged into the application.
     */
    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }
    /**
     * Sets the username indicating who is the current user of the application.
     *
     * @param username the new current username; null to indicate that no one is currently logged into the application.
     */
    @Override
    public void setCurrentUsername(String username) {
        this.currentUsername = username;

    }
    /**
     * Sets the username indicating who is the current user of the application.
     *
     * @param username the new current username.
     * @return UID.
     */
    @Override
    public String getUidByUsername(String username) {
        CompletableFuture<String> uidFuture = new CompletableFuture<>();
        usersRef.child(username).child("uid").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                uidFuture.complete(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                uidFuture.completeExceptionally(new RuntimeException(databaseError.getMessage()));
            }
        });
        try {
            return uidFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Checks if the given username exists.
     *
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    @Override
    public boolean existsByName(String username) {
        CompletableFuture<Boolean> existsFuture = new CompletableFuture<>();
        usersRef.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                existsFuture.complete(dataSnapshot.exists());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                existsFuture.completeExceptionally(new RuntimeException(databaseError.getMessage()));
            }
        });
        try {
            return existsFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Saves the user.
     *
     * @param user the user to save
     */
    @Override
    public void save(User user) {
        usersRef.child(user.getName()).setValueAsync(user);
    }
    /**
     * Saves the user.
     *
     * @param username the user to save
     * @param uid      the user to save
     */
    @Override
    public void saveUid(String username, String uid) {
        usersRef.child(username).child("uid").setValueAsync(uid);
    }
}
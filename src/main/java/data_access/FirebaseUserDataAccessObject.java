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


    public FirebaseUserDataAccessObject()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        this.usersRef = database.getReference("users");
        this.chatRoomsRef = database.getReference("chat_rooms");
    }

    @Override
    public void changePassword(User user) {
        usersRef.child(user.getName()).child("password").setValueAsync(user.getPassword());

    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getFirstMessage() {
        return "";
    }

    @Override
    public void setFirstMessage(String firstMessage) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void save(ChatRoom chatRoom) {
        chatRoomsRef.child(chatRoom.getName()).setValueAsync(chatRoom);
    }

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


    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }

    @Override
    public void setCurrentUsername(String username) {
        this.currentUsername = username;

    }

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

    @Override
    public void save(User user) {
        usersRef.child(user.getName()).setValueAsync(user);
    }

    @Override
    public void saveUid(String username, String uid) {
        usersRef.child(username).child("uid").setValueAsync(uid);
    }
}
package app;

import com.google.firebase.database.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Connects to the chats node in the Firebase Realtime Database.
 */
public class ChatService {
    public static final String MESSAGES = "messages";
    private final DatabaseReference chatRef;

    public ChatService() {
        chatRef = FirebaseDatabase.getInstance().getReference("chats");
    }

    /**
     * Used when a user sends a chat message.
     * @param groupId Unique ID.
     * @param content The data.
     * @param sender  The person who sent the data.
     */
    public void sendMessage(String groupId, String content, String sender) {
        chatRef.child(groupId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    chatRef.child(groupId).setValueAsync(new HashMap<String, Object>());
                    System.out.println("Created new chat room" + groupId);
                }
                final String messageId = chatRef.child(groupId).child(MESSAGES).push().getKey();
                final Message message = new Message(sender, content, System.currentTimeMillis());
                chatRef.child(groupId).child(MESSAGES).child(messageId).setValueAsync(message);
                System.out.println("Message sent to " + groupId);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Failed to read value: " + databaseError.toException());
            }
        });

    }

    /**
     * Automatically updates the app when new messages arrive, simulating real-time chat.
     * @param groupId Unique ID.
     */
    public void addMessageListener(String groupId) {
        chatRef.child(groupId).child(MESSAGES).addChildEventListener(new ChildEventListener() {
            @Override
            // Triggers when a new message is added.
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Converts the raw data into a Message object.
                final Message message = dataSnapshot.getValue(Message.class);
                System.out.println("New message from " + message.getSender() + ": " + message.getContent());
            }

            @Override
            // When a message is updated.
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            // When a message is removed.
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            // When a message's position changes.
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            // If the listener fails, it logs the error.
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
            }
        });
    }
    public CompletableFuture<Map<String, Object>> getChatList() {
        CompletableFuture<Map<String, Object>> futureChatList = new CompletableFuture<>();
        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> chatList = (HashMap<String, Object>) dataSnapshot.getValue();
                    System.out.println("Chat List: ");
                    for (Map.Entry<String, Object> entry : chatList.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    futureChatList.complete(chatList);
                }
                else {
                    futureChatList.complete(new HashMap<>());
                    System.out.println("No chat found");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
            }
        });
        return futureChatList;
    }
}

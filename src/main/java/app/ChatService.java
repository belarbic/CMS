package app;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        final CompletableFuture<Map<String, Object>> futureChatList = new CompletableFuture<>();
        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    final Map<String, Object> chatList = (HashMap<String, Object>) dataSnapshot.getValue();
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

    public CompletableFuture<Map<String, Message>> getMessagesForChatRoom(String chatRoomName) {
        final CompletableFuture<Map<String, Message>> futureMessages = new CompletableFuture<>();
        chatRef.child(chatRoomName).child(MESSAGES).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    final Map<String, Message> chatRoomMessages = new HashMap<>();
                    System.out.println("Chat Room Messages: ");
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        final Message message = snapshot.getValue(Message.class);
                        chatRoomMessages.put(message.getSender(), message);
                    }
                    futureMessages.complete(chatRoomMessages);
                }
                else {
                    futureMessages.complete(new HashMap<>());
                    System.out.println("No chat found");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
            }
        });
        return futureMessages;
    }

    public void addMessageListener(String chatRoomName, Consumer<Message> onNewMessage) {
        chatRef.child(chatRoomName).child(MESSAGES).addChildEventListener(new ChildEventListener() {

            /**
             * This method is triggered when a new child is added to the location to which this listener was
             * added.
             *
             * @param snapshot          An immutable snapshot of the data at the new child location
             * @param previousChildName The key name of sibling location ordered before the new child. This
             *                          will be null for the first child node of a location.
             */
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                final Message message = snapshot.getValue(Message.class);
                if (message != null) {
                    onNewMessage.accept(message);
                }
            }

            /**
             * This method is triggered when the data at a child location has changed.
             *
             * @param snapshot          An immutable snapshot of the data at the new data at the child location
             * @param previousChildName The key name of sibling location ordered before the child. This will
             *                          be null for the first child node of a location.
             */
            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {

            }

            /**
             * This method is triggered when a child is removed from the location to which this listener was
             * added.
             *
             * @param snapshot An immutable snapshot of the data at the child that was removed.
             */
            @Override
            public void onChildRemoved(DataSnapshot snapshot) {

            }

            /**
             * This method is triggered when a child location's priority changes. See {@link
             * DatabaseReference#setPriorityAsync(Object)} and <a
             * href="https://firebase.google.com/docs/database/android/retrieve-data#data_order"
             * target="_blank">Ordered Data</a> for more information on priorities and ordering data.
             *
             * @param snapshot          An immutable snapshot of the data at the location that moved.
             * @param previousChildName The key name of the sibling location ordered before the child
             *                          location. This will be null if this location is ordered first.
             */
            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {

            }

            /**
             * This method will be triggered in the event that this listener either failed at the server, or
             * is removed as a result of the security and Firebase rules. For more information on securing
             * your data, see: <a href="https://firebase.google.com/docs/database/security/quickstart"
             * target="_blank"> Security Quickstart</a>
             *
             * @param error A description of the error that occurred
             */
            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Error listening to message test: " + error.getMessage());
            }
        });
    }
}

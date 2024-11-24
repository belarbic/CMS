package app;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        final String messageId = chatRef.child(groupId).child(MESSAGES).push().getKey();
        final Message message = new Message(sender, content, System.currentTimeMillis());
        chatRef.child(groupId).child(MESSAGES).child(messageId).setValueAsync(message);

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
}

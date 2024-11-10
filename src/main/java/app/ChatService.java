package app;

import com.google.firebase.database.*;

public class ChatService {
    private final DatabaseReference chatRef;

    public ChatService() {
        chatRef = FirebaseDatabase.getInstance().getReference("chats");
    }

    public void sendMessage(String groupId, String content, String sender) {
        String messageId = chatRef.child(groupId).child("messages").push().getKey();
        Message message = new Message(sender, content, System.currentTimeMillis());
        chatRef.child(groupId).child("messages").child(messageId).setValueAsync(message);

    }

    public void addMessageListener(String groupId) {
        chatRef.child(groupId).child("messages").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message message = dataSnapshot.getValue(Message.class);
                System.out.println("New message from " + message.sender + ": " + message.content);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
            }
        });
    }
}

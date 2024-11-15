package entity;

import java.time.LocalDateTime;

/**
 * The representation of a message in our program.
 */
public class Message {

    private String content;
    private User sender;
    private LocalDateTime timestamp;
    private boolean edited;
    private ChatRoom chatRoom;

    public Message(String content, User sender, ChatRoom chatRoom) {
        this.content = content;
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.timestamp = LocalDateTime.now();
        this.edited = false;
    }

    public User getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    /**
     * A method that changes the user's message.
     * @param content the message's new content.
     */
    public void setContent(String content) {
        this.content = content;
        this.edited = true;
    }

    public boolean getEdited() {
        return edited;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }
}

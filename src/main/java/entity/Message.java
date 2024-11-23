package entity;

import java.time.LocalDateTime;

/**
 * The representation of a message in our program.
 */
public class Message {

    private String id;
    private String content;
    private LocalDateTime timestamp;
    private String sender;
    private boolean edited;

    public Message(String content, String sender) {
        // TODO make a call to the API for ID
        this.id = "";
        this.content = content;
        this.sender = sender;
        this.timestamp = LocalDateTime.now();
        this.edited = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isEdited() {
        return edited;
    }

    public String getChatRoom() {
        // TODO have to discuss this
        return "";
    }
}

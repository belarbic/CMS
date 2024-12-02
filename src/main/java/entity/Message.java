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
    private boolean deleted;

    public Message(String content, String sender) {
        this.id = "";
        this.content = content;
        this.sender = sender;
        this.timestamp = LocalDateTime.now();
        this.edited = false;
        this.deleted = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        if (deleted) {
            return "This message has been deleted";
        }
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

    public boolean deleted() {
        return deleted;
    }

    public void delete() {
        this.deleted = true;
    }

    public String getChatRoom() {
        return "";
    }
}

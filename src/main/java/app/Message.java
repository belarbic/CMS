package app;

/**
 * The Message class represents a chat message.
 * Modularity: The class is isolated, making it reusable across different parts of the application.
 * Maintenance: Changes to the Message class won't directly affect other parts of the app.
 */
public class Message {
    private String sender;
    private String content;
    private long timestamp;

    public Message() {

    }

    public Message(String sender, String content, long timestamp) {
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}

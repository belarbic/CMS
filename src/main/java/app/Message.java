package app;

/**
 * The Message class represents a chat message.
 * Modularity: The class is isolated, making it reusable across different parts of the application.
 * Maintenance: Changes to the Message class won't directly affect other parts of the app.
 */
public class Message {
    public String sender;
    public String content;
    public long timestamp;

    public Message() {

    }

    public Message(String sender, String content, long timestamp) {
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }
}

package app;

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

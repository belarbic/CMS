package use_case.create_chatroom;

/**
 * The Input Data for the Create ChatRoom Use Case.
 */
public class CreateChatRoomInputData {

    private final String name;
    private final String firstMessage;

    public CreateChatRoomInputData(String name, String firstMessage) {
        this.name = name;
        this.firstMessage = firstMessage;
    }

    String getName() {
        return name;
    }

    String getFirstMessage() {
        return firstMessage;
    }

}

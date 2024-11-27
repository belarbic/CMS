package use_case.create_chatroom;

/**
 * Output Data for the Create ChatRoom Use Case.
 */
public class CreateChatRoomOutputData {

    private final String name;
    private final String firstMessage;
    private final boolean useCaseFailed;

    public CreateChatRoomOutputData(String name, String firstMessage, boolean useCaseFailed) {
        this.name = name;
        this.firstMessage = firstMessage;
        this.useCaseFailed = useCaseFailed;
    }

    public String getName() {
        return name;
    }

    public String getFirstMessage() {
        return firstMessage;
    }
}

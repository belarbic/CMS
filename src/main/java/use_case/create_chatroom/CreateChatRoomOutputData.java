package use_case.create_chatroom;

/**
 * Output Data for the Create ChatRoom Use Case.
 */
public class CreateChatRoomOutputData {

    private final String name;
    private final boolean useCaseFailed;

    public CreateChatRoomOutputData(String name, boolean useCaseFailed) {
        this.name = name;
        this.useCaseFailed = useCaseFailed;
    }

    public String getName() {
        return name;
    }

}

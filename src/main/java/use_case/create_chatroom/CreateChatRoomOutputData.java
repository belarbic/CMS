package use_case.create_chatroom;

/**
 * Output Data for the Create ChatRoom Use Case.
 */
public class CreateChatRoomOutputData {

    private final String username;

    private final boolean useCaseFailed;

    public CreateChatRoomOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}

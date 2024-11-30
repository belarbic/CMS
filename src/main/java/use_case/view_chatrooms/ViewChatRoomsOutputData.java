package use_case.view_chatrooms;

/**
 * Output Data for the Create ChatRoom Use Case.
 */
public class ViewChatRoomsOutputData {

    private final boolean useCaseFailed;

    public ViewChatRoomsOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }
}

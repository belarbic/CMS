package use_case.create_chatroom;

/**
 * Input Boundary for actions which are related to creating a chatroom.
 */
public interface CreateChatRoomInputBoundary {

    /**
     * Executes the Create chatRoom use case.
     * @param createChatRoomInputData the input data
     */
    void execute(CreateChatRoomInputData createChatRoomInputData);

    void switchToLoggedInView();
}

package use_case.view_chatrooms;

/**
 * Input Boundary for actions which are related to creating a chatroom.
 */
public interface ViewChatRoomsInputBoundary {

    /**
     * Executes the Create chatRoom use case.
     * @param viewChatRoomsInputData the input data
     */
    void execute(ViewChatRoomsInputData viewChatRoomsInputData);
}

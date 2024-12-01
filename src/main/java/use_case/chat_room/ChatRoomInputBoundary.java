package use_case.chat_room;

public interface ChatRoomInputBoundary {
    /**
     * Executes the chatRoom use case.
     * @param chatRoomInputData the input data
     */
    void execute(ChatRoomInputData chatRoomInputData);

    void switchToLoggedInView();
}

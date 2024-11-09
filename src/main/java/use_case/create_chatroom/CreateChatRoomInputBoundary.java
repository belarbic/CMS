package use_case.create_chatroom;

/**
 * The Create ChatRoom Use Case.
 */
public interface CreateChatRoomInputBoundary {

    /**
     * Execute the Create ChatRoom Use Case.
     * @param createChatRoomInputData the input data for this use case
     */
    void execute(CreateChatRoomInputData createChatRoomInputData);

}

package use_case.create_chatroom;

/**
 * The output boundary for the Create ChatRoom Use Case.
 */
public interface CreateChatRoomOutputBoundary {
    /**
     * Prepares the success view for the Create ChatRoom Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(CreateChatRoomOutputData outputData);

    /**
     * Prepares the failure view for the Create ChatRoom Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    void switchToLoggedInView();
}

package use_case.view_chatrooms;

/**
 * The output boundary for the Create ChatRoom Use Case.
 */
public interface ViewChatRoomsOutputBoundary {
    /**
     * Prepares the success view for the Create ChatRoom Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ViewChatRoomsOutputData outputData);

    /**
     * Prepares the failure view for the Create ChatRoom Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}

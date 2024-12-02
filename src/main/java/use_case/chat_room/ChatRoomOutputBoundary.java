package use_case.chat_room;

public interface ChatRoomOutputBoundary {
    /**
     * Prepares the success view for the ChatRoom Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ChatRoomOutputData outputData);

    /**
     * Prepares the failure view for the ChatRoom Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    void switchToLoggedInView();
}

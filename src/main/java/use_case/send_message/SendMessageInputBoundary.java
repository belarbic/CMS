package use_case.send_message;

/**
 * Input Boundary for the Send Message Use case.
 */
public interface SendMessageInputBoundary {
    /**
     * Executes the delete message use case.
     * @param sendMessageInputData the input data
     */
    void execute(SendMessageInputData sendMessageInputData);
}

package use_case.send_message;

/**
 * Output Boundary for the Send Message Use case.
 */
public interface SendMessageOutputBoundary {
    /**
     * Prepares the success view for the send message use case.
     * @param outputData the output data
     */
    void presentSuccessView(SendMessageOutputData outputData);

    /**
     * Prepares the failure view for the send message use case.
     * @param errorMessage the error message
     */
    void presentFailView(String errorMessage);
}

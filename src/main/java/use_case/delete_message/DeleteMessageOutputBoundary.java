package use_case.delete_message;

public interface DeleteMessageOutputBoundary {
    /**
     * Prepares the success view for the delete message use case.
     * @param outputData the output data
     */
    void presentSuccessView(DeleteMessageOutputData outputData);

    /**
     * Prepares the failure view for the delete message use case.
     * @param errorMessage the error message
     */
    void presentFailView(String errorMessage);
}

package use_case.edit_message;

/**
 * Output Boundary for the Edit Message Use Case.
 */
public interface EditMessageOutputBoundary {
    /**
     * Prepares the success view for the Edit Message Use Case.
     * @param editMessageOutputData the output data
     */
    void prepareSuccessView(EditMessageOutputData editMessageOutputData);

    /**
     * Prepares the failure view for the Edit Message Use Case.
     * @param error the error message
     */
    void prepareFailView(String error);

    void switchToLoggedInView();

    void switchToEditMessageView();

}

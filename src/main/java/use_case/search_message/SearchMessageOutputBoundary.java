package use_case.search_message;

/**
 * Output Boundary for the Search Message Use Case.
 */
public interface SearchMessageOutputBoundary {

    /**
     * Prepares the success view for the Search Message Use Case.
     * @param searchMessageOutputData the output data
     */
    void prepareSuccessView(SearchMessageOutputData searchMessageOutputData);

    /**
     * Prepares the failure view for the Search Message Use Case.
     * @param error the error message
     */
    void prepareFailView(String error);

    void switchToLoggedInView();

    void switchToSearchMessageView();
}

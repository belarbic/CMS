package use_case.search_message;

/**
 * Input Boundary for the Search Message Use Case.
 */
public interface SearchMessageInputBoundary {

    /**
     * Executes the search message use case.
     * @param searchMessageInputData the input data
     */
    void execute(SearchMessageInputData searchMessageInputData);
}

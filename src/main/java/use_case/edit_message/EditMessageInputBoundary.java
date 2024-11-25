package use_case.edit_message;

/**
 * Input Boundary for the Edit Message Use Case.
 */
public interface EditMessageInputBoundary {
    /**
     * Executes the edit message use case.
     * @param editMessageInputData the input data
     */
    void execute(EditMessageInputData editMessageInputData);
}

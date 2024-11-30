package use_case.delete_message;

/**
 * The Input Boundary for the delete message use case.
 */
public interface DeleteMessageInputBoundary {
    /**
     * Executes the send message use case.
     * @param deleteMessageInputData the input data
     */
    void execute(DeleteMessageInputData deleteMessageInputData);
}

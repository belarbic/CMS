package interface_adapter.edit_message;

/**
 * ViewModel for presenting the status of an edited message.
 * Tracks the state and provides data to the UI about the success or failure of the edit operation.
 */
public class EditMessageViewModel {

    private boolean success;
    private String message;

    /**
     * Updates the status of the edit operation.
     *
     * @param success_state True if the edit was successful; false ow/.
     * @param message_state A message describing the result of the operation.
     */

    public void updateStatus(boolean success_state, String message_state) {
        this.success = success_state;
        this.message = message_state;
    }

    /**
     * Checks if the edit operation was successful.
     *
     * @return True if successful; false otherwise.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Retrieves the result message.
     *
     * @return The result message.
     */
    public String getMessage() {
        return message;
    }
}


package use_case.edit_message;

/**
 * Output Data for the Edit Message Use Case.
 */
public class EditMessageOutputData {

    private final String newContent;
    private final boolean useCaseFailed;

    /**
     * Creates a new EditMessageOutputData.
     * @param newContent the edited content of the message
     * @param useCaseFailed whether the use case failed
     */
    public EditMessageOutputData(String newContent, boolean useCaseFailed) {
        this.newContent = newContent;
        this.useCaseFailed = useCaseFailed;
    }

    public String getNewContent() {
        return newContent;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}

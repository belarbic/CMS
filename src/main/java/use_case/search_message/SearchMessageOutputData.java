package use_case.search_message;

import java.util.ArrayList;

import entity.Message;

/**
 * Output Data for the Search Message Use Case.
 */
public class SearchMessageOutputData {

    private final ArrayList<Message> messages;
    private final boolean useCaseFailed;

    /**
     * Creates a new SearchMessageOutputData.
     * @param messages the messages matching the search criteria
     * @param useCaseFailed whether the use case failed
     */
    public SearchMessageOutputData(ArrayList<Message> messages, boolean useCaseFailed) {
        this.messages = messages;
        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}

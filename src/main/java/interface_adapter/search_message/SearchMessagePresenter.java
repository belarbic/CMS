package interface_adapter.search_message;

import java.util.ArrayList;

import entity.Message;

/**
 * The SearchMessagesPresenter formats and presents the search results to the user.
 * It transforms the raw message data into a user-friendly format.
 */
public class SearchMessagePresenter {
    /**
     * Presents the search results to the user.
     * If no results are found, a message is displayed. Otherwise, it lists the found messages.
     *
     * @param searchResults The list of messages returned from the search.
     */
    public void presentSearchResults(ArrayList<Message> searchResults) {
        if (searchResults.isEmpty()) {
            System.out.println("No results found.");
        }
        else {
            System.out.println("Search Results:");
            for (Message message : searchResults) {
                System.out.println("Group: " + message.getChatRoom()
                        + " | Sender: " + message.getSender()
                        + " | Timestamp: " + message.getTimestamp()
                        + " | Message: " + message.getContent());
            }
        }
    }
}

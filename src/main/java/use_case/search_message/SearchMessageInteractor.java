package use_case.search_message;

import java.util.ArrayList;

import entity.Message;

/**
 * This interactor handles the logic of searching for messages by a keyword across multiple group chats.
 * It interacts with the MessageRepository to retrieve messages and applies the search filter.
 */
public class SearchMessageInteractor {
    private final MessageRepositoryInterface messageRepository;

    /**
     * Constructs a new SearchMessagesInteractor.
     *
     * @param messageRepository The repository that stores the messages.
     */
    public SearchMessageInteractor(MessageRepositoryInterface messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Searches for messages containing the given keyword.
     *
     * @param keyword The keyword to search for in message contents.
     * @return A list of messages that contain the given keyword.
     */
    public ArrayList<Message> searchMessage(String keyword) {
        final ArrayList<Message> allMessages = messageRepository.getMessages();
        final ArrayList<Message> searchResults = new ArrayList<>();

        for (Message message : allMessages) {
            if (message.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(message);
            }
        }
        return searchResults;
    }
}


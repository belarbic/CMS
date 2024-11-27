package use_case.search_message;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import entity.Message;

/**
 * Interface defining the contract for storing and retrieving messages.
 */
public interface MessageRepositoryInterface {
    /**
     * Adds a message to the repository for future retrieval.
     *
     * @param message The message to be added. Must not be null.
     * @throws IllegalArgumentException If the message is null.
     */
    void addMessage(Message message);

    /**
     * Retrieves all messages stored in the repository.
     *
     * @return A list containing all the messages stored in the repository.
     *         Returns an empty list if no messages are stored.
     */
    ArrayList<Message> getMessages();

    /**
     * Retrieves a message by its unique ID.
     *
     * @param id The unique ID of the message to be retrieved.
     * @return The message with the given ID, or null if no such message exists.
     * @throws IllegalArgumentException If the ID is null or empty.
     */
    Message getMessageById(String id);

    /**
     * Searches for messages containing the specified keyword in their content.
     * The search is case-insensitive and matches partial keywords.
     *
     * @param keyword The keyword to search for in message content.
     * @return A list of messages containing the specified keyword.
     *         If no matching messages are found, an empty list is returned.
     * @throws IllegalArgumentException If the keyword is null or empty.
     */
    ArrayList<Message> searchMessageByKeyword(String keyword);

    /**
     * Deletes a message identified by its unique ID from the repository.
     *
     * @param id The unique ID of the message to be deleted.
     * @throws IllegalArgumentException If the ID is null or empty.
     * @throws java.util.NoSuchElementException If no message is found with the given ID.
     */
    void deleteMessageById(String id);

    /**
     * Updates the content of a message identified by its unique ID.
     *
     * @param id The ID of the message to be updated.
     * @param content The new content for the message.
     * @return The updated message with the new content.
     * @throws IllegalArgumentException If the ID or content is null or empty.
     * @throws java.util.NoSuchElementException If no message is found with the given ID.
     */
    Message updateMessageContent(String id, String content) throws NoSuchElementException;
}

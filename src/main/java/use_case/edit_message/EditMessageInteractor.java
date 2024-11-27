package use_case.edit_message;

import entity.Message;
import use_case.search_message.MessageRepositoryInterface;

/**
 * Interactor for message edting.
 */
public class EditMessageInteractor {
    private final MessageRepositoryInterface messageRepository;

    public EditMessageInteractor(MessageRepositoryInterface messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Edits the content of an existing message.
     *
     * @param id The ID of the message to edit.
     * @param content The new content to replace the existing message content.
     * @return The updated message.
     * @throws IllegalArgumentException if content is empty or null.
     */
    public Message editMessage(String id, String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }

        final Message message = messageRepository.getMessageById(id);

        message.setContent(content);

        return messageRepository.updateMessageContent(id, content);
    }
}

package data_access;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import entity.Message;
import use_case.search_message.MessageRepositoryInterface;

/**
 * In-memory implementation of the {@link MessageRepositoryInterface}.
 * This repository stores messages in a list in memory for temporary storage.
 * It provides methods for adding, retrieving, updating, searching, and deleting messages.
 * This is a simple implementation primarily used for testing or small-scale applications.
 */
public class InMemoryMessageRepository implements MessageRepositoryInterface {
    private final ArrayList<Message> messages = new ArrayList<>();

    @Override
    public void addMessage(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        messages.add(message);
    }

    @Override
    public ArrayList<Message> getMessages() {
        return new ArrayList<>(messages);
    }

    @Override
    public Message getMessageById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Message id cannot be null or empty");
        }
        for (Message message : messages) {
            if (message.getId().equals(id)) {
                return message;
            }
        }
        throw new IllegalArgumentException("Message with id " + id + " not found");
    }

    @Override
    public ArrayList<Message> searchMessageByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Message keyword cannot be null or empty");
        }
        final ArrayList<Message> result = new ArrayList<>();
        for (Message message : messages) {
            if (message.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(message);
            }
        }
        return result;
    }

    @Override
    public void deleteMessageById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Message ID cannot be null or empty.");
        }

        final boolean removed = messages.removeIf(message -> message.getId().equals(id));
        if (!removed) {
            throw new NoSuchElementException("Message with ID " + id + " not found.");
        }
    }

    @Override
    public Message updateMessageContent(String id, String newContent) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Message ID cannot be null or empty.");
        }
        if (newContent == null || newContent.trim().isEmpty()) {
            throw new IllegalArgumentException("New content cannot be null or empty.");
        }

        for (Message message : messages) {
            if (message.getId().equals(id)) {
                message.setContent(newContent);
                return message;
            }
        }
        throw new NoSuchElementException("Message with ID " + id + " not found.");
    }
}

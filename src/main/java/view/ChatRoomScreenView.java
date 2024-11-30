package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

import entity.ChatRoom;
import entity.Message;
import entity.User;
import interface_adapter.delete_message.DeleteMessageController;
import interface_adapter.delete_message.DeleteMessagePresenter;
import interface_adapter.delete_message.DeleteMessageViewModel;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessagePresenter;
import interface_adapter.send_message.SendMessageViewModel;

/**
 * The view for the chatroom screen.
 * Provides user interface for viewing the chatroom
 */
public class ChatRoomScreenView extends JFrame implements MessageView {
    static final int J_FRAME_WIDTH = 600;
    static final int J_FRAME_HEIGHT = 400;

    private final JTextArea messageArea;
    private final JTextField inputField;
    private final JButton sendButton;

    private final ChatRoom chatRoom;
    private final User currentUser;
    private final SendMessageController sendMessageController;
    private final DeleteMessageController deleteMessageController;

    private final SendMessageViewModel sendMessageViewModel;
    private final DeleteMessageViewModel deleteMessageViewModel;

    private final SendMessagePresenter sendMessagePresenter;
    private final DeleteMessagePresenter deleteMessagePresenter;

    public ChatRoomScreenView(ChatRoom chatRoom,
                              User currentUser,
                              SendMessageController sendMessageController,
                              DeleteMessageController deleteMessageController,
                              SendMessagePresenter sendMessagePresenter,
                              DeleteMessagePresenter deleteMessagePresenter,
                              SendMessageViewModel sendMessageViewModel,
                              DeleteMessageViewModel deleteMessageViewModel) {
        this.chatRoom = chatRoom;
        this.currentUser = currentUser;
        this.sendMessageController = sendMessageController;
        this.deleteMessageController = deleteMessageController;
        this.sendMessagePresenter = sendMessagePresenter;
        this.deleteMessagePresenter = deleteMessagePresenter;
        this.sendMessageViewModel = sendMessageViewModel;
        this.deleteMessageViewModel = deleteMessageViewModel;

        // Configure the main frame
        setTitle("Chatroom: " + chatRoom.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(J_FRAME_WIDTH, J_FRAME_HEIGHT);
        setLayout(new BorderLayout());

        // Message display area
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        // Add right-click context menu
        addContextMenu();

        // Input field and send button
        final JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // Add action listener for sending messages
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Load initial messages
        refreshMessages();

        setVisible(true);
    }

    /**
     * Sends a message using the controller and updates the view.
     */
    private void sendMessage() {
        final String content = inputField.getText().trim();
        if (!content.isEmpty()) {
            sendMessageController.execute(content, currentUser, chatRoom);
            inputField.setText("");
            refreshMessages(); // Refresh the view after sending a message
        } else {
            sendMessagePresenter.presentFailView("Message cannot be empty!");
        }
    }

    /**
     * Adds a context menu to the message area for deleting messages.
     */
    private void addContextMenu() {
        JPopupMenu contextMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete Message");

        deleteItem.addActionListener(e -> {
            int caretPosition = messageArea.getCaretPosition();
            int line;
            try {
                // Get the line number of the clicked position
                line = messageArea.getLineOfOffset(caretPosition);

                // Get the corresponding message by line and delete it
                String messageId = getMessageIdByLine(line);
                if (messageId != null) {
                    deleteMessage(messageId);
                }
            } catch (Exception ex) {
                showError("Failed to identify the message.");
            }
        });

        contextMenu.add(deleteItem);

        // Attach the context menu to the message area
        messageArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    contextMenu.show(messageArea, e.getX(), e.getY());
                }
            }
        });
    }

    /**
     * Retrieves a message ID by its line number in the message area.
     *
     * @param line the line number
     * @return the ID of the message on that line, or null if not found
     */
    private String getMessageIdByLine(int line) {
        List<Message> messages = chatRoom.getMessages();
        if (line >= 0 && line < messages.size()) {
            return messages.get(line).getId();
        }
        return null;
    }

    /**
     * Deletes the specified message and refreshes the view.
     *
     * @param messageId the ID of the message to delete
     */
    private void deleteMessage(String messageId) {
        deleteMessageController.execute(messageId, chatRoom.getName());
        refreshMessages(); // Refresh the view after deleting the message
    }

    /**
     * Refreshes the messages displayed in the chatroom.
     */
    private void refreshMessages() {
        List<String> messages = chatRoom.getMessages().stream()
                .map(message -> message.getSender() + ": " + message.getContent())
                .toList();
        messageArea.setText(String.join("\n", messages));
    }

    // MessageView Implementation
    @Override
    public void showUpdatedMessage(String updatedMessage) {
        refreshMessages(); // Refresh messages if there's an update
    }

    @Override
    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showSearchResults(List<String> formattedMessages) {
        messageArea.setText(String.join("\n", formattedMessages));
    }

    @Override
    public void showNoResults() {
        JOptionPane.showMessageDialog(this, "No results found.", "Search Results",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showEditStatus(boolean isSuccess, String message) {
        JOptionPane.showMessageDialog(this, message, isSuccess ? "Edit Successful" : "Edit Failed",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

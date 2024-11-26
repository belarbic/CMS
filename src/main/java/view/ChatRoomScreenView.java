package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import entity.ChatRoom;
import entity.User;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessagePresenter;
import interface_adapter.send_message.SendMessageViewModel;

/**
 * The view for the chatroom.
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

    private final SendMessageViewModel viewModel;
    private final SendMessagePresenter presenter;

    public ChatRoomScreenView(ChatRoom chatRoom, User currentUser,
                        SendMessageController sendMessageController,
                        SendMessagePresenter presenter,
                        SendMessageViewModel viewModel) {
        this.chatRoom = chatRoom;
        this.currentUser = currentUser;
        this.sendMessageController = sendMessageController;
        this.presenter = presenter;
        this.viewModel = viewModel;

        // Configure the main frame
        setTitle("Chatroom: " + chatRoom.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(J_FRAME_WIDTH, J_FRAME_HEIGHT);
        setLayout(new BorderLayout());

        // Message display area
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);

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

        setVisible(true);
    }

    public ChatRoomScreenView(JTextArea messageArea, JTextField inputField, JButton sendButton,
                              ChatRoom chatRoom, User currentUser, SendMessageController sendMessageController,
                              SendMessageViewModel viewModel, SendMessagePresenter presenter) {
        this.messageArea = messageArea;
        this.inputField = inputField;
        this.sendButton = sendButton;
        this.chatRoom = chatRoom;
        this.currentUser = currentUser;
        this.sendMessageController = sendMessageController;
        this.viewModel = viewModel;
        this.presenter = presenter;
    }

    /**
     * Sends a message using the controller and updates the view.
     */
    private void sendMessage() {
        final String content = inputField.getText().trim();
        if (!content.isEmpty()) {
            sendMessageController.handleSendMessage(content, currentUser, chatRoom);
            inputField.setText("");
        }
        else {
            presenter.presentMessageFailure("Message cannot be empty!");
        }
    }

    // MessageView Implementation
    @Override
    public void showUpdatedMessage(String updatedMessage) {
        messageArea.append(updatedMessage + "\n");
    }

    @Override
    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showSearchResults(java.util.List<String> formattedMessages) {
        messageArea.setText("");
        for (String message : formattedMessages) {
            messageArea.append(message + "\n");
        }
    }

    @Override
    public void showNoResults() {
        JOptionPane.showMessageDialog(this, "No results found.", "Search Results",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showEditStatus(boolean isSuccess, String message) {

    }
}

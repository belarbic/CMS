package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.*;

import app.ChatService;
import app.Message;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat_room.ChatRoomController;
import interface_adapter.chat_room.ChatRoomViewModel;

/**
 * The View for when the user is creating a chatroom in the program.
 */
public class ChatRoomView extends JPanel implements ActionListener, PropertyChangeListener {

    static final int J_FRAME_WIDTH = 600;
    static final int J_FRAME_HEIGHT = 400;

    private final JTextArea messageArea;
    private final JTextField inputField;
    private final JButton sendButton;
    private final JButton backButton;

    private final String viewName = "Chatroom";
    private final ChatRoomViewModel chatRoomViewModel;

    private ChatRoomController chatRoomController;
    private ViewManagerModel viewManagerModel;

    public ChatRoomView(ChatRoomViewModel chatRoomViewModel) {

        this.chatRoomViewModel = chatRoomViewModel;
        this.chatRoomViewModel.addPropertyChangeListener(this);

        // Configure the main frame
//        setTitle("Chatroom: " + chatRoom.getName());
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Plain font for errors
        inputField.setForeground(new Color(0, 0, 0)); // Red color for error messages
        inputField.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Modern, bold font for the button
        sendButton.setPreferredSize(new Dimension(150, 40)); // Smaller button width (150px)
        sendButton.setMaximumSize(new Dimension(150, 40));  // Limit width to 150px
        sendButton.setFocusPainted(false);  // Remove focus border
        sendButton.setBackground(new Color(255, 255, 255));  // Light red color for 'Back' button
        sendButton.setForeground(Color.BLACK); // White text color
        sendButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside the button
        sendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Pointer cursor on hover
        backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Modern, bold font for the button
        backButton.setPreferredSize(new Dimension(150, 40)); // Smaller button width (150px)
        backButton.setMaximumSize(new Dimension(150, 40));  // Limit width to 150px
        backButton.setFocusPainted(false);  // Remove focus border
        backButton.setBackground(new Color(255, 92, 92));  // Light red color for 'Back' button
        backButton.setForeground(Color.WHITE); // White text color
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside the button
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Pointer cursor on hover
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.add(backButton, BorderLayout.WEST);
        add(inputPanel, BorderLayout.SOUTH);

        // Add action listener for sending messages
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        backButton.addActionListener(evt -> {
            if (evt.getSource().equals(backButton)) {
                chatRoomController.switchToLoggedInView();
            }
        });  // Action listener for the button

        // Load initial messages
        refreshMessages();

        setVisible(true);
    }

    /**
     * Sends a message using the controller and updates the view.
     */
    private void sendMessage() {
//        final String content = inputField.getText().trim();
//        if (!content.isEmpty()) {
//            sendMessageController.execute(content, currentUser, chatRoom);
//            inputField.setText("");
//            refreshMessages(); // Refresh the view after sending a message
//        } else {
//            sendMessagePresenter.presentFailView("Message cannot be empty!");
//        }
        String userName = chatRoomController.getUserName();
        String chatRoomName = chatRoomController.getChatRoomName();
        final String content = inputField.getText().trim();
        ChatService chatService = new ChatService();
        chatService.sendMessage(chatRoomName, content, userName);
        inputField.setText("");
//        refreshMessages();
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
//        java.util.List<Message> messages = chatRoom.getMessages();
//        if (line >= 0 && line < messages.size()) {
//            return messages.get(line).getId();
//        }
        return null;
    }

    /**
     * Deletes the specified message and refreshes the view.
     *
     * @param messageId the ID of the message to delete
     */
    private void deleteMessage(String messageId) {
//        deleteMessageController.execute(messageId, chatRoom.getName());
        refreshMessages(); // Refresh the view after deleting the message
    }

    /**
     * Refreshes the messages displayed in the chatroom.
     */
    private void refreshMessages() {
//        java.util.List<String> messages = chatRoom.getMessages().stream()
//                .map(message -> message.getSender() + ": " + message.getContent())
//                .toList();
//        messageArea.setText(String.join("\n", messages));
        String chatRoomName = "";
        if (chatRoomController != null) {
            chatRoomName = chatRoomController.getChatRoomName();
        }
        ChatService chatService = new ChatService();
//        chatService.getMessagesForChatRoom(chatRoomName).thenAccept(messages -> {
//            System.out.println("Received messages: ");
//            for (Map.Entry<String, Message> messageEntry : messages.entrySet()) {
//                System.out.println(messageEntry.getKey() + ": " + messageEntry.getValue().getContent());
//                messageArea.append(messageEntry.getKey() + ": " + messageEntry.getValue().getContent() + "\n");
//            }
//        });
        chatService.addMessageListener(chatRoomName, newMessage -> {
            SwingUtilities.invokeLater(() -> {
                messageArea.append(newMessage.getSender() + ": " + newMessage.getContent() + "\n");
            });
        });
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
    public String getViewName() {
        return viewName;
    }
    public void setChatRoomController(ChatRoomController chatRoomController) {
        this.chatRoomController = chatRoomController;
    }
}

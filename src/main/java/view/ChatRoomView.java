package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import java.awt.Color;
import java.util.List;

import app.ChatService;
import entity.Message;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat_room.ChatRoomController;
import interface_adapter.chat_room.ChatRoomState;
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

    private final String viewName = "Chatroom";
    private final ChatRoomViewModel chatRoomViewModel;

    private final JTextField nameInputField = new JTextField(15);
    private final JTextField messageInputField = new JTextField(15);
    private final JLabel nameErrorField = new JLabel();

//    private final JButton chatRoom;
//    private final JButton cancel;
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

        // Add components to the main panel
//        this.add(title);
//        this.add(Box.createVerticalStrut(20)); // Spacing after the title
//        this.add(nameInfo);
//        this.add(nameErrorField);
//        this.add(Box.createVerticalStrut(-10)); // Spacing between name and message fields
//        this.add(messageInfo);
//        this.add(Box.createVerticalStrut(20)); // Spacing before buttons
//        this.add(buttons);
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
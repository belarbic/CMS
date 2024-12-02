package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.*;

import app.ChatService;
import interface_adapter.chat_room.ChatRoomController;
import interface_adapter.view_chatrooms.ViewChatRoomsController;
import interface_adapter.view_chatrooms.ViewChatRoomsState;
import interface_adapter.view_chatrooms.ViewChatRoomsViewModel;

/**
 * The View for when the user is creating a chatroom in the program.
 */
public class ViewChatRoomsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "View Chatrooms";
    private final JList<String> chatRoomList;
    private final JButton cancel;
    private ViewChatRoomsController viewChatRoomsController;
    private ChatRoomController chatRoomController;

    public ViewChatRoomsView(ViewChatRoomsViewModel viewChatRoomsViewModel) {
        // Title styling
        final JLabel title = new JLabel("View Chatrooms");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));  // Modern font with bold style
        title.setForeground(new Color(40, 40, 40));  // Dark gray for text color
        title.setAlignmentX(Component.CENTER_ALIGNMENT); // Center title alignment
        this.add(Box.createVerticalStrut(40)); // Adds space below the title

// Welcome statement styling
        final JLabel welcomeStatement = new JLabel("Select a chatroom you're a part of:");
        welcomeStatement.setFont(new Font("Segoe UI", Font.PLAIN, 16));  // Lighter font for readability
        welcomeStatement.setForeground(new Color(100, 100, 100));  // Muted gray for the welcome message
        welcomeStatement.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment for text
        this.add(welcomeStatement);

// Add vertical space between welcome statement and chatroom list
        this.add(Box.createVerticalStrut(-40));  // Adds 20 pixels of space

// Chatroom list setup
        chatRoomList = new JList<>(new DefaultListModel<>());
        chatRoomList.setFont(new Font("Segoe UI", Font.PLAIN, 16));  // Modern font
        chatRoomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chatRoomList.setLayoutOrientation(JList.VERTICAL);
        chatRoomList.setVisibleRowCount(-1);
        chatRoomList.setBackground(Color.WHITE);  // White background for the list
        chatRoomList.setBorder(BorderFactory.createEmptyBorder(-40, 10, -40, 10)); // Padding inside the list

        // Modern Scroll Pane
        JScrollPane chatRoomListScrollPane = new JScrollPane(chatRoomList);
        chatRoomListScrollPane.setPreferredSize(new Dimension(300, 200));  // Set smaller dimensions for the scroll pane
        chatRoomListScrollPane.setMinimumSize(new Dimension(300, 150));  // Minimum size to prevent it from shrinking too much
        chatRoomListScrollPane.setMaximumSize(new Dimension(500, 200));  // Maximum size for flexibility
        this.add(Box.createVerticalStrut(70));
        chatRoomListScrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),  // Light gray border
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding inside the scroll pane
        ));

// Modern Scrollbar Customization
        chatRoomListScrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(180, 180, 180);  // Light gray color for the thumb
                this.trackColor = new Color(240, 240, 240);  // Light background for the track
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(new Color(240, 240, 240));  // Light background for the button
                button.setPreferredSize(new Dimension(0, 0));  // Make it invisible (optional)
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(new Color(240, 240, 240));  // Light background for the button
                button.setPreferredSize(new Dimension(0, 0));  // Make it invisible (optional)
                return button;
            }
        });

        chatRoomList.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting() && chatRoomList.getSelectedValue() != null) {
                String selectedValue = (String) chatRoomList.getSelectedValue();
                System.out.println("Selected chat room: " + selectedValue);
                // Action when a chat room is selected
                chatRoomController.execute(selectedValue, "hello");
            }
        });

        getChatRoomList(); // Method to populate the chatroom list
        this.add(chatRoomListScrollPane);

// Add vertical space between the chat room list and button panel (adjusted)
        this.add(Box.createVerticalStrut(10));  // Adds 10 pixels of space instead of 30

// Button panel setup
        final JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setBackground(new Color(240, 240, 240));  // Light background for the button panel

        cancel = new JButton("Back");
        cancel.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Modern, bold font for the button
        cancel.setPreferredSize(new Dimension(150, 40)); // Smaller button width (150px)
        cancel.setMaximumSize(new Dimension(150, 40));  // Limit width to 150px
        cancel.setFocusPainted(false);  // Remove focus border
        cancel.setBackground(new Color(255, 92, 92));  // Light red color for 'Back' button
        cancel.setForeground(Color.WHITE); // White text color
        cancel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside the button
        cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Pointer cursor on hover

// Hover effect for the button
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancel.setBackground(new Color(255, 70, 70));  // Darker red on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancel.setBackground(new Color(255, 92, 92));  // Original color
            }
        });

        buttons.add(cancel); // Add button to the panel

        cancel.addActionListener(evt -> {
            if (evt.getSource().equals(cancel)) {
                viewChatRoomsController.switchToLoggedInView();
            }
        });  // Action listener for the button

// Layout the main panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

// Add components to the main panel
        this.add(title);
        this.add(Box.createVerticalStrut(40));
        this.add(welcomeStatement);
        this.add(Box.createVerticalStrut(10));
        this.add(chatRoomListScrollPane);
        this.add(Box.createVerticalStrut(20));
        this.add(buttons);
        this.add(Box.createVerticalStrut(10));
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ViewChatRoomsState state = (ViewChatRoomsState) evt.getNewValue();
    }

    private void setFields() {
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewChatRoomsController(ViewChatRoomsController viewChatRoomsController) {
        this.viewChatRoomsController = viewChatRoomsController;
    }

    public void setChatRoomController(ChatRoomController chatRoomController) {
        this.chatRoomController = chatRoomController;
    }

    public void getChatRoomList() {
        ChatService chatService = new ChatService();
        chatService.getChatList().thenAccept(chatRooms -> {
            if (chatRooms != null) {
                DefaultListModel<String> listModel = (DefaultListModel<String>) chatRoomList.getModel();
                for (Map.Entry<String, Object> entry : chatRooms.entrySet()) {
                    listModel.addElement(entry.getKey());
                }
            }
        });
    }
}

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

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
    private final ViewChatRoomsViewModel viewChatRoomsViewModel;
    private final JList<String> chatRoomList;
    private final JButton cancel;
    private ViewChatRoomsController viewChatRoomsController;
    private ChatRoomController chatRoomController;

    public ViewChatRoomsView(ViewChatRoomsViewModel viewChatRoomsViewModel) {
        this.viewChatRoomsViewModel = viewChatRoomsViewModel;
        this.viewChatRoomsViewModel.addPropertyChangeListener(this);

        // Title styling
        final JLabel title = new JLabel("View Chatrooms");
        title.setFont(new Font("Arial", Font.BOLD, 24));  // Set font size and bold
        title.setAlignmentX(Component.CENTER_ALIGNMENT); // Center title alignment
        this.add(Box.createVerticalStrut(50)); // Adds space below the title

        // Welcome statement styling
        final JLabel welcomeStatement = new JLabel("Here are the chatrooms you're a part of:");
        welcomeStatement.setFont(new Font("Arial", Font.BOLD, 16));  // Set font size and bold
        welcomeStatement.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment for text
        this.add(welcomeStatement);

        // Add vertical space between welcome statement and back button
        this.add(Box.createVerticalStrut(30));  // Adds 30 pixels of space between the two components

        chatRoomList = new JList<>(new DefaultListModel<>());
        chatRoomList.setFont(new Font("Arial", Font.PLAIN, 16));
        chatRoomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chatRoomList.setLayoutOrientation(JList.VERTICAL);
        chatRoomList.setVisibleRowCount(-1);
        JScrollPane chatRoomListScrollPane = new JScrollPane(chatRoomList);
        chatRoomListScrollPane.setPreferredSize(new Dimension(80, 200));

        chatRoomList.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting() && chatRoomList.getSelectedValue() != null) {
                String selectedValue = (String) chatRoomList.getSelectedValue();
                System.out.println("Selected chat room: " + selectedValue);
                viewChatRoomsController.openChatRoom();
            }
        });
        getChatRoomList();
        this.add(Box.createVerticalStrut(30));  // Adds 30 pixels of space between the two components
        // Button panel
        final JPanel buttons = new JPanel();
        cancel = new JButton("Back");
        cancel.setFont(new Font("Arial", Font.PLAIN, 14)); // Consistent font for the button
        cancel.setPreferredSize(new Dimension(200, 40)); // Set button size
        cancel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));  // Allow horizontal expansion
        cancel.setFocusPainted(false);  // Remove focus border
        cancel.setBackground(new Color(255, 92, 92));  // Light red color for 'Back' button
        cancel.setForeground(Color.WHITE); // White text color
        buttons.add(cancel);

        cancel.addActionListener(evt -> {
            if (evt.getSource().equals(cancel)) {
                viewChatRoomsController.switchToLoggedInView();
            }
        });  // Action listener for the button

        // Layout the main panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add components to the main panel
        this.add(title);
        this.add(welcomeStatement);
        this.add(chatRoomListScrollPane);
        this.add(buttons);
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

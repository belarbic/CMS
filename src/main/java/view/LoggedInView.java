package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.chat_room.ChatRoomController;
import interface_adapter.create_chatroom.CreateChatRoomController;
import interface_adapter.create_chatroom.CreateChatRoomViewModel;
import interface_adapter.edit_message.EditMessageController;
import interface_adapter.logout.LogoutController;
import interface_adapter.search_message.SearchMessageController;
import interface_adapter.login.LoginState;
import interface_adapter.logout.LogoutController;
import interface_adapter.view_chatrooms.ViewChatRoomsController;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "Logged In";
    private final LoggedInViewModel loggedInViewModel;
    // private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;


    private final JLabel name;

    private final JButton logOut;

    // private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;

    private ChatRoomController chatRoomController;

    // new button to create new chatroom
    private final JButton createChatRoom;
    private CreateChatRoomController createChatRoomController;

    // new button to view current chatrooms
    private final JButton viewChatRooms;
    private ViewChatRoomsController viewChatRoomsController;

    // new button to search messages
    private final JButton searchMessages;
    private SearchMessageController searchMessageController;

    private final JButton editMessage;
    private EditMessageController editMessageController;

        public LoggedInView(LoggedInViewModel loggedInViewModel) {
            this.loggedInViewModel = loggedInViewModel;
            this.loggedInViewModel.addPropertyChangeListener(this);

            // Title label styling and alignment
            final JLabel title = new JLabel("Welcome! ");
            title.setFont(new Font("Arial", Font.BOLD, 24));  // Set font size and bold
            title.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center title alignment

            // Add vertical space above the title to move it down
            this.add(Box.createVerticalStrut(20));  // Adds 20 pixels of space above the title

            // Create additional info labels
            final JLabel usernameInfo = new JLabel("Currently logged in: ");
            usernameInfo.setFont(new Font("Arial", Font.PLAIN, 16));  // Set consistent font size
            usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center alignment for username info

            name = new JLabel();
            name.setFont(new Font("Arial", Font.PLAIN, 16));  // Consistent font for username display
            name.setAlignmentX(Component.CENTER_ALIGNMENT);

            final JLabel welcomeStatement = new JLabel("What would you like to do? ");
            welcomeStatement.setFont(new Font("Arial", Font.PLAIN, 16));  // Consistent font for statement
            welcomeStatement.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Panel for buttons, with consistent layout and spacing
            final JPanel buttons = new JPanel();
            buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center buttons with spacing

            // Button creation with consistent styling
            logOut = createButton("Log Out", new Color(255, 92, 92), Color.WHITE);
            buttons.add(logOut);

            changePassword = createButton("Change Password", new Color(34, 193, 195), Color.WHITE);
            buttons.add(changePassword);


            createChatRoom = createButton("Create Chatroom", new Color(34, 193, 195), Color.WHITE);
            buttons.add(createChatRoom);

            viewChatRooms = createButton("View Chatrooms", new Color(34, 193, 195), Color.WHITE);
            buttons.add(viewChatRooms);

            searchMessages = createButton("Search Messages", new Color(34, 193, 195), Color.WHITE);
            buttons.add(searchMessages);

            editMessage = createButton("Edit Message", new Color(34, 193, 195), Color.WHITE);
            buttons.add(editMessage);

            // Action listeners for each button, triggering the appropriate controller actions
            logOut.addActionListener(evt -> {
                if (evt.getSource().equals(logOut)) {
                    final LoggedInState currentState = loggedInViewModel.getState();
                    logoutController.execute(currentState.getUsername());
                }
            });

            createChatRoom.addActionListener(evt -> {
                if (evt.getSource().equals(createChatRoom)) {
                    final LoggedInState currentState = loggedInViewModel.getState();
                    createChatRoomController.execute(currentState.getUsername(), "");
                }
            });

            viewChatRooms.addActionListener(evt -> {
                if (evt.getSource().equals(viewChatRooms)) {
                    final LoggedInState currentState = loggedInViewModel.getState();
                    viewChatRoomsController.execute();
                }
            });

            searchMessages.addActionListener(evt -> {
                if (evt.getSource().equals(searchMessages)) {
//                    final LoggedInState currentState = loggedInViewModel.getState();
//                    searchMessageController.execute("", currentState.getUsername());
                    searchMessageController.switchToSearchMessageView();
                }
            });

            editMessage.addActionListener(evt -> {
                if (evt.getSource().equals(editMessage)) {
//                    final LoggedInState currentState = loggedInViewModel.getState();
//                    editMessageController.execute("test-message-id", "Hello World", currentState.getUsername());
                    editMessageController.switchToEditMessageView();
                }
            });

            changePassword.addActionListener(evt -> {
                if (evt.getSource().equals(changePassword)) {
//                    final LoggedInState currentState = loggedInViewModel.getState();
//                    changePasswordController.execute("123456", currentState.getUsername());
                    changePasswordController.switchToChangePasswordView();
                }
            });



            // Set layout of the main panel
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // Add components to the main panel
            this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Padding around the view
            this.setBackground(new Color(240, 240, 240)); // Light gray background to match previous views
            this.add(title);
            this.add(usernameInfo);
            this.add(name);
            this.add(welcomeStatement);

            // Add buttons panel
            this.add(buttons);
        }

        // Method to create buttons with consistent styling
        private JButton createButton(String labelText, Color backgroundColor, Color textColor) {
            JButton button = new JButton(labelText);
            button.setFont(new Font("Arial", Font.PLAIN, 14)); // Font styling for button
            button.setPreferredSize(new Dimension(200, 40));
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Allow to grow horizontally
            button.setFocusPainted(false);  // Remove focus outline for a clean look
            button.setBackground(backgroundColor);  // Set background color for the button
            button.setForeground(textColor);  // Set text color for the button
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside button
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand
            return button;
        }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            name.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }
    }


    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setCreateChatRoomController(CreateChatRoomController createChatRoomController) {
        this.createChatRoomController = createChatRoomController;
    }

    public void setViewChatRoomsController(ViewChatRoomsController viewChatRoomsController) {
        this.viewChatRoomsController = viewChatRoomsController;
    }

    public void setSearchMessageController(SearchMessageController searchMessageController) {
        this.searchMessageController = searchMessageController;
    }

    public void setEditMessageController(EditMessageController editMessageController) {
        this.editMessageController = editMessageController;
    }
    public void setChatRoomController(ChatRoomController chatRoomController) {
            this.chatRoomController = chatRoomController;
    }
}

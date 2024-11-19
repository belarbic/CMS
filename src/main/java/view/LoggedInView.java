package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.create_chatroom.CreateChatRoomController;
import interface_adapter.logout.LogoutController;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    // private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;

    private final JLabel name;

    private final JButton logOut;

    // private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;

    //new button to change username
    private final JButton changeUsername;

    // new button to create new chatroom
    private final JButton createChatRoom;
    private CreateChatRoomController createChatRoomController;

    // new button to view current chatrooms
    private final JButton viewChatRooms;

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Welcome! ");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // final LabelTextPanel passwordInfo = new LabelTextPanel(
        //        new JLabel("Password"), passwordInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        name = new JLabel();
        final JLabel welcomeStatement = new JLabel("What would you like to do? ");

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        changePassword = new JButton("Change Password");
        buttons.add(changePassword);

        // this code creates Change Username button and adds it to buttons.
        changeUsername = new JButton("Change Username");
        buttons.add(changeUsername);

        // this code creates the Create Chat Room button and adds it to buttons.
        createChatRoom = new JButton("Create Chat Room");
        buttons.add(createChatRoom);

        // this code creates the View Chat Room button and adds it to buttons.
        viewChatRooms = new JButton("View Chat Rooms");
        buttons.add(viewChatRooms);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        /*
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                loggedInViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final LoggedInState currentState = loggedInViewModel.getState();

                        this.changePasswordController.execute(
                                currentState.getUsername(),
                                currentState.getPassword()
                        );
                    }
                }
        );
*/
        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        final LoggedInState currentState = loggedInViewModel.getState();
                        // 2. Execute the logout Controller.
                        logoutController.execute(
                                currentState.getUsername()
                        );
                    }
                }
        );

        createChatRoom.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(createChatRoom)) {
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        final LoggedInState currentState = loggedInViewModel.getState();
                        // 2. Execute the createChatRoom Controller.
                        createChatRoomController.execute(
                                currentState.getUsername()
                        );
                    }
                }
        );

        this.add(title);
        this.add(usernameInfo);
        this.add(name);
        this.add(welcomeStatement);

        // this.add(passwordInfo);
        // this.add(passwordErrorField);
        this.add(buttons);
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
}

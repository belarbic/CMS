package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.create_chatroom.CreateChatRoomController;
import interface_adapter.create_chatroom.CreateChatRoomViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

/**
 * The View for when the user is logging into the program.
 */
public class CreateChatRoomView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "create chat room";
    private final CreateChatRoomViewModel createChatRoomViewModel;

    private final JTextField nameInputField = new JTextField(15);
    private final JLabel nameErrorField = new JLabel();

    private final JTextField messageInputField = new JTextField(15);
    private final JLabel messageErrorField = new JLabel();

    private final JButton createChatRoom;
    private final JButton cancel;
    private CreateChatRoomController createChatRoomController;

    public CreateChatRoomView(CreateChatRoomViewModel createChatRoomViewModel) {

        this.createChatRoomViewModel = createChatRoomViewModel;
        this.createChatRoomViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Create ChatRoom Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel nameInfo = new LabelTextPanel(
                new JLabel("Name"), nameInputField);
        final LabelTextPanel messageInfo = new LabelTextPanel(
                new JLabel("Message"), messageInputField);

        final JPanel buttons = new JPanel();
        createChatRoom = new JButton("create chat room");
        buttons.add(createChatRoom);
        cancel = new JButton("cancel");
        buttons.add(cancel);

        createChatRoom.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createChatRoom)) {
                            final LoginState currentState = createChatRoomViewModel.getState();

                            createChatRoomController.execute(
                                    currentState.getName(),
                                    currentState.getMessages()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(this);

        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                loginViewModel.setState(currentState);
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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                loginViewModel.setState(currentState);
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

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
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
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        usernameErrorField.setText(state.getLoginError());
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}

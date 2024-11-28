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
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.Box;

import interface_adapter.change_password.LoggedInState;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;

/**
 * The View for when the user is logging into the program.
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Log In";
    private final LoginViewModel loginViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    private final JButton logIn;
    private final JButton cancel;
    private LoginController loginController;

//    public LoginView(LoginViewModel loginViewModel) {
//
//        this.loginViewModel = loginViewModel;
//        this.loginViewModel.addPropertyChangeListener(this);
//
//        final JLabel title = new JLabel("Login");
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        final LabelTextPanel usernameInfo = new LabelTextPanel(
//                new JLabel("Username"), usernameInputField);
//        final LabelTextPanel passwordInfo = new LabelTextPanel(
//                new JLabel("Password"), passwordInputField);
//
//        final JPanel buttons = new JPanel();
//        logIn = new JButton("Log In");
//        buttons.add(logIn);
//        cancel = new JButton("Back");
//        buttons.add(cancel);
//
//        logIn.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(logIn)) {
//                            final LoginState currentState = loginViewModel.getState();
//
//                            loginController.execute(
//                                    currentState.getUsername(),
//                                    currentState.getPassword()
//                            );
//                        }
//                    }
//                }
//        );
//
//        cancel.addActionListener(this);
//
//        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {
//
//            private void documentListenerHelper() {
//                final LoginState currentState = loginViewModel.getState();
//                currentState.setUsername(usernameInputField.getText());
//                loginViewModel.setState(currentState);
//            }
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//        });
//
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
//
//            private void documentListenerHelper() {
//                final LoginState currentState = loginViewModel.getState();
//                currentState.setPassword(new String(passwordInputField.getPassword()));
//                loginViewModel.setState(currentState);
//            }
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//        });
//
//        this.add(title);
//        this.add(usernameInfo);
//        this.add(usernameErrorField);
//        this.add(passwordInfo);
//        this.add(buttons);
//    }
public LoginView(LoginViewModel loginViewModel) {
    this.loginViewModel = loginViewModel;
    this.loginViewModel.addPropertyChangeListener(this);

    // Title label styling and alignment
    final JLabel title = new JLabel("Login");
    title.setFont(new Font("Arial", Font.BOLD, 24));  // Set font size and bold
    title.setAlignmentX(Component.CENTER_ALIGNMENT); // Center title alignment

    // Add vertical spacing before the title to match the SignupView
    this.add(Box.createVerticalStrut(50));  // Adjust the value (50) for more or less space

    // Create the input panels for username and password
    final LabelTextPanel usernameInfo = createLabelTextPanel("Username", usernameInputField);
    final LabelTextPanel passwordInfo = createLabelTextPanel("Password", passwordInputField);

    usernameInputField.setPreferredSize(new java.awt.Dimension(250, 30));  // Increase width and height
    usernameInputField.setFont(new Font("Arial", Font.PLAIN, 16));  // Increase font size
    usernameInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Add padding for clarity

    passwordInputField.setPreferredSize(new java.awt.Dimension(250, 30));  // Increase width and height
    passwordInputField.setFont(new Font("Arial", Font.PLAIN, 16));  // Increase font size
    passwordInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Add padding for clarity

    // Create buttons panel and add buttons to it
    final JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center buttons with spacing

    // Log In button styling
    logIn = createButton("Log In", new Color(34, 193, 195), Color.WHITE);
    buttons.add(logIn);

    // Back button styling
    cancel = createButton("Back", new Color(204, 204, 204), Color.BLACK);
    buttons.add(cancel);

    // Button action listeners
    logIn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource().equals(logIn)) {
                final LoginState currentState = loginViewModel.getState();
                loginController.execute(
                        currentState.getUsername(),
                        currentState.getPassword()
                );
            }
        }
    });

    cancel.addActionListener(this);

    // Add listeners for username and password input fields
    addUsernameListener();
    addPasswordListener();

    // Set layout of the main panel and add components
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(title);
    this.add(usernameInfo);
    this.add(usernameErrorField);
    this.add(passwordInfo);
    this.add(buttons);
}

    // Helper method to create LabelTextPanel with label and input field
    private LabelTextPanel createLabelTextPanel(String labelText, JTextField inputField) {
        final JLabel label = new JLabel(labelText);
        LabelTextPanel panel = new LabelTextPanel(label, inputField);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Add padding for clarity
        return panel;
    }

    // Helper method to create a button with specific label, background, and foreground color
    private JButton createButton(String labelText, Color backgroundColor, Color textColor) {
        JButton button = new JButton(labelText);
        button.setFont(new Font("Arial", Font.PLAIN, 14));  // Font styling for button
        button.setFocusPainted(false);  // Remove default focus border
        button.setBackground(backgroundColor);  // Set button background color
        button.setForeground(textColor);  // Set button text color
        return button;
    }


    // Add listeners for username input field
    private void addUsernameListener() {
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
    }

    // Add listeners for password input field
    private void addPasswordListener() {
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

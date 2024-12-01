package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
        title.setFont(new Font("Roboto", Font.BOLD, 28));  // Set font size and bold
        title.setAlignmentX(Component.CENTER_ALIGNMENT); // Center title alignment

        // Add vertical spacing before the title to match the SignupView
        this.add(Box.createVerticalStrut(40));  // Adjust the value (40) for more or less space

        // Create the input panels for username and password
        final LabelTextPanel usernameInfo = createLabelTextPanel("Username:", usernameInputField);
        final LabelTextPanel passwordInfo = createLabelTextPanel("Password:", passwordInputField);

        usernameInputField.setPreferredSize(new java.awt.Dimension(250, 35));  // Wider and taller input field
        usernameInputField.setFont(new Font("Arial", Font.PLAIN, 16));  // Larger text in input field
        usernameInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10)); // Add padding for clarity
        usernameInfo.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));  // Left margin for the entire panel


        passwordInputField.setPreferredSize(new java.awt.Dimension(250, 35));  // Wider and taller input field
        passwordInputField.setFont(new Font("Arial", Font.PLAIN, 16));  // Larger text in input field
        passwordInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10)); // Add padding for clarity

        // Add red error message for username validation (centered)
        usernameErrorField.setFont(new Font("Arial", Font.ITALIC, 16));
        usernameErrorField.setForeground(Color.RED);
        usernameErrorField.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        cancel.addActionListener(evt -> {
            if (evt.getSource().equals(cancel)) {
                loginController.switchToSignUpView();
            }
        });  // Action listener for the button

        // Add listeners for username and password input fields
        addUsernameListener();
        addPasswordListener();

        // Set layout of the main panel and add components
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Add padding around the form
        this.setBackground(new Color(240, 240, 240)); // Light gray background for the login form
        this.add(title);
        this.add(Box.createVerticalStrut(20)); // Spacing after the title
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(Box.createVerticalStrut(-10)); // Spacing between username and password fields
        this.add(passwordInfo);
        this.add(Box.createVerticalStrut(20)); // Spacing before buttons
        this.add(buttons);
    }
    private LabelTextPanel createLabelTextPanel(String labelText, JTextField inputField) {
        final JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size for labels
        inputField.setPreferredSize(new Dimension(250, 30)); // Wider input fields
        inputField.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger input text
        inputField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200))); // Subtle border

        LabelTextPanel panel = new LabelTextPanel(label, inputField);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10)); // Spacing between label and field
        return panel;
    }
    private JButton createButton(String labelText, Color backgroundColor, Color textColor) {
        JButton button = new JButton(labelText);
        button.setFont(new Font("Arial", Font.PLAIN, 14)); // Font styling
        button.setFocusPainted(false); // Remove default focus border
        button.setBackground(backgroundColor); // Button background color
        button.setForeground(textColor); // Button text color
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside button
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand
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

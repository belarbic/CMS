package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

/**
 * The View for the Signup Use Case.
 */
public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Sign Up";

    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private SignupController signupController;

    private final JButton signUp;
    private final JButton toLogin;

public SignupView(SignupViewModel signupViewModel) {
    this.signupViewModel = signupViewModel;
    this.signupViewModel.addPropertyChangeListener(this);

    // Title label styling and alignment
    final JLabel title = new JLabel("Sign Up");
    title.setFont(new Font("Roboto", Font.BOLD, 28));  // Set font size and bold
    title.setAlignmentX(Component.CENTER_ALIGNMENT); // Center title alignment

    // Add vertical spacing before the title to match the LoginView
    this.add(Box.createVerticalStrut(40));  // Adjust the value (40) for more or less space

    // Create the input panels for username, password, and repeat password
    final LabelTextPanel usernameInfo = createLabelTextPanel("Username:", usernameInputField);
    final LabelTextPanel passwordInfo = createLabelTextPanel("Password:", passwordInputField);
    final LabelTextPanel repeatPasswordInfo = createLabelTextPanel("Repeat Password:", repeatPasswordInputField);

    // Ensure the input fields are consistent in size and font
    usernameInputField.setPreferredSize(new java.awt.Dimension(250, 35));  // Wider and taller input field
    usernameInputField.setFont(new Font("Arial", Font.PLAIN, 16));  // Larger text in input field
    usernameInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10)); // Add padding for clarity

    passwordInputField.setPreferredSize(new java.awt.Dimension(250, 35));  // Wider and taller input field
    passwordInputField.setFont(new Font("Arial", Font.PLAIN, 16));  // Larger text in input field
    passwordInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10)); // Add padding for clarity
    passwordInfo.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));  // Left margin for the entire panel


    repeatPasswordInputField.setPreferredSize(new java.awt.Dimension(250, 35));  // Same size for repeat password
    repeatPasswordInputField.setFont(new Font("Arial", Font.PLAIN, 16));  // Larger text in input field
    repeatPasswordInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10)); // Add padding for clarity
    repeatPasswordInfo.setBorder(BorderFactory.createEmptyBorder(0, -45, 0, 0));  // Left margin for the entire panel
    // Add error message fields for validation if needed
    // (Add error message fields for username, password, etc.)

    // Create buttons panel and add buttons to it
    final JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center buttons with spacing

    // Sign Up button styling
    signUp = createButton("Sign Up", new Color(34, 193, 195), Color.WHITE);
    buttons.add(signUp);

    // To Login button styling
    toLogin = createButton("Login", new Color(204, 204, 204), Color.BLACK);
    buttons.add(toLogin);

    // Button action listeners
    signUp.addActionListener(evt -> {
        if (evt.getSource().equals(signUp)) {
            final SignupState currentState = signupViewModel.getState();
            signupController.execute(
                    currentState.getUsername(),
                    currentState.getPassword(),
                    currentState.getRepeatPassword()
            );
        }
    });

    toLogin.addActionListener(evt -> signupController.switchToLoginView());

    // Add listeners for username, password, and repeat password fields
    addUsernameListener();
    addPasswordListener();
    addRepeatPasswordListener();

    // Set layout of the main panel and add components
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Add padding around the form
    this.setBackground(new Color(240, 240, 240)); // Light gray background for the form

    // Add components to the main panel
    this.add(title);
    this.add(Box.createVerticalStrut(20)); // Spacing after the title
    this.add(usernameInfo);
    this.add(Box.createVerticalStrut(-10)); // Spacing between fields
    this.add(passwordInfo);
    this.add(Box.createVerticalStrut(-10)); // Spacing between fields
    this.add(repeatPasswordInfo);
    this.add(Box.createVerticalStrut(20)); // Spacing before buttons
    this.add(buttons);
}

    private LabelTextPanel createLabelTextPanel(String labelText, JTextField inputField) {
        final JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size for labels
        inputField.setPreferredSize(new Dimension(250, 35)); // Set consistent width and height for input fields
        inputField.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger text in input field
        inputField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200))); // Subtle border

        LabelTextPanel panel = new LabelTextPanel(label, inputField);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10)); // Add padding for clarity between label and input
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






    private void addUsernameListener() {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                signupViewModel.setState(currentState);
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

    private void addPasswordListener() {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                signupViewModel.setState(currentState);
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

    private void addRepeatPasswordListener() {
        repeatPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(new String(repeatPasswordInputField.getPassword()));
                signupViewModel.setState(currentState);
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

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setSignupController(SignupController controller) {
        this.signupController = controller;
    }
}

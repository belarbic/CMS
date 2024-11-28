package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;

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

//    public SignupView(SignupViewModel signupViewModel) {
//        this.signupViewModel = signupViewModel;
//        signupViewModel.addPropertyChangeListener(this);
//
//        final JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        final LabelTextPanel usernameInfo = new LabelTextPanel(
//                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
//        final LabelTextPanel passwordInfo = new LabelTextPanel(
//                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
//        final LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
//                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
//
//        final JPanel buttons = new JPanel();
//        toLogin = new JButton(SignupViewModel.TO_LOGIN_BUTTON_LABEL);
//        buttons.add(toLogin);
//        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
//        buttons.add(signUp);
//
//        signUp.addActionListener(
//                // This creates an anonymous subclass of ActionListener and instantiates it.
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(signUp)) {
//                            final SignupState currentState = signupViewModel.getState();
//
//                            signupController.execute(
//                                    currentState.getUsername(),
//                                    currentState.getPassword(),
//                                    currentState.getRepeatPassword()
//                            );
//                        }
//                    }
//                }
//        );
//
//        toLogin.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        signupController.switchToLoginView();
//                    }
//                }
//        );
//
//        addUsernameListener();
//        addPasswordListener();
//        addRepeatPasswordListener();
//
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        this.add(title);
//        this.add(usernameInfo);
//        this.add(passwordInfo);
//        this.add(repeatPasswordInfo);
//        this.add(buttons);
//    }
public SignupView(SignupViewModel signupViewModel) {
    this.signupViewModel = signupViewModel;
    signupViewModel.addPropertyChangeListener(this);

    final JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
    title.setFont(new Font("Arial", Font.BOLD, 24));  // Set font size and bold
    title.setAlignmentX(Component.CENTER_ALIGNMENT); // Center title alignment
    this.add(Box.createVerticalStrut(50));

    final LabelTextPanel usernameInfo = new LabelTextPanel(
            new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
    usernameInputField.setPreferredSize(new java.awt.Dimension(250, 30));  // Increase width and height
    usernameInputField.setFont(new Font("Arial", Font.PLAIN, 16));  // Increase font size
    usernameInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Add padding for clarity

    final LabelTextPanel passwordInfo = new LabelTextPanel(
            new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
    passwordInputField.setPreferredSize(new java.awt.Dimension(250, 30));  // Increase width and height
    passwordInputField.setFont(new Font("Arial", Font.PLAIN, 16));  // Increase font size
    passwordInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Add padding for clarity

    final LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
            new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
    repeatPasswordInputField.setPreferredSize(new java.awt.Dimension(250, 30));  // Increase width and height
    repeatPasswordInputField.setFont(new Font("Arial", Font.PLAIN, 16));  // Increase font size
    repeatPasswordInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Add padding for clarity

    final JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center the buttons with spacing

    signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
    signUp.setFont(new Font("Arial", Font.PLAIN, 14));  // Font styling for button
    signUp.setFocusPainted(false);  // Remove default focus border
    signUp.setBackground(new Color(34, 193, 195));  // Light green background
    signUp.setForeground(Color.WHITE); // White text color
    buttons.add(signUp);

    toLogin = new JButton(SignupViewModel.TO_LOGIN_BUTTON_LABEL);
    toLogin.setFont(new Font("Arial", Font.PLAIN, 14));  // Font styling for button
    toLogin.setFocusPainted(false);  // Remove default focus border
    toLogin.setBackground(new Color(204, 204, 204));  // Light grey background
    toLogin.setForeground(Color.BLACK); // Black text color
    buttons.add(toLogin);

    signUp.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource().equals(signUp)) {
                final SignupState currentState = signupViewModel.getState();
                signupController.execute(
                        currentState.getUsername(),
                        currentState.getPassword(),
                        currentState.getRepeatPassword()
                );
            }
        }
    });

    toLogin.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            signupController.switchToLoginView();
        }
    });

    // Add listeners for input fields
    addUsernameListener();
    addPasswordListener();
    addRepeatPasswordListener();

    // Set layout of the main panel
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // Add components to the main panel
    this.add(title);
    this.add(usernameInfo);
    this.add(passwordInfo);
    this.add(repeatPasswordInfo);
    this.add(buttons);
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

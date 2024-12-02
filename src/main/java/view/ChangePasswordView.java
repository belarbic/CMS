package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.chat_room.ChatRoomController;
import interface_adapter.edit_message.EditMessageViewModel;

/**
 * The View for changing the password.
 */
public class ChangePasswordView extends JPanel implements ActionListener, PropertyChangeListener {

    private ChangePasswordController changePasswordController;
    private ChangePasswordViewModel changePasswordViewModel;

    private JTextField usernameField;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JButton submitButton;
    private JButton cancelButton;
    private final String viewName = "Change Password";

    public ChangePasswordView(ChangePasswordViewModel changePasswordViewModel) {
        this.changePasswordViewModel = changePasswordViewModel;
        this.changePasswordViewModel.addPropertyChangeListener(this);


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title styling
        JLabel title = new JLabel("hello");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

        // Space between title and form
        add(Box.createVerticalStrut(30));

        // Username Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField = new JTextField(20);
        add(usernameLabel);
        add(usernameField);

        // Old Password Field
        JLabel oldPasswordLabel = new JLabel("Old Password:");
        oldPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        oldPasswordField = new JPasswordField(20);
        add(oldPasswordLabel);
        add(oldPasswordField);

        // New Password Field
        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newPasswordField = new JPasswordField(20);
        add(newPasswordLabel);
        add(newPasswordField);

        // Submit and Cancel buttons
        JPanel buttonsPanel = new JPanel();
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        buttonsPanel.add(submitButton);
        buttonsPanel.add(cancelButton);
        add(buttonsPanel);

        // Set panel properties
        setPreferredSize(new Dimension(400, 300));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand.equals("Submit")) {
            String username = usernameField.getText();
            String oldPassword = new String(oldPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());

            // Call controller to handle password change
            changePasswordController.execute(newPassword, username);

            // Optionally, show success message
            JOptionPane.showMessageDialog(this, "Password changed successfully!");
        } else if (actionCommand.equals("Cancel")) {
            // Handle cancel action (e.g., go back to the previous screen)
            JOptionPane.showMessageDialog(this, "Password change canceled.");
        }
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
}


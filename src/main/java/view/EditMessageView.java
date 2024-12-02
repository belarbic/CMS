
package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.edit_message.EditMessageController;
import interface_adapter.edit_message.EditMessageState;
import interface_adapter.edit_message.EditMessageViewModel;

/**
 * The View for editing messages in the program.
 */
public class EditMessageView extends JPanel implements ActionListener, PropertyChangeListener {

    static final int EDIT_BORDER = 5;
    static final int MAIN_BORDER = 10;
    static final int ERROR_MESSAGE_COLOR = 255;
    static final int WIDTH = 400;
    static final int HEIGHT = 300;

    private final String viewName = "Edit Message";
    private final EditMessageViewModel editMessageViewModel;

    private final JLabel timestampLabel = new JLabel();
    private final JLabel senderLabel = new JLabel();
    private final JTextArea originalMessageArea = new JTextArea(3, 30);
    private final JTextArea contentInputArea = new JTextArea(3, 30);
    private final JLabel editErrorField = new JLabel();

    private final JButton save;
    private final JButton cancel;
    private EditMessageController editMessageController;

    /**
     * Creates a new EditMessageView.
     * @param editMessageViewModel the view model for edit message functionality
     */
    public EditMessageView(EditMessageViewModel editMessageViewModel) {
        this.editMessageViewModel = editMessageViewModel;
        this.editMessageViewModel.addPropertyChangeListener(this);

        // Title label styling and alignment
        final JLabel title = new JLabel("Edit Message");
        title.setFont(new Font("Roboto", Font.BOLD, 24));  // Set font size and bold
        title.setAlignmentX(Component.CENTER_ALIGNMENT); // Center title alignment

        // Original message panel
        final JPanel originalMessagePanel = new JPanel();
        originalMessagePanel.setLayout(new BoxLayout(originalMessagePanel, BoxLayout.Y_AXIS));
        originalMessagePanel.setBorder(BorderFactory.createTitledBorder("Original Message"));
        originalMessagePanel.setPreferredSize(new Dimension(350, 200)); // Set a smaller size

// Message header panel
        final JPanel messageHeaderPanel = new JPanel();
        messageHeaderPanel.setLayout(new BoxLayout(messageHeaderPanel, BoxLayout.X_AXIS)); // Align horizontally
        messageHeaderPanel.add(timestampLabel);
        messageHeaderPanel.add(senderLabel);

// Original message area setup
        originalMessageArea.setEditable(false);
        originalMessageArea.setLineWrap(true);
        originalMessageArea.setWrapStyleWord(true);
        originalMessageArea.setPreferredSize(new Dimension(300, 100)); // Smaller area size
        originalMessageArea.setBackground(new Color(245, 245, 245)); // Optional: set a different background color
        originalMessagePanel.add(messageHeaderPanel);
        originalMessagePanel.add(new JScrollPane(originalMessageArea));

// Edit panel
        final JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
        editPanel.setBorder(BorderFactory.createTitledBorder("New Message"));
        editPanel.setPreferredSize(new Dimension(350, 150)); // Set a smaller size

// Content input area setup
        contentInputArea.setLineWrap(true);
        contentInputArea.setWrapStyleWord(true);
        contentInputArea.setBorder(BorderFactory.createEmptyBorder(EDIT_BORDER, EDIT_BORDER, EDIT_BORDER, EDIT_BORDER));
        contentInputArea.setPreferredSize(new Dimension(300, 100)); // Smaller area size
        editPanel.add(contentInputArea);

// Buttons panel
        final JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS)); // Align buttons horizontally
        buttons.setPreferredSize(new Dimension(350, 50)); // Smaller button panel

// Save button
        save = new JButton("Save");
        save.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Modern, bold font
        save.setPreferredSize(new Dimension(150, 40)); // Smaller button width
        save.setFocusPainted(false); // Remove focus border
        save.setBackground(new Color(92, 184, 92)); // Light green color for the search button
        save.setForeground(Color.WHITE); // White text
        save.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Pointer cursor on hover
        buttons.add(save);

// Cancel button
        buttons.add(Box.createHorizontalStrut(20));

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Modern, bold font
        cancel.setPreferredSize(new Dimension(150, 40)); // Smaller button width
        cancel.setFocusPainted(false); // Remove focus border
        cancel.setBackground(new Color(255, 92, 92)); // Light red color for 'Cancel' button
        cancel.setForeground(Color.WHITE); // White text
        cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Pointer cursor on hover
        buttons.add(cancel);
// Adjust button spacing
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

// Panel setup: Adjust layout for overall form
        save.addActionListener(
                evt -> {
                    if (evt.getSource().equals(save)) {
                        final EditMessageState currentState = editMessageViewModel.getState();
                        editMessageController.execute(
                                currentState.getMessageId(),
                                contentInputArea.getText(),
                                currentState.getUsername()
                        );
                    }
                }
        );

//        cancel.addActionListener(this);

        cancel.addActionListener(evt -> {
            if (evt.getSource().equals(cancel)) {
                editMessageController.switchToLoggedInView();
            }
        });  // Action listener for the button

        // Error message
        editErrorField.setAlignmentX(Component.CENTER_ALIGNMENT);
        editErrorField.setForeground(new java.awt.Color(ERROR_MESSAGE_COLOR, 0, 0));

        // Main layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(MAIN_BORDER, MAIN_BORDER, MAIN_BORDER, MAIN_BORDER));

        this.add(title);
        this.add(originalMessagePanel);
        this.add(editPanel);
        this.add(editErrorField);
        this.add(buttons);

        // Set preferred size for the panel
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    /**
     * React to a change in the state of the application.
     * @param evt the PropertyChangeEvent to react to
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final EditMessageState state = (EditMessageState) evt.getNewValue();
        setFields(state);
        editErrorField.setText(state.getEditError());

        // Update message context
        timestampLabel.setText("[" + state.getTimestamp() + "]");
        senderLabel.setText(state.getUsername() + ": ");
        originalMessageArea.setText(state.getOriginalContent());
    }

    private void setFields(EditMessageState state) {
        contentInputArea.setText(state.getContent());
    }

    /**
     * Gives the name of the view.
     * @return the view name
     */
    public String getViewName() {
        return viewName;
    }
    public void setEditMessageController(EditMessageController editMessageController) {
        this.editMessageController = editMessageController;
    }
}

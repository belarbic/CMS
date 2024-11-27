
package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.w3c.dom.css.RGBColor;

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

    private final String viewName = "edit message";
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

        // Main title
        final JLabel title = new JLabel("Edit Message Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Original message panel
        final JPanel originalMessagePanel = new JPanel();
        originalMessagePanel.setLayout(new BoxLayout(originalMessagePanel, BoxLayout.Y_AXIS));
        originalMessagePanel.setBorder(BorderFactory.createTitledBorder("Original Message"));

        final JPanel messageHeaderPanel = new JPanel();
        messageHeaderPanel.add(timestampLabel);
        messageHeaderPanel.add(senderLabel);

        originalMessageArea.setEditable(false);
        originalMessageArea.setLineWrap(true);
        originalMessageArea.setWrapStyleWord(true);

        originalMessagePanel.add(messageHeaderPanel);
        originalMessagePanel.add(originalMessageArea);

        // Edit panel
        final JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
        editPanel.setBorder(BorderFactory.createTitledBorder("New Message"));

        contentInputArea.setLineWrap(true);
        contentInputArea.setWrapStyleWord(true);
        contentInputArea.setBorder(BorderFactory.createEmptyBorder(EDIT_BORDER, EDIT_BORDER, EDIT_BORDER, EDIT_BORDER));
        editPanel.add(contentInputArea);

        // Buttons panel
        final JPanel buttons = new JPanel();
        save = new JButton("Save Changes");
        buttons.add(save);
        cancel = new JButton("Cancel");
        buttons.add(cancel);

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

        cancel.addActionListener(this);

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

    /**
     * Sets the edit message controller.
     * @param controller the controller to set
     */
    public void setEditMessageController(EditMessageController controller) {
        this.editMessageController = controller;
    }
}

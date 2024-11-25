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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import interface_adapter.edit_message.EditMessageController;
import interface_adapter.edit_message.EditMessageState;
import interface_adapter.edit_message.EditMessageViewModel;

/**
 * The View for editing messages in the program.
 */
public class EditMessageView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "edit message";
    private final EditMessageViewModel editMessageViewModel;
    private final JTextField messageIdField = new JTextField(15);
    private final JTextArea messageContentArea = new JTextArea(5, 20);
    private final JLabel editErrorField = new JLabel();
    private final JButton saveButton;
    private final JButton cancelButton;
    private EditMessageController editMessageController;

    /**
     * Creates a new EditMessageView.
     * @param editMessageViewModel the view model
     */
    public EditMessageView(EditMessageViewModel editMessageViewModel) {
        this.editMessageViewModel = editMessageViewModel;
        this.editMessageViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Edit Message Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel messageIdPanel = new LabelTextPanel(
                new JLabel("Message ID"), messageIdField);

        final JPanel messageContentPanel = new JPanel();
        messageContentPanel.setLayout(new BoxLayout(messageContentPanel, BoxLayout.Y_AXIS));
        messageContentPanel.add(new JLabel("Message Content"));
        messageContentArea.setLineWrap(true);
        messageContentArea.setWrapStyleWord(true);
        messageContentPanel.add(new JScrollPane(messageContentArea));

        final JPanel buttons = new JPanel();
        saveButton = new JButton("Save");
        buttons.add(saveButton);
        cancelButton = new JButton("Cancel");
        buttons.add(cancelButton);

        saveButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(saveButton)) {
                        final EditMessageState currentState = editMessageViewModel.getState();
                        editMessageController.execute(
                                currentState.getMessageId(),
                                currentState.getContent(),
                                currentState.getUsername()
                        );
                    }
                }
        );

        cancelButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(messageIdPanel);
        this.add(messageContentPanel);
        this.add(editErrorField);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final EditMessageState state = (EditMessageState) evt.getNewValue();
        setFields(state);
        editErrorField.setText(state.getEditError());
    }

    private void setFields(EditMessageState state) {
        messageIdField.setText(state.getMessageId());
        messageContentArea.setText(state.getContent());
    }

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

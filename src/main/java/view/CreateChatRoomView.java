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
import interface_adapter.create_chatroom.CreateChatRoomState;
import interface_adapter.create_chatroom.CreateChatRoomViewModel;

/**
 * The View for when the user is creating a chatroom in the program.
 */
public class CreateChatRoomView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Create Chatroom";
    private final CreateChatRoomViewModel createChatRoomViewModel;

    private final JTextField nameInputField = new JTextField(15);
    private final JLabel nameErrorField = new JLabel();

    private final JButton createChatRoom;
    private final JButton cancel;
    private CreateChatRoomController createChatRoomController;

    public CreateChatRoomView(CreateChatRoomViewModel createChatRoomViewModel) {

        this.createChatRoomViewModel = createChatRoomViewModel;
        this.createChatRoomViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Create Chatroom");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Name"), nameInputField);

        final JPanel buttons = new JPanel();
        createChatRoom = new JButton("Create!");
        buttons.add(createChatRoom);
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        createChatRoom.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createChatRoom)) {
                            final CreateChatRoomState currentState = createChatRoomViewModel.getState();

                            createChatRoomController.execute(
                                    currentState.getName()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(this);

        nameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final CreateChatRoomState currentState = createChatRoomViewModel.getState();
                currentState.setName(nameInputField.getText());
                createChatRoomViewModel.setState(currentState);
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

        this.add(title);
        this.add(usernameInfo);
        this.add(nameErrorField);
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
        final CreateChatRoomState state = (CreateChatRoomState) evt.getNewValue();
        setFields(state);
        nameErrorField.setText(state.getCreateChatRoomError());
    }

    private void setFields(CreateChatRoomState state) {
        nameInputField.setText(state.getName());
    }

    public String getViewName() {
        return viewName;
    }

    public void setCreateChatRoomController(CreateChatRoomController createChatRoomController) {
        this.createChatRoomController = createChatRoomController;
    }
}

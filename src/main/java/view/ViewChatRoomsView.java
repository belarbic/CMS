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
import interface_adapter.view_chatrooms.ViewChatRoomsController;
import interface_adapter.view_chatrooms.ViewChatRoomsState;
import interface_adapter.view_chatrooms.ViewChatRoomsViewModel;

/**
 * The View for when the user is creating a chatroom in the program.
 */
public class ViewChatRoomsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "View Chatrooms";
    private final ViewChatRoomsViewModel viewChatRoomsViewModel;

    private final JButton cancel;
    private ViewChatRoomsController viewChatRoomsController;

    public ViewChatRoomsView(ViewChatRoomsViewModel viewChatRoomsViewModel) {

        this.viewChatRoomsViewModel = viewChatRoomsViewModel;
        this.viewChatRoomsViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("View Chatrooms");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel welcomeStatement = new JLabel("Here are the chatrooms you're a part of: ");

        final JPanel buttons = new JPanel();
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(welcomeStatement);
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
        final ViewChatRoomsState state = (ViewChatRoomsState) evt.getNewValue();
    }

    private void setFields() {
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewChatRoomsController(ViewChatRoomsController viewChatRoomsController) {
        this.viewChatRoomsController = viewChatRoomsController;
    }
}

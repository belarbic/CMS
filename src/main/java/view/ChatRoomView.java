package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import java.awt.Color;

import app.ChatService;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat_room.ChatRoomController;
import interface_adapter.chat_room.ChatRoomState;
import interface_adapter.chat_room.ChatRoomViewModel;

/**
 * The View for when the user is creating a chatroom in the program.
 */
public class ChatRoomView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Chatroom";
    private final ChatRoomViewModel chatRoomViewModel;

    private final JTextField nameInputField = new JTextField(15);
    private final JTextField messageInputField = new JTextField(15);
    private final JLabel nameErrorField = new JLabel();

    private final JButton chatRoom;
    private final JButton cancel;
    private ChatRoomController chatRoomController;
    private ViewManagerModel viewManagerModel;

    public ChatRoomView(ChatRoomViewModel chatRoomViewModel) {
        this.chatRoomViewModel = chatRoomViewModel;
        this.chatRoomViewModel.addPropertyChangeListener(this);

        // Title label styling and alignment
        final JLabel title = new JLabel("Chatroom");
        title.setFont(new Font("Roboto", Font.BOLD, 28));  // Set font size and bold
        title.setAlignmentX(Component.CENTER_ALIGNMENT); // Center title alignment

        // Add vertical spacing before the title to match the LoginView
        this.add(Box.createVerticalStrut(40));  // Adjust the value (40) for more or less space

        // Create the input panels for Chatroom Name and First Message
        final LabelTextPanel nameInfo = createLabelTextPanel("Chatroom Name:", nameInputField);
        final LabelTextPanel messageInfo = createLabelTextPanel("First Message:", messageInputField);

        // Add red error message for chatroom name validation (centered)
        nameErrorField.setFont(new Font("Arial", Font.ITALIC, 12));
        nameErrorField.setForeground(Color.RED);
        nameErrorField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ensure the input fields are consistent in size and font
        nameInputField.setPreferredSize(new java.awt.Dimension(250, 35));  // Wider and taller input field
        nameInputField.setFont(new Font("Arial", Font.PLAIN, 16));  // Larger text in input field
        nameInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10)); // Add padding for clarity

        messageInputField.setPreferredSize(new java.awt.Dimension(250, 35));  // Wider and taller input field
        messageInputField.setFont(new Font("Arial", Font.PLAIN, 16));  // Larger text in input field
        messageInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10)); // Add padding for clarity
        messageInfo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));  // Left margin for the entire panel

        // Create buttons panel and add buttons to it
        final JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center buttons with spacing

        // Create button styling
        chatRoom = createButton("Create!", new Color(34, 193, 195), Color.WHITE);
        buttons.add(chatRoom);

        // Cancel button styling
        cancel = createButton("Cancel", new Color(255, 92, 92), Color.WHITE);
        buttons.add(cancel);

        // Button action listeners
        chatRoom.addActionListener(evt -> {
            if (evt.getSource().equals(chatRoom)) {
                chatRoomController.execute(nameInputField.getText(), messageInputField.getText());
            }
        });

        cancel.addActionListener(evt -> {
            if (evt.getSource().equals(cancel)) {
                chatRoomController.switchToLoggedInView();
            }
        });

        // Document listeners for name and message input fields
//    addNameListener();
//    addMessageListener();

        // Set layout of the main panel and add components
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Add padding around the form
        this.setBackground(new Color(240, 240, 240)); // Light gray background for the form

        // Add components to the main panel
        this.add(title);
        this.add(Box.createVerticalStrut(20)); // Spacing after the title
        this.add(nameInfo);
        this.add(nameErrorField);
        this.add(Box.createVerticalStrut(-10)); // Spacing between name and message fields
        this.add(messageInfo);
        this.add(Box.createVerticalStrut(20)); // Spacing before buttons
        this.add(buttons);
    }

    private LabelTextPanel createLabelTextPanel(String labelText, JTextField inputField) {
        final JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size for labels
        inputField.setPreferredSize(new Dimension(250, 30)); // Wider input fields
        inputField.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger text in input field
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
    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ChatRoomState state = (ChatRoomState) evt.getNewValue();
        setFields(state);
        nameErrorField.setText(state.getChatRoomError());
    }

    private void setFields(ChatRoomState state) {
        nameInputField.setText(state.getName());
        messageInputField.setText(state.getFirstMessage());
    }

    public String getViewName() {
        return viewName;
    }

    public void setChatRoomController(ChatRoomController chatRoomController) {
        this.chatRoomController = chatRoomController;
    }
}

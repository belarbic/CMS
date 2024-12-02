
package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.search_message.SearchMessageController;
import interface_adapter.search_message.SearchMessageState;
import interface_adapter.search_message.SearchMessageViewModel;

/**
 * The View for searching messages in the program.
 */
public class SearchMessageView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "search message";
    private final SearchMessageViewModel searchMessageViewModel;
    private final JTextField keywordInputField = new JTextField(15);
    private final JLabel searchErrorField = new JLabel();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> resultsList;
    private final JButton search;
    private final JButton cancel;
    private SearchMessageController searchMessageController;

    /**
     * Creates a new SearchMessageView.
     * @param searchMessageViewModel the view model
     */
    public SearchMessageView(SearchMessageViewModel searchMessageViewModel) {
        this.searchMessageViewModel = searchMessageViewModel;
        this.searchMessageViewModel.addPropertyChangeListener(this);
        this.resultsList = new JList<>(listModel);

// Title styling
        final JLabel title = new JLabel("Search Messages Screen");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24)); // Bold and modern font
        title.setForeground(new Color(40, 40, 40)); // Dark gray text
        title.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
        this.add(title);

// Add space below the title
        this.add(Box.createVerticalStrut(90));

// Search info panel styling
        final LabelTextPanel searchInfo = new LabelTextPanel(
                new JLabel("Search Keyword"), keywordInputField);
        searchInfo.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment for the panel
        this.add(searchInfo);
        keywordInputField.setPreferredSize(new java.awt.Dimension(250, 35));  // Wider and taller input field
        keywordInputField.setFont(new Font("Arial", Font.PLAIN, 16));  // Larger text in input field
        searchInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10)); // Add padding for clarity
        searchInfo.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));  // Left margin for the entire panel

// Add space below the search info
        this.add(Box.createVerticalStrut(20));

// Search error field styling
        searchErrorField.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Plain font for errors
        searchErrorField.setForeground(new Color(255, 0, 0)); // Red color for error messages
        searchErrorField.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(searchErrorField);

// Add space below the error field
        this.add(Box.createVerticalStrut(20));

// Button panel styling
        final JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setBackground(new Color(240, 240, 240)); // Light background for the button panel

        search = new JButton("Search Messages");
        search.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Modern, bold font
        search.setPreferredSize(new Dimension(150, 40)); // Smaller button width
        search.setFocusPainted(false); // Remove focus border
        search.setBackground(new Color(92, 184, 92)); // Light green color for the search button
        search.setForeground(Color.WHITE); // White text
        search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Pointer cursor on hover

// Hover effect for the search button
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                search.setBackground(new Color(72, 164, 72)); // Darker green on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                search.setBackground(new Color(92, 184, 92)); // Original color
            }
        });

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Modern, bold font
        cancel.setPreferredSize(new Dimension(150, 40)); // Smaller button width
        cancel.setFocusPainted(false); // Remove focus border
        cancel.setBackground(new Color(255, 92, 92)); // Light red color for 'Cancel' button
        cancel.setForeground(Color.WHITE); // White text
        cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Pointer cursor on hover

        cancel.addActionListener(evt -> {
            if (evt.getSource().equals(cancel)) {
                searchMessageController.switchToLoggedInView();
            }
        });  // Action listener for the button

// Hover effect for the cancel button
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancel.setBackground(new Color(255, 70, 70)); // Darker red on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancel.setBackground(new Color(255, 92, 92)); // Original color
            }
        });

        buttons.add(Box.createHorizontalGlue()); // Add flexible space on the left
        buttons.add(search); // Add search button
        buttons.add(Box.createHorizontalStrut(20)); // Add space between buttons
        buttons.add(cancel); // Add cancel button
        buttons.add(Box.createHorizontalGlue()); // Add flexible space on the right
        this.add(buttons);

// Add space below the button panel
        this.add(Box.createVerticalStrut(20));

// Scroll pane styling
        final JScrollPane scrollPane = new JScrollPane(resultsList);
        scrollPane.setPreferredSize(new Dimension(300, 150)); // Set smaller dimensions for the scroll pane
        scrollPane.setMinimumSize(new Dimension(300, 150)); // Minimum size to prevent shrinking
        scrollPane.setMaximumSize(new Dimension(500, 200)); // Maximum size for flexibility
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultsList.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Modern font
        resultsList.setBackground(Color.WHITE); // White background for the list
        resultsList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding inside the list
        this.add(scrollPane);

// Add optional glue to push everything upwards or downwards
        this.add(Box.createVerticalGlue());

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SearchMessageState state = (SearchMessageState) evt.getNewValue();
        setFields(state);
        searchErrorField.setText(state.getSearchError());

        listModel.clear();
        if (state.getMessages() != null) {
            for (var message : state.getMessages()) {
                listModel.addElement(String.format("[%s] %s: %s",
                        message.getTimestamp(),
                        message.getSender(),
                        message.getContent()));
            }
        }
    }

    private void setFields(SearchMessageState state) {
        keywordInputField.setText(state.getKeyword());
    }

    public String getViewName() {
        return viewName;
    }

    /**
     * Sets the search message controller.
     * @param controller the controller to set
     */
    public void setSearchMessageController(SearchMessageController controller) {
        this.searchMessageController = controller;
    }
}

package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import interface_adapter.search_message.SearchMessageController;
import interface_adapter.search_message.SearchMessageViewModel;

/**
 * A Swing-based implementation of the search message view.
 * Provides a user interface for searching messages and displaying results.
 */
public class SearchMessageView extends JFrame implements MessageView {

    static final int COLUMN_SIZE = 20;
    static final int J_FRAME_WIDTH = 600;
    static final int J_FRAME_HEIGHT = 400;

    private final JTextField searchInputField;
    private final JButton searchButton;
    private final JTextArea searchResultsArea;
    private final JLabel errorLabel;

    private final SearchMessageController searchMessageController;
    private final SearchMessageViewModel viewModel;

    /**
     * Constructs a SearchMessageView with the specified controller and ViewModel.
     *
     * @param searchMessageController The controller for handling search input.
     * @param viewModel               The ViewModel for storing and retrieving search results.
     */
    public SearchMessageView(SearchMessageController searchMessageController, SearchMessageViewModel viewModel) {
        this.searchMessageController = searchMessageController;
        this.viewModel = viewModel;

        // Set up the JFrame
        this.setTitle("Search Messages");
        this.setSize(J_FRAME_WIDTH, J_FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        final JPanel searchPanel = new JPanel();
        searchInputField = new JTextField(COLUMN_SIZE);
        searchButton = new JButton("Search");
        searchPanel.add(new JLabel("Keyword:"));
        searchPanel.add(searchInputField);
        searchPanel.add(searchButton);

        errorLabel = new JLabel();
        errorLabel.setForeground(java.awt.Color.RED);

        // Results area
        searchResultsArea = new JTextArea();
        searchResultsArea.setEditable(false);
        final JScrollPane resultsScrollPane = new JScrollPane(searchResultsArea);

        // Add components to frame
        this.add(searchPanel, BorderLayout.NORTH);
        this.add(resultsScrollPane, BorderLayout.CENTER);
        this.add(errorLabel, BorderLayout.SOUTH);

        // Add listeners
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearch();
            }
        });
    }

    /**
     * Handles the search button click event.
     * Sends the keyword to the controller and updates the view with results or errors.
     */
    private void handleSearch() {
        final String keyword = searchInputField.getText();
        searchMessageController.handleSearchInput(keyword);

        // Display results
        final List<String> results = viewModel.getFormattedMessages();
        if (!results.isEmpty()) {
            showSearchResults(results);
        }
        else {
            showNoResults();
        }

        // Display errors
        if (viewModel.getError() != null) {
            showError(viewModel.getError());
        }
    }

    @Override
    public void showSearchResults(List<String> searchResults) {
        searchResultsArea.setText("");
        for (String result : searchResults) {
            searchResultsArea.append(result + "\n");
        }
        errorLabel.setText("");
    }

    @Override
    public void showNoResults() {
        searchResultsArea.setText("No results found.");
        errorLabel.setText("");
    }

    @Override
    public void showError(String errorMessage) {
        errorLabel.setText(errorMessage);
        searchResultsArea.setText("");
    }

    @Override
    public void showUpdatedMessage(String updatedMessage) {
        throw new UnsupportedOperationException("SearchMessageView does not support this operation.");
    }

    @Override
    public void showEditStatus(boolean isSuccess, String message) {
        throw new UnsupportedOperationException("SearchMessageView does not support this operation.");
    }

    /**
     * Starts the view by making it visible.
     */
    public void start() {
        this.setVisible(true);
    }
}


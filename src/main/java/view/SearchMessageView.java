
package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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

        final JLabel title = new JLabel("Search Messages Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel searchInfo = new LabelTextPanel(
                new JLabel("Search Keyword"), keywordInputField);

        final JPanel buttons = new JPanel();
        search = new JButton("Search Messages");
        buttons.add(search);
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        final JScrollPane scrollPane = new JScrollPane(resultsList);

        search.addActionListener(
                evt -> {
                    if (evt.getSource().equals(search)) {
                        final SearchMessageState currentState = searchMessageViewModel.getState();
                        searchMessageController.execute(
                                currentState.getKeyword(),
                                currentState.getUsername()
                        );
                    }
                }
        );

        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(searchInfo);
        this.add(searchErrorField);
        this.add(buttons);
        this.add(scrollPane);
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

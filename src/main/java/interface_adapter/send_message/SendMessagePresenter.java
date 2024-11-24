package interface_adapter.send_message;

import java.util.List;

import entity.Message;
import view.MessageView;

/**
 * The presenter for the send message use case.
 */
public class SendMessagePresenter {
    private final MessageView view;
    private final SendMessageViewModel viewModel;

    public SendMessagePresenter(MessageView view, SendMessageViewModel viewModel) {
        this.view = view;
        this.viewModel = viewModel;
    }

    /**
     * Handles success in sending a message by updating the ViewModel and notifying the view.
     *
     * @param message The message that was successfully sent.
     */
    public void presentMessageSuccess(Message message) {
        viewModel.addMessage(message);
        view.showUpdatedMessage(viewModel.getMessages().get(viewModel.getMessages().size() - 1));
    }

    /**
     * Handles failure in sending a message.
     *
     * @param errorMessage The error message to display.
     */
    public void presentMessageFailure(String errorMessage) {
        view.showError(errorMessage);
    }

    /**
     * Displays search results using the ViewModel.
     *
     * @param messages The list of messages from the search results.
     */
    public void presentSearchResults(List<Message> messages) {
        if (messages.isEmpty()) {
            view.showNoResults();
        }
        else {
            viewModel.updateMessages(messages);
            view.showSearchResults(viewModel.getMessages());
        }
    }

}

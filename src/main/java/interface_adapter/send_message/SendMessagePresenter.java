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
     * Updates the ViewModel and View for a successful send operation.
     *
     * @param message The message that was successfully sent.
     */
    public void presentMessageSuccess(Message message) {
        // Update the ViewModel state
        viewModel.getState().setContent(message.getContent());
        viewModel.getState().setSenderUsername(message.getSender().getName());
        viewModel.getState().setTimestamp(message.getTimestamp().toString());
        viewModel.getState().setMessageSent(true);
        viewModel.getState().setSendError(null);

        // Notify the view
        view.showUpdatedMessage(viewModel.getState().getContent());
    }

    /**
     * Updates the ViewModel and View for a failed send operation.
     *
     * @param errorMessage The error message describing the failure.
     */
    public void presentMessageFailure(String errorMessage) {
        // Update the ViewModel state
        viewModel.getState().setSendError(errorMessage);
        viewModel.getState().setMessageSent(false);

        // Notify the view
        view.showError(errorMessage);
    }

}

package interface_adapter.send_message;

import use_case.send_message.SendMessageOutputBoundary;
import use_case.send_message.SendMessageOutputData;
import view.MessageView;


/**
 * The presenter for the send message use case.
 */
public class SendMessagePresenter implements SendMessageOutputBoundary {
    private final MessageView view;
    private final SendMessageViewModel viewModel;

    /**
     * Constructs a new SendMessagePresenter.
     *
     * @param view      The view to update.
     * @param viewModel The view model to update.
     */
    public SendMessagePresenter(MessageView view, SendMessageViewModel viewModel) {
        this.view = view;
        this.viewModel = viewModel;
    }

    @Override
    public void presentSuccessView(SendMessageOutputData outputData) {
        // Update ViewModel state
        viewModel.getState().setContent(outputData.getContent());
        viewModel.getState().setSenderUsername(outputData.getSenderUsername());
        viewModel.getState().setTimestamp(outputData.getTimestamp());
        viewModel.getState().setMessageSent(true);
        viewModel.getState().setSendError(null);

        // Notify the view
        view.showUpdatedMessage(viewModel.getState().getContent());
    }

    @Override
    public void presentFailView(String errorMessage) {
        // Update ViewModel state
        viewModel.getState().setSendError(errorMessage);
        viewModel.getState().setMessageSent(false);

        // Notify the view
        view.showError(errorMessage);
    }
}

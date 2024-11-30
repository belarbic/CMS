package interface_adapter.delete_message;

import use_case.delete_message.DeleteMessageOutputBoundary;
import use_case.delete_message.DeleteMessageOutputData;

/**
 * Presenter for deleting messages.
 */
public class DeleteMessagePresenter implements DeleteMessageOutputBoundary {
    private final DeleteMessageViewModel viewModel;

    public DeleteMessagePresenter(DeleteMessageViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentSuccessView(DeleteMessageOutputData outputData) {
        viewModel.getState().setMessageId(outputData.getMessageId());
        viewModel.getState().setMessageContent(outputData.getContent());
        viewModel.getState().setDeleteSuccess(true);
        viewModel.getState().setErrorMessage(null);
    }

    @Override
    public void presentFailView(String errorMessage) {
        viewModel.getState().setDeleteSuccess(false);
        viewModel.getState().setErrorMessage(errorMessage);
    }
}

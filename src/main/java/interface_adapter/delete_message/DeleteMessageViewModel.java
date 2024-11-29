package interface_adapter.delete_message;

import interface_adapter.ViewModel;

/**
 * The View Model for the Delete Message View.
 */
public class DeleteMessageViewModel extends ViewModel<DeleteMessageState> {

    /**
     * Creates a new DeleteMessageViewModel.
     */
    public DeleteMessageViewModel() {
            super("delete message");
            setState(new DeleteMessageState());
        }
}

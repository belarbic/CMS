package interface_adapter.send_message;

import interface_adapter.ViewModel;

/**
 * The View Model for the Send Message View.
 */
public class SendMessageViewModel extends ViewModel<SendMessageState> {

    /**
     * Creates a new SendMessageViewModel.
     */
    public SendMessageViewModel() {
        super("send message");
        setState(new SendMessageState());
    }
}

package use_case.view_chatrooms;

import entity.ChatRoom;
import entity.User;

/**
 * The CreateChatRoom Interactor.
 */
public class ViewChatRoomsInteractor implements ViewChatRoomsInputBoundary {
    private final ViewChatRoomsUserDataAccessInterface userDataAccessObject;
    private final ViewChatRoomsOutputBoundary viewChatRoomsPresenter;

    public ViewChatRoomsInteractor(ViewChatRoomsUserDataAccessInterface userDataAccessInterface,
                                   ViewChatRoomsOutputBoundary viewChatRoomsOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.viewChatRoomsPresenter = viewChatRoomsOutputBoundary;
    }

    @Override
    public void execute(ViewChatRoomsInputData viewChatRoomsInputData) {
        final ViewChatRoomsOutputData viewChatRoomsOutputData = new ViewChatRoomsOutputData(false);
        viewChatRoomsPresenter.prepareSuccessView(viewChatRoomsOutputData);
    }
    public void switchToLoggedInView() {
        viewChatRoomsPresenter.switchToLoggedInView();
    }
}

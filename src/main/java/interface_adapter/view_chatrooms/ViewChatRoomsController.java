package interface_adapter.view_chatrooms;

import use_case.create_chatroom.CreateChatRoomInputBoundary;
import use_case.create_chatroom.CreateChatRoomInputData;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.view_chatrooms.ViewChatRoomsInputBoundary;
import use_case.view_chatrooms.ViewChatRoomsInputData;

/**
 * The controller for the Create ChatRoom Use Case.
 */
public class ViewChatRoomsController {

    private final ViewChatRoomsInputBoundary viewChatRoomsUseCaseInteractor;

    public ViewChatRoomsController(ViewChatRoomsInputBoundary viewChatRoomsUseCaseInteractor) {
        this.viewChatRoomsUseCaseInteractor = viewChatRoomsUseCaseInteractor;
    }

    /**
     * Executes the Create ChatRoom Use Case.
     */
    //     public void execute(String name) {
    public void execute() {
        final ViewChatRoomsInputData viewChatRoomsInputData = new ViewChatRoomsInputData();

        viewChatRoomsUseCaseInteractor.execute(viewChatRoomsInputData);
    }
    public void switchToLoggedInView() {
        viewChatRoomsUseCaseInteractor.switchToLoggedInView();
    }
    public void openChatRoom() {
        viewChatRoomsUseCaseInteractor.openChatRoom();
    }
}

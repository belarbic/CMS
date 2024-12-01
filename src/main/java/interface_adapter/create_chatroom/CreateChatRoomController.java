package interface_adapter.create_chatroom;

import use_case.create_chatroom.CreateChatRoomInputBoundary;
import use_case.create_chatroom.CreateChatRoomInputData;

/**
 * The controller for the Create ChatRoom Use Case.
 */
public class CreateChatRoomController {

    private final CreateChatRoomInputBoundary createChatRoomUseCaseInteractor;

    public CreateChatRoomController(CreateChatRoomInputBoundary createChatRoomUseCaseInteractor) {
        this.createChatRoomUseCaseInteractor = createChatRoomUseCaseInteractor;
    }

    /**
     * Executes the Create ChatRoom Use Case.
     * @param name the name of the chatRoom
     * @param firstMessage the first message in the chatRoom
     */
    public void execute(String name, String firstMessage) {
        final CreateChatRoomInputData createChatRoomInputData = new CreateChatRoomInputData(
                name, firstMessage);

        createChatRoomUseCaseInteractor.execute(createChatRoomInputData);
    }
    public void switchToLoggedInView() {
        createChatRoomUseCaseInteractor.switchToLoggedInView();
    }
}

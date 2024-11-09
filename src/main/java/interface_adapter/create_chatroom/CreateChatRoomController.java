package interface_adapter.create_chatroom;

import java.util.ArrayList;

import entity.Message;
import entity.User;
import use_case.create_chatroom.CreateChatRoomInputBoundary;
import use_case.create_chatroom.CreateChatRoomInputData;

/**
 * The controller for the Create ChatRoom Use Case.
 */
public class CreateChatRoomController {

    private final CreateChatRoomInputBoundary createChatroomUseCaseInteractor;

    public CreateChatRoomController(CreateChatRoomInputBoundary createChatroomUseCaseInteractor) {
        this.createChatroomUseCaseInteractor = createChatroomUseCaseInteractor;
    }

    /**
     * Executes the Create ChatRoom Use Case.
     * @param name the name of the chat room
     * @param participants the participants in the chat room
     * @param message the first message being sent in the chat room
     */
    public void execute(String name, ArrayList<User> participants, Message message) {
        final CreateChatRoomInputData createChatRoomInputData =
                new CreateChatRoomInputData(name, participants, message);

        createChatroomUseCaseInteractor.execute(createChatRoomInputData);
    }
}

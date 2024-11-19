package use_case.create_chatroom;

import entity.ChatRoom;
import entity.User;

/**
 * The CreateChatRoom Interactor.
 */
public class CreateChatRoomInteractor implements CreateChatRoomInputBoundary {
    private final CreateChatRoomUserDataAccessInterface userDataAccessObject;
    private final CreateChatRoomOutputBoundary createChatRoomPresenter;

    public CreateChatRoomInteractor(CreateChatRoomUserDataAccessInterface userDataAccessInterface,
                                    CreateChatRoomOutputBoundary createChatRoomOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.createChatRoomPresenter = createChatRoomOutputBoundary;
    }

    @Override
    public void execute(CreateChatRoomInputData createChatRoomInputData) {
        if (userDataAccessObject.getName().isEmpty() | userDataAccessObject.getName() == null) {
            createChatRoomPresenter.prepareFailView("ChatRoom needs a name!");
        }
        else {
            final CreateChatRoomOutputData createChatRoomOutputData = new CreateChatRoomOutputData(userDataAccessObject.getName(), false);
            createChatRoomPresenter.prepareSuccessView(createChatRoomOutputData);
        }
    }
}

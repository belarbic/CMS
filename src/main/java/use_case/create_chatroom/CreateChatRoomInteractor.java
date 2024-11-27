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
        final String name = createChatRoomInputData.getName();
        final String firstMessage = createChatRoomInputData.getFirstMessage();
        userDataAccessObject.setName(name);
        userDataAccessObject.setFirstMessage(firstMessage);
        if (userDataAccessObject.getName().isEmpty() || userDataAccessObject.getName() == null) {
            createChatRoomPresenter.prepareFailView("ChatRoom needs a name!");
        }
        else {
            final CreateChatRoomOutputData createChatRoomOutputData = new CreateChatRoomOutputData(userDataAccessObject.getName(), userDataAccessObject.getFirstMessage(), false);
            createChatRoomPresenter.prepareSuccessView(createChatRoomOutputData);
        }
    }
}

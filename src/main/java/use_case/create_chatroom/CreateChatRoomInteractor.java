package use_case.create_chatroom;

import app.ChatService;

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

        if (name == null || name.isEmpty()) {
            createChatRoomPresenter.prepareFailView("ChatRoom needs a name!");
            return; // Exit early if validation fails
        }

        ChatService chatService = new ChatService();
        System.out.println(name);
        chatService.addMessageListener(name);
        chatService.sendMessage(name, firstMessage, userDataAccessObject.getCurrentUsername());
        CreateChatRoomOutputData outputData = new CreateChatRoomOutputData(
                name,
                firstMessage,
                false
        );
        createChatRoomPresenter.prepareSuccessView(outputData);
    }
    public void switchToLoggedInView() {
        createChatRoomPresenter.switchToLoggedInView();
    }
}

package use_case.chat_room;

import app.ChatService;

public class ChatRoomInteractor implements ChatRoomInputBoundary {
    private final ChatRoomUserDataAccessInterface userDataAccessObject;
    private final ChatRoomOutputBoundary chatRoomPresenter;

    public ChatRoomInteractor(ChatRoomUserDataAccessInterface userDataAccessInterface,
                                    ChatRoomOutputBoundary chatRoomOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.chatRoomPresenter = chatRoomOutputBoundary;
    }

    @Override
    public void execute(ChatRoomInputData chatRoomInputData) {
        final String name = chatRoomInputData.getName();
        final String firstMessage = chatRoomInputData.getFirstMessage();

        ChatService chatService = new ChatService();
        chatService.getMessagesForChatRoom(name);

        if (name == null || name.isEmpty()) {
            chatRoomPresenter.prepareFailView("ChatRoom needs a name!");
            return; // Exit early if validation fails
        }
        ChatRoomOutputData outputData = new ChatRoomOutputData(
                name,
                firstMessage,
                false
        );
        chatRoomPresenter.prepareSuccessView(outputData);
    }
    public void switchToLoggedInView() {
        chatRoomPresenter.switchToLoggedInView();
    }

    @Override
    public void loadMessages(String chatRoomName) {

    }
}

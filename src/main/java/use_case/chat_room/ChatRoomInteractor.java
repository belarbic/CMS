package use_case.chat_room;

public class ChatRoomInteractor implements ChatRoomInputBoundary {
    private final ChatRoomUserDataAccessInterface userDataAccessObject;
    private final ChatRoomOutputBoundary chatRoomPresenter;
    private String chatRoomName;

    public ChatRoomInteractor(ChatRoomUserDataAccessInterface userDataAccessInterface,
                                    ChatRoomOutputBoundary chatRoomOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.chatRoomPresenter = chatRoomOutputBoundary;
    }

    @Override
    public void execute(ChatRoomInputData chatRoomInputData) {
        final String name = chatRoomInputData.getName();
        final String firstMessage = chatRoomInputData.getFirstMessage();
        chatRoomName = name;

//        ChatService chatService = new ChatService();
//        chatService.getMessagesForChatRoom(name).thenAccept(messages -> {
//            System.out.println("Received messages: ");
//            for (Map.Entry<String, Message> messageEntry : messages.entrySet()) {
//                System.out.println(messageEntry.getKey() + ": " + messageEntry.getValue().getContent());
//
//            }
//        });
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

    @Override
    public String getChatRoomName() {
        return chatRoomName;
    }

    @Override
    public String getUserName() {
        return userDataAccessObject.getCurrentUsername();
    }
}

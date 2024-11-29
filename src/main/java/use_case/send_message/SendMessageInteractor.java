package use_case.send_message;

import entity.ChatRoom;
import entity.Message;
import entity.User;

/**
 * Interactor for the send message use case.
 */
public class SendMessageInteractor implements SendMessageInputBoundary {
    private final SendMessageUserDataAccessInterface userDataAccessObject;
    private final SendMessageOutputBoundary outputBoundary;

    public SendMessageInteractor(SendMessageUserDataAccessInterface userDataAccessObject,
                                 SendMessageOutputBoundary outputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(SendMessageInputData inputData) {
        final String content = inputData.getContent();
        final String senderUsername = inputData.getSenderUsername();
        final String chatRoomName = inputData.getChatRoomName();

        if (content == null || content.trim().isEmpty()) {
            outputBoundary.presentFailView("Message content cannot be empty");
            return;
        }

        if (!userDataAccessObject.chatRoomExists(chatRoomName)) {
            outputBoundary.presentFailView("Chat room not found");
            return;
        }

        if (!userDataAccessObject.userExistsInChatRoom(senderUsername, chatRoomName)) {
            outputBoundary.presentFailView("Sender not found in chat room");
            return;
        }

        final ChatRoom chatRoom = userDataAccessObject.getChatRoomByName(chatRoomName);
        final User sender = userDataAccessObject.getUserInChatRoom(senderUsername, chatRoomName);

        final Message message = new Message(content, senderUsername);
        chatRoom.getMessages().add(message);

        final SendMessageOutputData outputData = new SendMessageOutputData(
                message.getContent(),
                sender.getName(),
                message.getTimestamp().toString()
        );
        outputBoundary.presentSuccessView(outputData);
    }
}

package interface_adapter.chat_room;

import entity.ChatRoom;
import use_case.chat_room.ChatRoomInputBoundary;
import use_case.chat_room.ChatRoomInputData;

/**
 * The controller for the ChatRoom Use Case.
 */
public class ChatRoomController {
    private final ChatRoomInputBoundary chatRoomUseCaseInteractor;

    public ChatRoomController(ChatRoomInputBoundary chatRoomUseCaseInteractor) {
        this.chatRoomUseCaseInteractor = chatRoomUseCaseInteractor;
    }

    /**
     * Executes the Create ChatRoom Use Case.
     * @param name the name of the chatRoom
     * @param firstMessage the first message in the chatRoom
     */
    public void execute(String name, String firstMessage) {
        final ChatRoomInputData chatRoomInputData = new ChatRoomInputData(
                name, firstMessage);

        chatRoomUseCaseInteractor.execute(chatRoomInputData);
    }
    public void switchToLoggedInView() {
        chatRoomUseCaseInteractor.switchToLoggedInView();
    }
    public void loadMessages(String chatRoomName) {
        chatRoomUseCaseInteractor.loadMessages(chatRoomName);
    }
    public String getChatRoomName() {
        return chatRoomUseCaseInteractor.getChatRoomName();
    }
    public String getUserName() {
        return chatRoomUseCaseInteractor.getUserName();
    }

}



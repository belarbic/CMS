package use_case.create_chatroom;

/**
 * The Input Data for the Create ChatRoom Use Case.
 */
public class CreateChatRoomInputData {

    private final String name;

    public CreateChatRoomInputData(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

}

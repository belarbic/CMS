package use_case.chat_room;

public class ChatRoomOutputData {
    private final String name;
    private final String firstMessage;
    private final boolean useCaseFailed;

    public ChatRoomOutputData(String name, String firstMessage, boolean useCaseFailed) {
        this.name = name;
        this.firstMessage = firstMessage;
        this.useCaseFailed = useCaseFailed;
    }

    public String getName() {
        return name;
    }

    public String getFirstMessage() {
        return firstMessage;
    }
}

package interface_adapter.search_message;

import java.util.ArrayList;

import entity.Message;

/**
 * The state for the Search Message View Model.
 */
public class SearchMessageState {
    private String keyword = "";
    private String searchError;
    private String username = "";
    private ArrayList<Message> messages = new ArrayList<>();

    public String getKeyword() {
        return keyword;
    }

    public String getSearchError() {
        return searchError;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}

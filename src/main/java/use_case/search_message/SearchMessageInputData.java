package use_case.search_message;

/**
 * Input Data for the Search Message Use Case.
 */
public class SearchMessageInputData {

    private final String keyword;
    private final String username;

    /**
     * Creates a new SearchMessageInputData.
     * @param keyword the search term to look for
     * @param username the username of the user performing the search
     */
    public SearchMessageInputData(String keyword, String username) {
        this.keyword = keyword;
        this.username = username;
    }

    String getKeyword() {
        return keyword;
    }

    String getUsername() {
        return username;
    }
}

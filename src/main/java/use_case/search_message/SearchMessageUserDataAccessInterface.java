package use_case.search_message;

import entity.User;

/**
 * Data Access Interface for the Search Message Use Case.
 */
public interface SearchMessageUserDataAccessInterface {

    /**
     * Checks if a user exists by username.
     * @param username the username to check
     * @return true if the user exists, false otherwise
     */
    boolean existsByName(String username);

    /**
     * Gets a user by username.
     * @param username the username to look up
     * @return the user with the given username
     */
    User get(String username);
}

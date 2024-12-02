package use_case.search_message;

import use_case.change_password.ChangePasswordInputData;

/**
 * The Change Password Use Case.
 */
public interface SearchMessageInputBoundary {

    /**
     * Execute the Change Password Use Case.
     * @param searchMessageInputData input data for this use case
     */
    void execute(SearchMessageInputData searchMessageInputData);

    void switchToLoggedInView();

    void switchToSearchMessageView();

}

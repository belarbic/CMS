package use_case.change_password;

import entity.User;
import entity.UserFactory;

/**
 * The Change Password Interactor.
 */
public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    private final ChangePasswordUserDataAccessInterface userDataAccessObject;
    private final ChangePasswordOutputBoundary changePasswordPresenter;
    private final UserFactory userFactory;

    public ChangePasswordInteractor(ChangePasswordUserDataAccessInterface changePasswordDataAccessInterface,
                                    ChangePasswordOutputBoundary changePasswordOutputBoundary, UserFactory userFactory) {
        this.userDataAccessObject = changePasswordDataAccessInterface;
        this.changePasswordPresenter = changePasswordOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(ChangePasswordInputData changePasswordInputData) {
        final User user = userFactory.create(changePasswordInputData.getUsername(),
                changePasswordInputData.getPassword());
        userDataAccessObject.changePassword(user);

        final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getName(),
                false);
        changePasswordPresenter.prepareSuccessView(changePasswordOutputData);
    }
    @Override
    public void switchToChangePasswordView() {
        changePasswordPresenter.switchToChangePasswordView();
    }
    public void switchToLoggedInView() {
        changePasswordPresenter.switchToLoggedInView();
    }
}
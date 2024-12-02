package interface_adapter.change_password;

/**
 * The state for the Create ChatRoom View Model.
 */
public class ChangePasswordState {

    private String username = "";
    private String oldPassword = "";
    private String newPassword = "";
    private String confirmPassword = "";
    private String passwordError;
    private String changeError;


    public String getUsername() {
        return username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getChangeError() {
        return changeError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setChangeError(String changeError) {
        this.changeError = changeError;
    }
//    public void setChangePasswordState(String nameError) {
//        this.changePasswordState = nameError;
//    }
}






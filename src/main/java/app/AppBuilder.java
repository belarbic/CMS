package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.FirebaseUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.create_chatroom.CreateChatRoomController;
import interface_adapter.create_chatroom.CreateChatRoomPresenter;
import interface_adapter.create_chatroom.CreateChatRoomViewModel;
import interface_adapter.edit_message.EditMessageController;
import interface_adapter.edit_message.EditMessagePresenter;
import interface_adapter.edit_message.EditMessageViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.search_message.SearchMessageController;
import interface_adapter.search_message.SearchMessagePresenter;
import interface_adapter.search_message.SearchMessageViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.view_chatrooms.ViewChatRoomsController;
import interface_adapter.view_chatrooms.ViewChatRoomsPresenter;
import interface_adapter.view_chatrooms.ViewChatRoomsViewModel;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.create_chatroom.CreateChatRoomInputBoundary;
import use_case.create_chatroom.CreateChatRoomInteractor;
import use_case.create_chatroom.CreateChatRoomOutputBoundary;
import use_case.edit_message.EditMessageInputBoundary;
import use_case.edit_message.EditMessageInteractor;
import use_case.edit_message.EditMessageOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.search_message.SearchMessageInputBoundary;
import use_case.search_message.SearchMessageInteractor;
import use_case.search_message.SearchMessageOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.CreateChatRoomView;
import view.EditMessageView;
import view.LoggedInView;
import view.LoginView;
import view.SearchMessageView;
import view.SignupView;
import view.ViewManager;
import use_case.view_chatrooms.ViewChatRoomsInputBoundary;
import use_case.view_chatrooms.ViewChatRoomsInteractor;
import use_case.view_chatrooms.ViewChatRoomsOutputBoundary;
import view.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
//    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
    private final FirebaseUserDataAccessObject userDataAccessObject = new FirebaseUserDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private CreateChatRoomView createChatRoomView;
    private CreateChatRoomViewModel createChatRoomViewModel;
    private ViewChatRoomsView viewChatRoomsView;
    private ViewChatRoomsViewModel viewChatRoomsViewModel;
    private SearchMessageView searchMessageView;
    private SearchMessageViewModel searchMessageViewModel;
    private EditMessageView editMessageView;
    private EditMessageViewModel editMessageViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     *
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     *
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     *
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds the CreateChatRoom View to the application.
     *
     * @return this builder
     */
    public AppBuilder addCreateChatRoomView() {
        createChatRoomViewModel = new CreateChatRoomViewModel();
        createChatRoomView = new CreateChatRoomView(createChatRoomViewModel);
        cardPanel.add(createChatRoomView, createChatRoomView.getViewName());
        return this;
    }

    /**
     * Adds the ViewChatRooms View to the application.
     * @return this builder
     */
    public AppBuilder addViewChatRoomsView() {
        viewChatRoomsViewModel = new ViewChatRoomsViewModel();
        viewChatRoomsView = new ViewChatRoomsView(viewChatRoomsViewModel);
        cardPanel.add(viewChatRoomsView, viewChatRoomsView.getViewName());
        return this;
    }

    /**
     * Adds the SearchMessage View to the application.
     * @return this builder
     */
    public AppBuilder addSearchMessageView() {
        searchMessageViewModel = new SearchMessageViewModel();
        searchMessageView = new SearchMessageView(searchMessageViewModel);
        cardPanel.add(searchMessageView, searchMessageView.getViewName());
        return this;
    }

    /**
     * Adds the EditMessage View to the application.
     *
     * @return this builder
     */
    public AppBuilder addEditMessageView() {
        editMessageViewModel = new EditMessageViewModel();
        editMessageView = new EditMessageView(editMessageViewModel);
        cardPanel.add(editMessageView, editMessageView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Create ChatRoom Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addCreateChatRoomUseCase() {
        final CreateChatRoomOutputBoundary createChatRoomOutputBoundary = new CreateChatRoomPresenter(viewManagerModel,
                loggedInViewModel, createChatRoomViewModel);
        final CreateChatRoomInputBoundary createChatRoomInteractor = new CreateChatRoomInteractor(
                userDataAccessObject, createChatRoomOutputBoundary);

        final CreateChatRoomController createChatRoomController =
                new CreateChatRoomController(createChatRoomInteractor);
        loggedInView.setCreateChatRoomController(createChatRoomController);
        return this;
    }

    /**
     * Adds the View ChatRoom Use Case to the application.
     * @return this builder
     */
    public AppBuilder addViewChatRoomsUseCase() {
        final ViewChatRoomsOutputBoundary viewChatRoomsOutputBoundary = new ViewChatRoomsPresenter(viewManagerModel,
                loggedInViewModel, viewChatRoomsViewModel);
        final ViewChatRoomsInputBoundary viewChatRoomsInteractor = new ViewChatRoomsInteractor(
                userDataAccessObject, viewChatRoomsOutputBoundary);

        final ViewChatRoomsController viewChatRoomsController = new ViewChatRoomsController(viewChatRoomsInteractor);
        loggedInView.setViewChatRoomsController(viewChatRoomsController);
        return this;
    }

    /**
     * Adds the Search Message Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSearchMessageUseCase() {
        final SearchMessageOutputBoundary searchMessageOutputBoundary = new SearchMessagePresenter(
                viewManagerModel,
                loggedInViewModel,
                searchMessageViewModel);

        final SearchMessageInputBoundary searchMessageInteractor = new SearchMessageInteractor(
                userDataAccessObject, searchMessageOutputBoundary);

        final SearchMessageController searchMessageController = new SearchMessageController(
                searchMessageInteractor);

        searchMessageView.setSearchMessageController(searchMessageController);
        loggedInView.setSearchMessageController(searchMessageController);
        return this;
    }

    /**
     * Adds the Edit Message Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addEditMessageUseCase() {
        final EditMessageOutputBoundary editMessageOutputBoundary = new EditMessagePresenter(
                viewManagerModel,
                loggedInViewModel,
                editMessageViewModel);

        final EditMessageInputBoundary editMessageInteractor = new EditMessageInteractor(
                userDataAccessObject, editMessageOutputBoundary);

        final EditMessageController editMessageController = new EditMessageController(
                editMessageInteractor);

        editMessageView.setEditMessageController(editMessageController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(loggedInViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     *
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Chat Messenger v1.0");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}


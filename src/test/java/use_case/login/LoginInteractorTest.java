package use_case.login;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    @Test
    void successTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add Paul to the data access repository before we log in
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToSignUpView() {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void successUserLoggedInTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add Paul to the data access repository before we log in
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", userRepository.getCurrentUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToSignUpView() {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        assertNull(userRepository.getCurrentUsername());

        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        LoginInputData inputData = new LoginInputData("Paul", "wrong");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add Paul to the data access repository before we log in, and the passwords should not match
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for \"Paul\".", error);
            }

            @Override
            public void switchToSignUpView() {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureUserDoesNotExistTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Paul: Account does not exist.", error);
            }

            @Override
            public void switchToSignUpView() {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureEmptyUsernameTest() {
        LoginInputData inputData = new LoginInputData("", "password");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Username cannot be empty.", error);
            }

            @Override
            public void switchToSignUpView() {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureEmptyPasswordTest() {
        LoginInputData inputData = new LoginInputData("Paul", "");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password cannot be empty.", error);
            }

            @Override
            public void switchToSignUpView() {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureEmptyUsernameAndPasswordTest() {
        LoginInputData inputData = new LoginInputData("", "");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(error.equals("Username cannot be empty.") || error.equals("Password cannot be empty."));
            }

            @Override
            public void switchToSignUpView() {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void testSignInFirebaseUserFailure() {
        // Simulating the response when Firebase returns a failure sign-in
        LoginInputData inputData = new LoginInputData("Paul", "wrongpassword");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        // This method would interact with Firebase, but we simulate a failure in the code logic
        LoginInteractor interactor = new LoginInteractor(userRepository, new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Unexpected success");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for \"Paul\".", error);
            }

            @Override
            public void switchToSignUpView() {

            }
        });

        interactor.execute(inputData);
    }
    @Test
    void failureNullUsernameAndPasswordTest() {
        LoginInputData inputData = new LoginInputData(null, null);
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(error.contains("Username cannot be empty"));
            }

            @Override
            public void switchToSignUpView() {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
    @Test
    void failureBlankUsernameTest() {
        LoginInputData inputData = new LoginInputData("    ", "password");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Username cannot be empty.", error);
            }

            @Override
            public void switchToSignUpView() {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
    @Test
    void failureNullPasswordTest() {
        LoginInputData inputData = new LoginInputData("Paul", null);
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password cannot be empty.", error);
            }

            @Override
            public void switchToSignUpView() {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
    @Test
    void failureIncorrectPasswordTest() {
        LoginInputData inputData = new LoginInputData("Paul", "wrongpassword");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for \"Paul\".", error);
            }

            @Override
            public void switchToSignUpView() {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
    @Test
    void multipleLoginAttemptsTest() {
        LoginInputData inputData1 = new LoginInputData("Paul", "password");
        LoginInputData inputData2 = new LoginInputData("Paul", "wrongpassword");

        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        // First login attempt should succeed
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Unexpected failure: " + error);
            }

            @Override
            public void switchToSignUpView() {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        interactor.execute(inputData1);

        // Second login attempt with wrong password should fail
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Unexpected success");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for \"Paul\".", error);
            }

            @Override
            public void switchToSignUpView() {

            }
        };

        interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData2);
    }
    @Test
    void validLoginWithFirebaseTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        // Assuming Firebase call is successful (this would be mocked for tests)
        LoginInteractor interactor = new LoginInteractor(userRepository, new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Unexpected failure: " + error);
            }

            @Override
            public void switchToSignUpView() {

            }
        });

        // Simulate successful Firebase sign-in
        boolean firebaseSignInResult = true;
        // You can mock the `signInFirebaseUser` method to return true here
        interactor.execute(inputData);
    }
    @Test
    void testSignInFirebaseUserSuccess() {
        // Prepare input data (valid credentials)
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add Paul to the repository before attempting login
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        // Simulating the behavior of Firebase returning a successful sign-in
        LoginInteractor interactor = new LoginInteractor(userRepository, new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", user.getUsername());  // Assert that the username returned matches
            }

            @Override
            public void prepareFailView(String error) {
                fail("Unexpected failure: " + error);  // Fail if we reach this point unexpectedly
            }

            @Override
            public void switchToSignUpView() {

            }
        });

        // Simulate the Firebase login and check that the correct username is returned
        interactor.execute(inputData);
    }
}

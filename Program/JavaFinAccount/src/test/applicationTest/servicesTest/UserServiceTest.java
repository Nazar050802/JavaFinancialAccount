package applicationTest.servicesTest;

import cz.cuni.mff.mozharon.financialaccounting.application.services.UserService;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.User;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.UserRepositoryInterface;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils.HashingUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test suite for UserService.
 */
public class UserServiceTest {

    @Mock
    private UserRepositoryInterface userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("Create user with valid data should return the created user")
    void createUser_WithValidData_ShouldReturnUser() throws NoSuchAlgorithmException, InvalidUserException {
        String loginName = "username";
        String password = "password123";
        String hashedLoginName = HashingUtility.sha256(loginName);
        String hashedPassword = HashingUtility.sha256(password);

        User user = new User(loginName, hashedLoginName, password, hashedPassword);

        when(userRepository.createUser(loginName, hashedLoginName, password, hashedPassword)).thenReturn(user);

        User createdUser = userService.createUser(loginName, password);

        assertNotNull(createdUser);
        assertEquals(loginName, createdUser.getLoginName());
        verify(userRepository).createUser(loginName, hashedLoginName, password, hashedPassword);
    }

    @Test
    @DisplayName("Check credentials should return true if credentials match")
    void checkCredentials_WithCorrectCredentials_ShouldReturnTrue() throws NoSuchAlgorithmException {
        User user = mock(User.class);
        String loginName = "user";
        String password = "pass";
        String hashedLogin = HashingUtility.sha256(loginName);
        String hashedPassword = HashingUtility.sha256(password);

        when(user.getHashedLoginName()).thenReturn(hashedLogin);
        when(user.getHashedPassword()).thenReturn(hashedPassword);
        when(userRepository.getUser()).thenReturn(user);

        assertTrue(userService.checkCredentials(loginName, password));
    }

    @Test
    @DisplayName("Check credentials should return false if credentials do not match")
    void checkCredentials_WithWrongCredentials_ShouldReturnFalse() throws NoSuchAlgorithmException {
        User user = mock(User.class);
        String loginName = "user";
        String password = "pass";
        String wrongPassword = "wrongpassword";
        String hashedLogin = HashingUtility.sha256(loginName);
        String hashedPassword = HashingUtility.sha256(password);

        when(user.getHashedLoginName()).thenReturn(hashedLogin);
        when(user.getHashedPassword()).thenReturn(hashedPassword);
        when(userRepository.getUser()).thenReturn(user);

        assertFalse(userService.checkCredentials(loginName, wrongPassword));
    }
}
package domainTest.entitiesTest;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.User;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() throws InvalidUserException {
        user = new User("username", "hashedUsername", "password", "hashedPassword");
    }

    @Test
    @DisplayName("Create user successfully")
    void createUserSuccessfully() {
        assertAll(
                () -> assertEquals("username", user.getLoginName()),
                () -> assertEquals("password", user.getPassword()),
                () -> assertEquals("hashedUsername", user.getHashedLoginName()),
                () -> assertEquals("hashedPassword", user.getHashedPassword())
        );
    }

    @Test
    @DisplayName("Exception for too long login name")
    void exceptionForTooLongLoginName() {
        InvalidUserException thrown = assertThrows(InvalidUserException.class, () ->
                new User("a".repeat(41), "hashedUsername", "password", "hashedPassword"));
        assertTrue(thrown.getMessage().contains("User login name length must be less than 40 symbols"));
    }

    @Test
    @DisplayName("Exception for too long password")
    void exceptionForTooLongPassword() {
        InvalidUserException thrown = assertThrows(InvalidUserException.class, () ->
                new User("username", "hashedUsername", "a".repeat(41), "hashedPassword"));
        assertTrue(thrown.getMessage().contains("User password length must be less than 40 symbols"));
    }

    @Test
    @DisplayName("Set and get new login name successfully")
    void setAndGetNewLoginName() throws InvalidUserException {
        user.setLoginName("newUsername");
        assertEquals("newUsername", user.getLoginName());
    }

    @Test
    @DisplayName("Set and get new password successfully")
    void setAndGetNewPassword() throws InvalidUserException {
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    @DisplayName("Exception when setting an empty hashed login name")
    void exceptionWhenSettingEmptyHashedLoginName() {
        InvalidUserException thrown = assertThrows(InvalidUserException.class, () ->
                user.setHashedLoginName(""));
        assertTrue(thrown.getMessage().contains("Hashed user login name length must be more than 0"));
    }

    @Test
    @DisplayName("Exception when setting an empty hashed password")
    void exceptionWhenSettingEmptyHashedPassword() {
        InvalidUserException thrown = assertThrows(InvalidUserException.class, () ->
                user.setHashedPassword(""));
        assertTrue(thrown.getMessage().contains("Hashed user password length must be more than 0"));
    }
}

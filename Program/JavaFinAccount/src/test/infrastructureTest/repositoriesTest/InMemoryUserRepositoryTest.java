package infrastructureTest.repositoriesTest;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.User;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for InMemoryUserRepository
 */
class InMemoryUserRepositoryTest {
    private InMemoryUserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
    }

    @Test
    @DisplayName("Create and retrieve a user")
    void testCreateAndRetrieveUser() throws InvalidUserException {
        User newUser = userRepository.createUser("user1", "hashedUser1", "pass123", "hashedPass123");
        assertNotNull(newUser, "The created user should not be null.");
        assertEquals("user1", newUser.getLoginName(), "Login name should match the provided value.");

        User retrievedUser = userRepository.getUser();
        assertNotNull(retrievedUser, "Retrieved user should not be null.");
        assertEquals(newUser, retrievedUser, "Retrieved user should be the same as the newly created user.");
    }

    @Test
    @DisplayName("Set and get user")
    void testSetAndGetUser() throws InvalidUserException {
        User user = new User("user2", "hashedUser2", "password", "hashedPassword");
        userRepository.setUser(user);
        User retrievedUser = userRepository.getUser();

        assertNotNull(retrievedUser, "The retrieved user should not be null.");
        assertEquals(user, retrievedUser, "The set and retrieved user should be the same.");
    }

    @Test
    @DisplayName("Handle null when no user is set")
    void testHandleNullWhenNoUserIsSet() {
        assertNull(userRepository.getUser(), "No user should be retrieved if none is set.");
    }
}

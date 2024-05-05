package infrastructureTest.serializationTest;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.User;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidUserException;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization.UserSerializer;
import cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils.HashingUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for UserSerializer.
 */
public class UserSerializerTest {
    private UserSerializer serializer;

    @BeforeEach
    void setUp() {
        serializer = new UserSerializer();
    }

    @Test
    @DisplayName("Serialize should return correct hashed format")
    void testSerialize() throws NoSuchAlgorithmException, InvalidUserException {
        User user = new User("username", HashingUtility.sha256("username"), "password", HashingUtility.sha256("password"));
        String expected = "USER|" + HashingUtility.sha256("username") + "|" + HashingUtility.sha256("password");

        String serializedData = serializer.serialize(user);
        assertEquals(expected, serializedData, "Serialized user should match the expected format with hashed values.");
    }

    @Test
    @DisplayName("Deserialize should return correct User object")
    void testDeserialize() throws InvalidUserException {
        String loginHash = "hashedLogin";
        String passwordHash = "hashedPassword";
        String data = "USER|" + loginHash + "|" + passwordHash;
        User user = serializer.deserialize(data);

        assertNotNull(user, "Deserialized user should not be null.");
        assertEquals("", user.getLoginName(), "Login name should be empty as it's not provided in deserialization.");
        assertEquals(loginHash, user.getHashedLoginName(), "Hashed login should match.");
        assertEquals("", user.getPassword(), "Password should be empty as it's not provided in deserialization.");
        assertEquals(passwordHash, user.getHashedPassword(), "Hashed password should match.");
    }
}
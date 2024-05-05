package infrastructureTest.utilsTest;

import cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils.HashingUtility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class HashingUtilityTest {

    @Test
    @DisplayName("SHA-256 hash of string")
    void testSha256Hashing() {
        String input = "test";

        // Expected SHA-256 hash of "test"
        String expectedOutput = "9F86D081884C7D659A2FEAA0C55AD015A3BF4F1B2B0B822CD15D6C15B0F00A08";
        assertEquals(expectedOutput.toLowerCase(), HashingUtility.sha256(input));
    }

    @Test
    @DisplayName("SHA-1 hash of string")
    void testSha1Hashing() {
        String input = "test";

        // Expected SHA-1 hash of "test"
        String expectedOutput = "A94A8FE5CCB19BA61C4C0873D391E987982FBBD3";
        assertEquals(expectedOutput.toLowerCase(), HashingUtility.sha1(input));
    }

    @Test
    @DisplayName("NoSuchAlgorithmException for SHA-256")
    void testSha256Exception() {
        try {
            // Changing the algorithm name to trigger NoSuchAlgorithmException
            MessageDigest.getInstance("SHA-256999");
            fail("NoSuchAlgorithmException expected but not thrown");
        } catch (NoSuchAlgorithmException e) {
        }
    }

    @Test
    @DisplayName("NoSuchAlgorithmException for SHA-1")
    void testSha1Exception() {
        try {
            // Changing the algorithm name to trigger NoSuchAlgorithmException
            MessageDigest.getInstance("SHA-1999");
            fail("NoSuchAlgorithmException expected but not thrown");
        } catch (NoSuchAlgorithmException e) {
        }
    }
}

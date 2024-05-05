package cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils;

/**
 * Provides utilities for data serialization.
 */
public class SerializerUtils {
    /**
     * Cleanses the field by replacing all '|' characters with a safe alternative
     * to avoid serialization errors due to delimiter conflicts.
     *
     * @param field the field to cleanse
     * @return the cleansed field
     */
    public static String cleanseField(String field) {
        return field.replace("|", "\\");
    }
}

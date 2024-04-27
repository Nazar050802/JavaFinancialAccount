package cz.cuni.mff.mozharon.financialaccounting.infrastructure.utils;

public class SerializerUtils {
    // Cleanses the field by replacing all '|' characters with a safe alternative
    public static String cleanseField(String field) {
        return field.replace("|", "\\");
    }
}

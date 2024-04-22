package cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization;

public class SerializerUtils {
    // Cleanses the field by replacing all '|' characters with a safe alternative
    static String cleanseField(String field) {
        return field.replace("|", "\\");
    }
}

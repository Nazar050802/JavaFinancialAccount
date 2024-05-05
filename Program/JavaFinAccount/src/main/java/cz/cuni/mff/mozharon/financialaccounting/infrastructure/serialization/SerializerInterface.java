package cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization;

public interface SerializerInterface<T> {
    /**
     * The keyword that identifies the type of object being serialized or deserialized.
     */
    String keyWord = "";
    /**
     * Serializes an object of type T into a string.
     *
     * @param data the object to serialize
     * @return the serialized string representation of the object
     * @throws Exception if serialization fails
     */
    String serialize(T data) throws Exception;
    /**
     * Deserializes a string into an object of type T.
     *
     * @param data the string to deserialize
     * @return the deserialized object of type T
     * @throws Exception if deserialization fails
     */
    T deserialize(String data) throws Exception;
}

package cz.cuni.mff.mozharon.financialaccounting.infrastructure.serialization;

import java.security.NoSuchAlgorithmException;

public interface SerializerInterface<T> {
    String keyWord = "";
    String serialize(T data) throws Exception;
    T deserialize(String data) throws Exception;
}

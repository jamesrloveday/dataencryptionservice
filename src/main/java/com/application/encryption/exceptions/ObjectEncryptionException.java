package com.application.encryption.exceptions;

/**
 * Created by james on 25/06/2017.
 */
public class ObjectEncryptionException extends Throwable {
    public ObjectEncryptionException(String s) {
        super(s);
    }

    public ObjectEncryptionException(Exception ex) {
        super(ex);
    }
}

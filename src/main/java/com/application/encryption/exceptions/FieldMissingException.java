package com.application.encryption.exceptions;

/**
 * Created by james on 22/06/2017.
 */
public class FieldMissingException extends Throwable {

    public FieldMissingException(String message) {
        super(message);
    }

    public FieldMissingException(String message, Throwable ex) {
        super(message, ex);
    }

    public FieldMissingException(FieldMissingException ex) {
        super(ex);
    }
}

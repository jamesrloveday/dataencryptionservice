/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.encryption.controller;


import com.application.encryption.encryption.AES;
import com.application.encryption.exceptions.FieldMissingException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author james
 *
 * Class Encryptor encrypts a String and returns the encrypted string
 *
 *
 */
public class Encryptor extends AbstractCipher {

    private static final Logger LOG = Logger.getLogger("EncryptorLogger");

    /**
     * Method ecryptStringVal
     *
     * @param s the input string value to encrypt
     * @param key the secret key to use or null
     * @return String in encrypted format
     * @throws FieldMissingException
     */
    public String encryptStringVal(String s, String key) throws FieldMissingException {
        if(key == null) {
            key = secretKey;
        }
        LOG.info("Attempting to encrypt a string: " + s);

        try {
            return AES.encrypt(s, key);
        } catch(FieldMissingException ex) {
            LOG.log(Level.INFO, "Attempt to encrypt the user has failed a field is missing", ex);
            throw new FieldMissingException(ex);
        }
    }
}

package com.application.encryption.controller;

import com.application.encryption.encryption.AES;

import java.util.logging.Logger;

/**
 * Created by james on 24/06/2017.
 *
 * Class to decrypt user data
 */
public class Decryptor extends AbstractCipher {

    private static final Logger LOG = Logger.getLogger("DecryptorLogger");

    /**
     * Method decryptStringVal decrypts to incoming string
     *
     * @param s the string value to decrypt
     * @param key the secret key to use or null
     * @return A string in decrypted format
     */
    public String decryptStringVal(String s, String key) {
        LOG.info("Attempting to decrypt string: " + s);
        if(key == null) {
            key = secretKey;
        }

        return AES.decrypt(s, key);
    }
}

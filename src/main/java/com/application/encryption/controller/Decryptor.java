package com.application.encryption.controller;


import com.application.encryption.encryption.AES;
import com.application.encryption.exceptions.ObjectDecryptionException;
import com.application.encryption.model.User;

import java.util.logging.Logger;

/**
 * Created by james on 24/06/2017.
 *
 * Class to decrypt user data
 */
public class Decryptor extends AbstractCipher {

    private static final Logger LOG = Logger.getLogger("DecryptorLogger");

    /**
     * Method getDecryptedUser
     *
     * Method takes a User object and decrypts each field according to the secret key used,
     * then returning the user.
     *
     * @param incomingUser
     * @param key
     * @return User with decrypted data
     * @throws ObjectDecryptionException
     */
    public User getDecryptedUser(User incomingUser, String key) throws ObjectDecryptionException {
        LOG.info("Starting to decrypt the user");
        if(key == null) {
            key = secretKey;
        }

        User user = null;
        if(incomingUser == null) {
            LOG.info("Nothing to decrypt, returning to caller");
            throw new ObjectDecryptionException("Cannot decrypt user when user is null");
        } else {
            user = incomingUser;
        }

        user.username = AES.decrypt(incomingUser.username, key);
        user.password = AES.decrypt(incomingUser.password, key);
        user.email = AES.decrypt(incomingUser.email, key);
        LOG.info("user decryption completed, returning to caller");
        return user;
    }
}

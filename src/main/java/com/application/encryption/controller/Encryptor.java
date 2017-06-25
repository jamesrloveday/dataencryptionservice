/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.encryption.controller;

import com.application.encryption.encryption.AES;
import com.application.encryption.exceptions.FieldMissingException;
import com.application.encryption.exceptions.ObjectEncryptionException;
import com.application.encryption.model.User;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author james
 *
 * Class Encryptor encrypts user data and returns a user with the fields encrypted
 *
 *
 */
public class Encryptor extends AbstractCipher {

    private static final Logger LOG = Logger.getLogger("EncryptorLogger");

    /**
     * Method encryptUserData takes a user object and encrypts each field according to the secret key, then
     * returning a user where each field is encrypted.
     *
     * @param incomingUser
     * @param key
     * @return A User object where each field is encrypted
     * @throws ObjectEncryptionException
     * @throws FieldMissingException
     */
    public User encryptUserData(User incomingUser,
                                           String key)
            throws ObjectEncryptionException, FieldMissingException {
        LOG.info("Starting user details encryption");

        if(key == null) {
            key = this.secretKey;
        }

        User user;
        if(incomingUser == null) {
            LOG.info("User is null nothing to encrypt");
            throw new ObjectEncryptionException("Cannot encrypt null data");
        } else {
            user = incomingUser;
        }

        try {
            user.username = AES.encrypt(incomingUser.username, key);
            user.password = AES.encrypt(incomingUser.password, key);
            user.email = AES.encrypt(incomingUser.email, key);
        } catch(FieldMissingException ex) {
            LOG.log(Level.INFO, "Attempt to encrypt the user has failed a field is missing " +
            user.toString(), ex);
            throw new FieldMissingException(ex);
        } catch(Exception ex) {
            LOG.log(Level.INFO, "An exception condition has occurred: ", ex);
            throw new ObjectEncryptionException(ex);
        }
        LOG.info("User encryption is complete returning to caller");
        return user;
    }


}

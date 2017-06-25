package com.application.encryption.controller;

import com.application.encryption.exceptions.FieldMissingException;
import com.application.encryption.exceptions.ObjectDecryptionException;
import com.application.encryption.exceptions.ObjectEncryptionException;
import com.application.encryption.model.User;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by james on 24/06/2017.
 */
@RunWith(JUnit4.class)
public class DecryptorTest {

    private Decryptor decryptor;
    private Encryptor encryptor;
    private User incomingUser;
    private User user;

    private String key;

    @Before
    public void setup() throws IOException, ObjectEncryptionException, FieldMissingException {
        Properties props = new Properties();
        props.load(new FileInputStream("src/test/resources/test.properties"));
        key = props.getProperty("secretKey");
        decryptor = new Decryptor();
        encryptor = new Encryptor();
        incomingUser = new User("testUser", "testPassword", "testEmail@testUser.com");
        incomingUser = encryptor.encryptUserData(incomingUser, key);
    }

    @Test
    public void expectUsernameIsDecrypted() throws ObjectDecryptionException {
        user = decryptor.getDecryptedUser(incomingUser, key);

        assertThat(user.username, is("testUser"));
    }

    @Test(expected = ObjectDecryptionException.class)
    public void expectExceptionCannotDecryptNull() throws ObjectDecryptionException {
        incomingUser = null;
        user = decryptor.getDecryptedUser(incomingUser, key);
    }

}

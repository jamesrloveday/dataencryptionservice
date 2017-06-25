package com.application.encryption.controller;

import com.application.encryption.exceptions.FieldMissingException;
import com.application.encryption.exceptions.ObjectEncryptionException;
import com.application.encryption.model.User;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by james on 18/06/2017.
 */
@RunWith(JUnit4.class)
public class EncryptorTest {

    private Encryptor encryptor;
    private User incomingUser;
    private User user;

    private String key;

    @Before
    public void setup() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("src/test/resources/test.properties"));
        key = props.getProperty("secretKey");
        encryptor = new Encryptor();
        incomingUser = new User("testUser", "testPassword", "testEmail@testUser.com");
    }

    @Test
    public void expectUserNameIsEncrypted() throws Exception, ObjectEncryptionException, FieldMissingException {

        user = encryptor.encryptUserData(incomingUser, key);

        assertThat(user.username, IsNot.not("testUser"));
    }

    @Test
    public void expectUserPasswordIsEncrypted() throws Exception, ObjectEncryptionException, FieldMissingException {
        user = encryptor.encryptUserData(incomingUser, key);

        assertThat(user.password, IsNot.not("testPassword"));
    }

    @Test
    public void expectUserEmailIsEncrypted() throws Exception, ObjectEncryptionException, FieldMissingException {
        user = encryptor.encryptUserData(incomingUser, key);

        assertThat(user.email, IsNot.not("testEmail@testUser.com"));
    }

    @Test(expected = FieldMissingException.class)
    public void expectExceptionUserNameIsNUll() throws ObjectEncryptionException, FieldMissingException {
        incomingUser = new User(null, "testPassword", "testEmail@testUser.com");

        user = encryptor.encryptUserData(incomingUser, key);
    }

    @Test(expected = FieldMissingException.class)
    public void expectExceptionUserNameIsEmpty() throws ObjectEncryptionException, FieldMissingException {
        incomingUser = new User("", "testPassword", "testEmail@testUser.com");

        user = encryptor.encryptUserData(incomingUser, key);

    }

    @Test(expected = FieldMissingException.class)
    public void expectExceptionPasswordIsNull() throws ObjectEncryptionException, FieldMissingException {
        incomingUser = new User("TestUser", null, "testEmail@testUser.com");

        user = encryptor.encryptUserData(incomingUser, key);
    }

    @Test(expected = FieldMissingException.class)
    public void expectExceptionPasswordIsEmpty() throws ObjectEncryptionException, FieldMissingException {
        incomingUser = new User("TestUser", "", "testEmail@testUser.com");

        user = encryptor.encryptUserData(incomingUser, key);
    }

    @Test(expected = ObjectEncryptionException.class)
    public void expectExceptionUserIsNull() throws ObjectEncryptionException, FieldMissingException {
        incomingUser = null;

        user = encryptor.encryptUserData(incomingUser, key);
    }

}

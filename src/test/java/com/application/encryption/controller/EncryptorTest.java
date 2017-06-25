package com.application.encryption.controller;

import com.application.encryption.exceptions.FieldMissingException;
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
    private String toEncrypt = "testString";

    private String key;

    @Before
    public void setup() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("src/test/resources/test.properties"));
        key = props.getProperty("secretKey");
        encryptor = new Encryptor();
    }

    @Test
    public void expectEncryptedString() throws FieldMissingException {
        String actual = encryptor.encryptStringVal(toEncrypt, key);

        assertThat(actual, IsNot.not("testString"));
    }

}

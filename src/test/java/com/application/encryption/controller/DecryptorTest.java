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
 * Created by james on 24/06/2017.
 */
@RunWith(JUnit4.class)
public class DecryptorTest {

    private Decryptor decryptor;
    private Encryptor encryptor;

    private String toDecrypt;
    private String toEncrypt = "testString";

    private String key;

    @Before
    public void setup() throws IOException, FieldMissingException {
        Properties props = new Properties();
        props.load(new FileInputStream("src/test/resources/test.properties"));
        key = props.getProperty("secretKey");
        decryptor = new Decryptor();
        encryptor = new Encryptor();

        toDecrypt = encryptor.encryptStringVal(toEncrypt, key);
    }

    @Test
    public void expectStringIsDecrypted() {
        String actual = decryptor.decryptStringVal(toDecrypt, key);

        assertThat(actual, IsNot.not(toDecrypt));
    }

}

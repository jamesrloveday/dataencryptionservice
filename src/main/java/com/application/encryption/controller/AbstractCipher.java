package com.application.encryption.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by james on 18/06/2017.
 */
public class AbstractCipher {

    public static String secretKey;

    static {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        secretKey = props.getProperty("secretKey");
    }
}

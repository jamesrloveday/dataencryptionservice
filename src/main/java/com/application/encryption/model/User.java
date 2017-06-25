package com.application.encryption.model;

/**
 * Created by james on 18/06/2017.
 */
public final class User {
    public String username;
    public String password;
    public String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

}

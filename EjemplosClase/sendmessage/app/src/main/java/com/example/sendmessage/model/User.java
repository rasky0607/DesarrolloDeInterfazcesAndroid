package com.example.sendmessage.model;

import androidx.annotation.NonNull;

public class User {
    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    private String user;
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return "usuario :"+user;
    }
}

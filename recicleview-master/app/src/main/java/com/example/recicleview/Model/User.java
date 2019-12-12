package com.example.recicleview.Model;

import androidx.annotation.NonNull;

public class User {
    //Campos
    private String name;
    private String email;

    //propiedades
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//Constructor
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {

        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

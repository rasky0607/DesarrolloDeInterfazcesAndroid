package com.example.sendmessage.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
/*
* Si se quiere usar el constructor vacio y hubiera otro constructor, se ha de implementar.
* Si no hay constructor, java lo crea por defecto.*/
//clase Pojo
/**
 * <h1>Clase Message</h1>
 * <p>Clase Pojo o Modelo</p>
 * <h2>Conceptos aprendidos</h2>
 * <ol>
 *     <li>Parcelable y Serializable</li>
 * </ol>
 * @author Pablo López Santana
 * @version 1.0
 * @see androidx.appcompat.app.AppCompatActivity
 * @see android.os.Bundle*/
public class Message implements Parcelable {
    private String author;
    private String message;

    //Constructor de la clase Parcelable
    protected Message(Parcel in) {
        author = in.readString();
        message = in.readString();
    }

//Implementacion de Parcelable
    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /*
    * Serializable: lo transforma en código máquina, pero en toda una cadena continua de 0 y 1.
    parcelo: Hace lo mismo que Serializable, pero separa que cadenas de 0 y 1 corresponde a cada dato
    (No se juntan todos en una misma ristra de 0 y 1,por lo que es más eficiente).
    * */
    //En que  orden pasamos las cadenas, para que pueda identificar a que propiedad pertenece cada trozo de las cadenas que va a enviar
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(message);
    }

    //Propiedades
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    //constructor
    public Message(String author, String message) {
        this.author = author;
        this.message = message;
    }

    @NonNull
    @Override
    public String toString() {
        return author+": "+ message;
    }


}

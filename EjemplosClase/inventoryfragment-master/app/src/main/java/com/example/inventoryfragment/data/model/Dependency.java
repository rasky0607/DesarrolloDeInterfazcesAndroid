package com.example.inventoryfragment.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Dependency implements Parcelable {

    public static final String TAG = "dependency";
    private String name;
    private String shortName;
    private String description;
    private String urlImagen;
    private  String inventory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }


    //Constructor
    public Dependency(String name, String shortName, String description,String inventiory, String urlImagen) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.inventory=inventiory;
        this.urlImagen = urlImagen;
    }


//Corta cada uno de los campos en bit(IMPORTANTE) en el mismo orden que se cortar se escriben
    protected  Dependency(Parcel in){
        name = in.readString();
        shortName=in.readString();
        description=in.readString();
        inventory=in.readString();
        urlImagen=in.readString();

    }

//TODO Aclarar funcion de este metodo
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(shortName);
        parcel.writeString(description);
        parcel.writeString(inventory);
        parcel.writeString(urlImagen);

    }

    public static final Creator<Dependency> CREATOR = new Creator<Dependency>() {
        @Override
        public Dependency createFromParcel(Parcel in) {
            return new Dependency(in);
        }

        @Override
        public Dependency[] newArray(int size) {
            return new Dependency[size];
        }
    };



}

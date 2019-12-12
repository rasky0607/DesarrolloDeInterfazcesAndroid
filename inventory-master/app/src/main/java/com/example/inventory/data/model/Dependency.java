package com.example.inventory.data.model;

public class Dependency {

    private String name;

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

    private String shortName;
    private String description;
    private String urlImagen;

    //Constructor
    public Dependency(String name, String shortName, String description, String urlImagen) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.urlImagen = urlImagen;
    }

    //Sobrescivir ToString

}

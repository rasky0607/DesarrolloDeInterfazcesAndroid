package com.example.customspinner.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

/*Clase que represan una red social, cuyo objetos se ordenan por el nombre/name(esto se llama orden natural)*/
public class Social implements Comparable<Social> {

    private  int icon;
    private  String name;
    /*[Ejemplo2 ordenacion] para ordenar por varios criterios para, debemos usar la interfaz comparator y
         crear una nueva clase donde definiremos los criterios de comparacion*/
    private  int totaluser;
    private  int dollars;

    //[Ejemplo2 ordenacion]
    //Constructor
    public Social(int icon, String name, int totaluser, int dollars) {
        this.icon = icon;
        this.name = name;
        this.totaluser = totaluser;
        this.dollars = dollars;
    }



    //Constructor
    public Social(int icon, String name) {
        this.icon = icon;
        this.name = name;

    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotaluser() {
        return totaluser;
    }

    public void setTotaluser(int totaluser) {
        this.totaluser = totaluser;
    }

    //[Ejemplo2 ordenacion]
    public int getDollars() {
        return dollars;
    }

    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    /**[Ejemplo1 ordenar] Tras implementar la interfaz Comparable<Social>  Este metodo se llama automaticamente cuando se tiene una lista/spinner de datos Social
    *Casos que se puden dar:
    * <0 -> El objeto es menor al objeto que se pasa por parametro
    * <0 -> El objeto es igual al objeto que se pasa por parametro
    * <' -> El objeto es mayor al objeto que se pasa por parametro
    *@param social
     * * @return
    */
    @Override
    public int compareTo(Social social) {
        return name.compareTo(social.name);
    }

    //[Ejemplo1]  Obligatorio  implementar el metodo Equeals para (ordenar de forma natural) para identificar cuando dos objetos social son iguales
    @Override
    public boolean equals(@Nullable Object obj) {
        return name.equals(((Social)obj).name);
    }

    /**[Ejemplo1]  Obligatorio sobreescribir  este metodo para (ordenar de forma natural),
     *  este dara un identificador unico a cada elemento de la lista, para poder identificar cada uno de ellos a la hora de comprarlos*/
    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }



}

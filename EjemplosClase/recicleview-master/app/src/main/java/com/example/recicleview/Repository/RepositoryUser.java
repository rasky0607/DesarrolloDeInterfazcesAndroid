package com.example.recicleview.Repository;

import com.example.recicleview.Model.User;

import java.util.ArrayList;
import java.util.List;

/**Clase Sigleton (por que solo tendremos una instancia de esta clase)
 *  que contiene  el listado de usuarios de nuestra app
 *  para aplicar el patron Sigleton :
 *  Constructor privado
 *  Un unico objeto de la propia clase*/
public class RepositoryUser {

    /*
    private  DAOSqlite;
    private  DAOFirebise;
    */

    //Objeto de la propia clase
    private static RepositoryUser instance;

    List<User> list;



    //Creacion del metodo getInstance() que devolvera la unica instancia de esta clase.
    //Al colocarlo  static  el contenido debe ser statico ( es decir  RepositoryUser instance; debe ser static)
    public static RepositoryUser getInstance(){
        if(instance==null)
            instance=new RepositoryUser();
        return instance;
    }

    //Constructor
    private  RepositoryUser(){
        //Instanciamos la lista
        list=new ArrayList<>();
        //Llenamos la lista
        Inicialice();
    }

    //Llena la lista de datos.
    public void  Inicialice(){

      list.add(new User("Lourdes","Moronlu@gmail.com"));
      list.add(new User("Marco","marco@gmail.com"));
      list.add(new User("Notredame","Notredame@gmail.com"));
      list.add(new User("Palotes","Palotes@gmail.com"));
      list.add(new User("Notredame","Notredame@gmail.com"));
      list.add(new User("Palotes","Palotes@gmail.com"));
      list.add(new User("Notredame","Notredame@gmail.com"));
      list.add(new User("Palotes","Palotes@gmail.com"));
      list.add(new User("Notredame","Notredame@gmail.com"));
      list.add(new User("Palotes","Palotes@gmail.com"));
      list.add(new User("Lourdes","Moronlu@gmail.com"));
      list.add(new User("Marco","marco@gmail.com"));
      list.add(new User("Notredame","Notredame@gmail.com"));
      list.add(new User("Palotes","Palotes@gmail.com"));
      list.add(new User("Notredame","Notredame@gmail.com"));
      list.add(new User("Palotes","Palotes@gmail.com"));
      list.add(new User("Notredame","Notredame@gmail.com"));
      list.add(new User("Palotes","Palotes@gmail.com"));
      list.add(new User("Notredame","Notredame@gmail.com"));
      list.add(new User("Palotes","Palotes@gmail.com"));
      list.add(new User("Lourdes","Moronlu@gmail.com"));
      list.add(new User("Marco","marco@gmail.com"));
      list.add(new User("Notredame","Notredame@gmail.com"));
      list.add(new User("Palotes","Palotes@gmail.com"));
      list.add(new User("Notredame","Notredame@gmail.com"));
      list.add(new User("Palotes","Palotes@gmail.com"));
      list.add(new User("Notredame","Notredame@gmail.com"));
      list.add(new User("Palotes","Palotes@gmail.com"));
      list.add(new User("Notredame","Notredame@gmail.com"));
      list.add(new User("Palotes","Palotes@gmail.com"));
    }

    /**Metodo que devuelve la lista de usuario*/
    public  List<User> getList(){
        return  list;
    }
}

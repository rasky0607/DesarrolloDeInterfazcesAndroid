package com.example.inventoryfragment.utils;

import java.util.regex.Pattern;
//Añadido
import android.util.Patterns;


/*Estas clases  no se pueden heredar, por eso se indican que son Final*/
public final class CommonUtils {

    /**Este metodo comprueba si este password cumple un patron dado*/
    public  static  boolean CheckPatternPassword(String password)
    {

        //FORMA LOURDES
        //patron constante con el que chekearemos un password
       //final String PATTER_PASSWORD="/^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,15}/";
        final String PATTER_PASSWORD="(^.)*(([A-Z])+(.*)([0-9])+)(.*$){8,15}";
        //final String PATTER_PASSWORD="[+[A-Z]][+[0-9]]]";
        Pattern pattern= Pattern.compile(PATTER_PASSWORD);
        //Comprobacion del password con el patron
        return pattern.matcher(password).matches();
        //Forma  rapida DAMIAN
       // password.matches(PATTER_PASSWORD);


    }

    public  static  boolean CheckPatternEmail(String email)
    {

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches())//si es correcto
            return  true;

        return  false;
    }



}

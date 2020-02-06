package com.pablolopez.intentservicedownload;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;

import java.io.File;

/**
 * Created by Ahsen Saeed on 11/1/2017.
 */
/**Crea un directorio en la SDK con el n ombr del proyecto, donde descargara los ficheros*/

public class DirectoryHelper extends ContextWrapper {

    public static final String ROOT_DIRECTORY_NAME = "IntentService";

    private DirectoryHelper(Context context) {
        super(context);
        createFolderDirectories();
    }

    public static void createDirectory(Context context) {
        new DirectoryHelper(context);
    }

    //metodo que comprueba si esta disponible la SD
    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(extStorageState);
    }
    //metodo que comprueba si la SD esta en modo solo lectura
    public static  boolean isExternalStoregeReadOnly(){
        String extStorageState = Environment.getExternalStorageState();
        return  Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState);
    }

    private void createFolderDirectories() {
        if (isExternalStorageAvailable())
            createDirectory(ROOT_DIRECTORY_NAME);
    }

    private void createDirectory(String directoryName) {
        if (!isDirectoryExists(directoryName)) {
            File file = new File(Environment.getExternalStorageDirectory(), directoryName);
            file.mkdir();
        }
    }

    private boolean isDirectoryExists(String directoryName) {
        File file = new File(Environment.getExternalStorageDirectory() + "/" + directoryName);
        return file.isDirectory() && file.exists();
    }
}
package es.pablolopez.InventoryJetPack;

import android.app.Application;

import es.pablolopez.InventoryJetPack.data.dao.InventoryDatabase;

public class InventoryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Crea la Base de datos
        InventoryDatabase.create(this);
    }
}

package es.pablolopez.InventoryJetPack;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import es.pablolopez.InventoryJetPack.data.dao.InventoryDatabase;

public class InventoryApplication extends Application {

    public  final static String CHANNEL_ID="1";
    @Override
    public void onCreate() {
        super.onCreate();
        //Crea la Base de datos
        InventoryDatabase.create(this);
        //Creamos la notificacion
        createNotificationChannel();
    }

    //Declaracion de canal de notificaciones
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}

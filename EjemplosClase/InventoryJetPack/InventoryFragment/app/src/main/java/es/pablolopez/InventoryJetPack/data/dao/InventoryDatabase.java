package es.pablolopez.InventoryJetPack.data.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.pablolopez.InventoryJetPack.data.model.Dependency;

@Database( entities = {Dependency.class},version = 1, exportSchema = false)
public abstract class InventoryDatabase extends RoomDatabase {

    /*Metodo que va devolver un objeto DependencyDao (como un get)
    * Importante indicar que es abstracto ya que si no, nos obliga aimplementar todos su metodos*/
    public abstract  DependencyDao dependencyDao();
/**Solucion de GOOGLE*/
        private static volatile InventoryDatabase INSTANCE;
        private static final int NUMBER_OF_THREADS = 4;
       public static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);

      public static InventoryDatabase getDatabase() {
            return INSTANCE;
        }

        /*Nuestra Solucion*/
    public static void create(final Context context) {
        if (INSTANCE == null) {
            synchronized (InventoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InventoryDatabase.class, "inventory")
                            .build();
                }
            }
        }

    }


}

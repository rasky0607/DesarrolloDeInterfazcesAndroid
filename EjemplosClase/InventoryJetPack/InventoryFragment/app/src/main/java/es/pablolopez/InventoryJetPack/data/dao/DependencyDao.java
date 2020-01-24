package es.pablolopez.InventoryJetPack.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import es.pablolopez.InventoryJetPack.data.model.Dependency;

/*Estamos indicando que es una clase accesible al resto de las clases*/
@Dao
public interface DependencyDao {
    @Insert(onConflict =OnConflictStrategy.IGNORE )// para ignorar cuando da excepcion(en este caso cuando insertamos dos dependencia con la mima clave)
    Long instert (Dependency dependency);
    @Delete
    void delete (Dependency dependency);
    @Update
    void update (Dependency dependency);
    @Query("SELECT * from dependency ORDER BY name ASC")
    List<Dependency> getAll();

    //findByShortName es lo que es en realidad el get de dependency o obtener una dependencia
    //En este caso vemos como ejemplo, como pasar un parametro del metodo a la etiqueta room usando la notacion =:
    @Query("SELECT * FROM dependency WHERE shortname=:shortName")
    Dependency findByShortName(String shortName);

}

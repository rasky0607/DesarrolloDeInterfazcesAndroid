package es.pablolopez.InventoryJetPack.layout.sector;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.pablolopez.InventoryJetPack.data.model.Sector;

@Dao
public interface SectorDao {
    @Insert
    void instert (Sector sector);
    @Delete
    void delete (Sector sector);
    @Update
    void update (Sector sector);

    @Query("SELECT * from sector ORDER BY name ASC")
    List<Sector> getAll();

    //findByShortName es lo que es en realidad el get de dependency o obtener una dependencia
    //En este caso vemos como ejemplo, como pasar un parametro del metodo a la etiqueta room usando la notacion =:
    @Query("SELECT * FROM dependency WHERE shortname=:shortName")
    Sector findByShortName(String shortName);

}

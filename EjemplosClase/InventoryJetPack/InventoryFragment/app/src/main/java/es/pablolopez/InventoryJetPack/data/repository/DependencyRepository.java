package es.pablolopez.InventoryJetPack.data.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.room.OnConflictStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import es.pablolopez.InventoryJetPack.data.dao.DependencyDao;
import es.pablolopez.InventoryJetPack.data.dao.InventoryDatabase;
import es.pablolopez.InventoryJetPack.data.model.Dependency;

public class DependencyRepository {
    private static DependencyRepository instance;
    //private ArrayList<Dependency> list;
    private DependencyDao dependencyDao;


    /*static {
        instance = new DependencyRepository();
    }*/

    public DependencyRepository() {
        //initialice();
        dependencyDao = InventoryDatabase.getDatabase().dependencyDao();
    }

   /* private void initialice() {
        list = new ArrayList<>();
        list.add(new Dependency("2º Ciclo de Grado Superior", "2ºCFGS", "Aula de informatica","2018","S"));
        list.add(new Dependency("1º Ciclo de Grado Superior", "1ºCFGS", "Aula de informatica","2019","S"));
        list.add(new Dependency("2º Ciclo de Grado Medio", "2ºCFGM", "Aula de informatica","2019","M"));
        list.add(new Dependency("1º Ciclo de Grado Medio", "1ºCFGM", "Aula de informatica","2020","M"));
        list.add(new Dependency("4º ESO", "4ºESO", "ESO","2020","E"));
        list.add(new Dependency("3º ESO", "3ºESO", "ESO","2019","E"));
        list.add(new Dependency("2º ESO", "2ºESO", "ESO","2019","E"));
        list.add(new Dependency("1º ESO", "1ºESO", "ESO","2019","E"));
        list.add(new Dependency("2º Bachillerato", "2ºBACH", "Bachillerato","2018","B"));
        list.add(new Dependency("1º Bachillerato", "1ºBACH", "Bachillerato","2018","B"));

    }*/

    public static DependencyRepository getInstance(){
        if (instance == null){
            instance = new DependencyRepository();
        }
        return instance;
    }

    //getAll o getList // ssummit para hacerlo rapido sin comprobaciones, AsynTask para  hacerlo con comprobaciones
    public List<Dependency> getDependencies(){
        try {
            //Este get Devuelve la lista una vez es obtenida despues de que finalice el summit(), si no fuera de este forma volveria un objeto Future
            //summit devuelve un valo a diferencia de execute
            return InventoryDatabase.databaseWriteExecutor.submit(() -> dependencyDao.getAll()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;

    }

    //Añadir en la Bd una dependencia
   public boolean add(final Dependency dependency) {
        //En estas sentencias Lambdas si usamos un "execute", podemos usar encolar varias sentencias de codigo en caso de "submit" NO.
       /**
        *   InventoryDatabase.databaseWriteExecutor.execute(()->dependencyDao.instert(dependency));
        *                                                       dependencyDao.update(dependency))*/
       InventoryDatabase.databaseWriteExecutor.execute(()->dependencyDao.instert(dependency));
       return true;
    }
    //Editar en la Bd una dependencia
    public boolean edit(Dependency dependency) {
        InventoryDatabase.databaseWriteExecutor.execute(()->dependencyDao.update(dependency));
        return true;
    }

    //Eliminar en la Bd una dependencia
    public boolean delete(Dependency dependency) {
        InventoryDatabase.databaseWriteExecutor.execute(()->dependencyDao.delete(dependency));
        return true;
    }

    public Dependency get(Dependency dependency) {
        return dependencyDao.findByShortName(dependency.getShortname());
    }

    public int getPositionDependency(Dependency dependency) {
        int i = 0;
        List<Dependency> list = dependencyDao.getAll();
        for (Dependency item : list){
            if (item.getShortname().equals(dependency.getShortname())){
                return i;
            }
            i++;
        }
        return -1;
    }

//Clase -Tarea asincrona interna para añadir hilo para las Query o consultas que devuelve toda la lista de dependencias
    private  class  QueryAsynTask extends AsyncTask<Void,Void,List<Dependency>>{
    //añadido  hilo - tarea para optener la lista de la BD de datos
        @Override
        protected List<Dependency> doInBackground(Void... voids) {
            return dependencyDao.getAll();
        }
    }

    //Clase-Tarea asincrona interna para añadir hilo para las Inserciones para insertar una nueva dependencias y o gestionar  el  resultado de esta
    private  class  InsertAsyncTasck extends AsyncTask<Dependency,Void,Long>{

        @Override
        protected Long doInBackground(Dependency... dependency) {
            Long result = dependencyDao.instert(dependency[0]);

            //Gestionamos el  resultado de la insercion en caso de devolver una excepcion que ignoramos.
            //Este -1 es de la definicion de sqlite, es lo que devuelve al lanzar un error  sqlite normalmente por contraint
            //Aborto entra en  los catch, pero ignore , solo entra en los finaly , en los dos casos devuelve  -1
            if(result==-1){
                dependencyDao.update(dependency[0]);
            }
            return null;
        }
    }

}

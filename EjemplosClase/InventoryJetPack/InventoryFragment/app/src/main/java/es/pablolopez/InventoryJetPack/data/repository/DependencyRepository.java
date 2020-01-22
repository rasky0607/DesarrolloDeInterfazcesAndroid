package es.pablolopez.InventoryJetPack.data.repository;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

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

    //getAll //TODO PENDIENTE
    public List<Dependency> getDependencies(){
        //return  dependencyDao.getAll();
      return  new QueryAsynTask().execute().get();

    }

    //Añadir
   public void add(final Dependency dependency) {
       InventoryDatabase.databaseWriteExecutor.execute(()->dependencyDao.instert(dependency));
    }
    //Editar
    public void edit(Dependency dependency) {
        InventoryDatabase.databaseWriteExecutor.execute(()->dependencyDao.update(dependency));
    }

    //Eliminar
    public void delete(Dependency dependency) {
        InventoryDatabase.databaseWriteExecutor.execute(()->dependencyDao.delete(dependency));
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


    private  class  QueryAsynTask extends AsyncTask<Void,Void,List<Dependency>>{

        @Override
        protected List<Dependency> doInBackground(Void... voids) {
            return dependencyDao.getAll();
        }
    }
}

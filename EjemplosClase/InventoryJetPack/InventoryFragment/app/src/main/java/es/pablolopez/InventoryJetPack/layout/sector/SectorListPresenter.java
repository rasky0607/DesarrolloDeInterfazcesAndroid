package es.pablolopez.InventoryJetPack.layout.sector;

import android.os.AsyncTask;

import java.util.ArrayList;

import es.pablolopez.InventoryJetPack.data.model.Sector;
import es.pablolopez.InventoryJetPack.data.repository.SectorRepository;

public class SectorListPresenter implements SectorListContract.Presenter {

    private SectorListContract.View view;

    public SectorListPresenter(SectorListContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        new AsyncTask<Void,Void, ArrayList<Sector>>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                view.showProgressBar();
            }

            @Override
            protected ArrayList<Sector> doInBackground(Void... voids) {
                try {
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    return SectorRepository.getInstance().getSectors();
                }
            }

            @Override
            protected void onPostExecute(ArrayList<Sector> sectors) {
                super.onPostExecute(sectors);
                view.hideProgressBar();
                if (sectors.isEmpty()){
                    view.showNoSectors("NO HAY SECTORES");
                }else {
                    view.refeshData(sectors);
                }
            }
        }.execute();
    }


    @Override
    public void deleteSector(Sector sector) {
        if (SectorRepository.getInstance().removeSector(sector)){
            view.showDeleteMessage("Sector Eliminado: "+sector.getShortname());
            view.onSuccessDelete(sector);
        }else {
            view.showError("No se ha podido eliminar el sector: "+sector.getShortname());
        }
    }

    @Override
    public void undoDelete(Sector sector) {
        if (SectorRepository.getInstance().addSector(sector)){
            view.refeshData(SectorRepository.getInstance().getSectors());
        }else {
            view.showError("No se ha podido recuperar");
        }
    }
}

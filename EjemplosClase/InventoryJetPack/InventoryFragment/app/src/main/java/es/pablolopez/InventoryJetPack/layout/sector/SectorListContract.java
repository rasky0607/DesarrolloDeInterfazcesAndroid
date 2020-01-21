package es.pablolopez.InventoryJetPack.layout.sector;

import java.util.ArrayList;

import es.pablolopez.InventoryJetPack.data.model.Sector;
import es.pablolopez.InventoryJetPack.layout.base.BaseView;

public interface SectorListContract{

    interface View extends BaseView<Presenter> {
        void showProgressBar();
        void hideProgressBar();
        void refeshData(ArrayList<Sector> sectors);
        void onSuccessDelete(Sector sector);
        void showNoSectors(String error);
        void showDeleteMessage(String message);
    }

    interface Presenter{
        void loadData();
        void deleteSector(Sector sector);
        void undoDelete(Sector sector);
    }
}

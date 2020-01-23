package es.pablolopez.InventoryJetPack.data.repository;

import java.util.ArrayList;

import es.pablolopez.InventoryJetPack.data.model.Sector;

public class SectorRepository {
    private static SectorRepository INSTANCE;

    private ArrayList<Sector> sectors;

    public static SectorRepository getInstance(){
        if (INSTANCE == null){
            INSTANCE = new SectorRepository();
        }
        return INSTANCE;
    }

    public SectorRepository() {
        sectors = new ArrayList<>();
        initialiceData();
    }

    private void initialiceData() {
        this.sectors.add(new Sector("Sector 1", "SC_1", "Sector_Desc_1",DependencyRepository.getInstance().getDependencies().get(1),"1"));
        this.sectors.add(new Sector("Sector 2", "SC_2", "Sector_Desc_2",DependencyRepository.getInstance().getDependencies().get(2),"2"));
        this.sectors.add(new Sector("Sector 3", "SC_3", "Sector_Desc_3",DependencyRepository.getInstance().getDependencies().get(3),"3"));
        this.sectors.add(new Sector("Sector 4", "SC_4", "Sector_Desc_4",DependencyRepository.getInstance().getDependencies().get(4),"4"));
        this.sectors.add(new Sector("Sector 5", "SC_5", "Sector_Desc_5",DependencyRepository.getInstance().getDependencies().get(5),"5"));
    }

    public ArrayList<Sector> getSectors(){
        return this.sectors;
    }

    public boolean addSector(Sector sector){
        return this.sectors.add(sector);
    }

    public boolean removeSector(Sector sector){
        return this.sectors.remove(sector);
    }

    public boolean edit(Sector sector) {
        for (Sector it : sectors) {
            if (it.getShortname().equals(sector.getShortname())) {
                it.setName(sector.getName());
                it.setDescription(sector.getDescription());
                return true;
            }
        }
        return false;
    }

}

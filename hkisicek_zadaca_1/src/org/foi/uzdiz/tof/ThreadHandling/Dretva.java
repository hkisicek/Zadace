package org.foi.uzdiz.tof.ThreadHandling;

import org.foi.uzdiz.tof.FileHandling.FilesBuilder;
import org.foi.uzdiz.tof.SystemHandling.Location;
import org.foi.uzdiz.tof.SystemHandling.LocationFactory;

import java.util.ArrayList;
import org.foi.uzdiz.tof.FileHandling.LocationsAdapter;

public class Dretva {

    private static final Dretva dretva = new Dretva();
    private ArrayList<Location> locations;
    private int sjeme;
    private String mjesta;
    private String senzori;
    private String aktuatori;
    private String algoritam;
    private int brojCiklusa;
    private int trajanjeCiklusa;
    private String exportFile;

    private Dretva(){}

    public static Dretva inicijalizirajDretvu() {
        return dretva;
    }
    
    public boolean init(){

        FilesBuilder filesBuilder = new FilesBuilder();
        System.out.println("Čitam datoteke...");
        this.locations = filesBuilder.preapareLocations(this.mjesta,this.senzori,this.aktuatori);
        return false;
    }

    public boolean pokreni(){
        System.out.println("Inicijaliziram sustav...");
        LocationFactory factory=new LocationFactory();
        
        return false;
    }

    public void setSjeme(int sjeme){
        this.sjeme = sjeme;
    }
    public void setMjesta(String mjesta){
        this.mjesta = mjesta;
    }
    public void setAktuatori(String aktuatori){
        this.aktuatori = aktuatori;
    }
    public void setAlgoritam(String algoritam){
        this.algoritam = algoritam;
    }
    public void setSenzori(String senzori){
        this.senzori = senzori;
    }
    public void setBrojCiklusa(int brojCiklusa){
        this.brojCiklusa = brojCiklusa;
    }
    public void setTrajanjeCiklusa(int trajanjeCiklusa){
        this.trajanjeCiklusa = trajanjeCiklusa;
    }
    public void setExportFile(String exportFile){
        this.exportFile = exportFile;
    }
}
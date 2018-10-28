/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datoteke;

/**
 *
 * @author helena
 */
public class DatotekaFactory {
    
      public Datoteka otvoriDatoteku(String datoteka, String naziv) {
        switch (naziv) {
            case "Izlaz":
                return IzlazDatoteka.getInstance();
            case "Aktuatori":
                return new AktuatorDatoteka(datoteka);
            case "Senzori":
                return new SenzorDatoteka(datoteka);
            case "Mjesta":
                return new MjestaDatoteka(datoteka);
            default:
                return null;
        }
    }  
}

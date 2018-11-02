/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkisicek_zadaca2018.Datoteke;

/**
 *
 * @author Helena
 */
public class DatotekaFactory {
     public Datoteka otvoriDatoteku(String nazivDatoteke, String tip) {
        switch (tip) {
            case "Aktuatori":
                return new ParametriDatoteka(nazivDatoteke);
            default:
                return null;
        }
    }  
}

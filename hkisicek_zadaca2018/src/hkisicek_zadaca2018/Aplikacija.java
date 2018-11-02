/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkisicek_zadaca2018;

import Utils.Konstante;
import hkisicek_zadaca2018.Datoteke.DatotekaFactory;


/**
 *
 * @author Helena
 */
public class Aplikacija {

    private static Aplikacija instance = null;
    private DatotekaFactory datotekaFactory = new DatotekaFactory();
    
    private String datotekaParametar;

    private Aplikacija(String datotekaParametar) {
        this.datotekaParametar = datotekaParametar;
    }

    public static Aplikacija instanciraj(String datotekaParametar) {
        if (instance == null) {
            instance = new Aplikacija(datotekaParametar);
        }
        return instance;
    }
    
    private void pokreniAplikaciju(){
        citajDatoteku(datotekaParametar, Konstante.DATOTEKA_PARAMETAR);
    }
    
    private void citajDatoteku(String nazivDatoteke, String tip){
        datotekaFactory.otvoriDatoteku(nazivDatoteke, tip);
    }
}

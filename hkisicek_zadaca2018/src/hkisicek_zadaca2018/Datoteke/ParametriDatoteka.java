/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkisicek_zadaca2018.Datoteke;

import Utils.Parametri;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Helena
 */
public class ParametriDatoteka implements Datoteka{

    private String nazivDatoteke;
    BufferedReader citac;
    
    public ParametriDatoteka(String nazivDatoteke){
        this.nazivDatoteke = nazivDatoteke;
        citaj();
    }
   
    @Override
    public void otvori() {
        try {
            citac = new BufferedReader(new FileReader(this.nazivDatoteke));
        } catch (FileNotFoundException e) {
            Logger.getLogger(ParametriDatoteka.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void citaj() {
        otvori();
        String red;
        try {
            red = citac.readLine();
            int brojac = 0;

            List<String> naziviDatoteka = new ArrayList<>();
            List<Integer> brojcaniParametri = new ArrayList<>();

            while (red != null) {
                String[] parametar = red.split(":");
                if (brojac < 4) {
                    naziviDatoteka.add(parametar[1]);
                }
                brojcaniParametri.add(Integer.parseInt(parametar[1]));
            }
            Parametri parametri = new Parametri(
                    naziviDatoteka.get(0),
                    naziviDatoteka.get(1),
                    naziviDatoteka.get(2),
                    naziviDatoteka.get(3),
                    brojcaniParametri.get(0),
                    brojcaniParametri.get(1),
                    brojcaniParametri.get(2),
                    brojcaniParametri.get(3),
                    brojcaniParametri.get(4),
                    brojcaniParametri.get(5),
                    brojcaniParametri.get(6),
                    brojcaniParametri.get(7),
                    brojcaniParametri.get(8),
                    brojcaniParametri.get(9),
                    brojcaniParametri.get(10),
                    brojcaniParametri.get(11),
                    brojcaniParametri.get(12),
                    brojcaniParametri.get(13),
                    brojcaniParametri.get(14),
                    brojcaniParametri.get(15),
                    brojcaniParametri.get(16),
                    brojcaniParametri.get(17),
                    brojcaniParametri.get(18),
                    brojcaniParametri.get(19),
                    brojcaniParametri.get(20),
                    brojcaniParametri.get(21),
                    brojcaniParametri.get(22)
            );
            zatvori();
        } catch (IOException ex) {
            Logger.getLogger(ParametriDatoteka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void zatvori() {
        try {
            citac.close();
        } catch (IOException e) {
            Logger.getLogger(ParametriDatoteka.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

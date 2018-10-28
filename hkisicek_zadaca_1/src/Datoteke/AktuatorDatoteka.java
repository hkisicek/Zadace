/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datoteke;

import Uređaji.Aktuator;
import hkisicek_zadaca_1.Mjesto;
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
 * @author helena
 */
public class AktuatorDatoteka implements Datoteka {

    private String imeDatoteke;
    List<Aktuator> aktuatori;
    BufferedReader reader;

    public AktuatorDatoteka(String imeDatoteke) {
        this.imeDatoteke = imeDatoteke;
    }

    @Override
    public void otvoriDatoteku() {
        try {
            reader = new BufferedReader(new FileReader(this.imeDatoteke));
        } catch (FileNotFoundException e) {
            Logger.getLogger(SenzorDatoteka.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void provjeriDatoteku() {
        String red;
        aktuatori = new ArrayList<>();
        try {
            Aktuator aktuator;
            red = reader.readLine();
            boolean prvaLinija = true;
            while (red != null) {

                String[] atributi = red.split(";");
                red = reader.readLine();
                if (prvaLinija) {
                    prvaLinija = false;
                } else {
                    if ((Integer.parseInt(atributi[1]) >= 0
                            && Integer.parseInt(atributi[1]) < 3
                            && Integer.parseInt(atributi[2]) >= 0
                            && Integer.parseInt(atributi[2]) < 4)) {

                        aktuator = new Aktuator(atributi[0],
                                Integer.parseInt(atributi[1]),
                                Integer.parseInt(atributi[2]),
                                Integer.parseInt(atributi[3]),
                                Integer.parseInt(atributi[4]));

                        aktuatori.add(aktuator);
                    }
                }
            }
        } catch (Exception e) {
           // Logger.getLogger(SenzorDatoteka.class.getName()).log(Level.SEVERE, null, e);
        }

        System.out.println("\n----Čitam datoteku aktuatora------");

        aktuatori.stream().map((s) -> {
            System.out.println("");
            return s;
        }).map((s) -> {
            System.out.println("Naziv: " + s.getNaziv()
                    + "\nTip: " + s.getTip()
                    + "\nVrsta: " + s.getVrsta()
                    + "\nMin vrijednost: " + s.getMinVrijednost()
                    + "\nMax vrijednost: " + s.getMaxVrijednost());
            return s;

        }).forEachOrdered((s) -> {

        });
    }

    @Override
    public void zatvoriDatoteku() {
        try {
            reader.close();
        } catch (IOException e) {
            Logger.getLogger(SenzorDatoteka.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void zapisi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> vratiProcitano() {
        otvoriDatoteku();
        provjeriDatoteku();
        zatvoriDatoteku();
        List<Object> listaAktuatora = new ArrayList<>(aktuatori);
        return listaAktuatora;
    }

    @Override
    public List<Mjesto> vratiMjesta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

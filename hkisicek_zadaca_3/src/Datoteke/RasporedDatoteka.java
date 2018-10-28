/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datoteke;

import Utils.Greske;
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
public class RasporedDatoteka implements Datoteka {

    private final String imeDatoteke;
    private List<Object> raspored;
    private List<String> zapisi;
    BufferedReader reader;

    public RasporedDatoteka(String imeDatoteke) {
        this.imeDatoteke = imeDatoteke;
        raspored = new ArrayList<>();
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
        zapisi = new ArrayList<>();
        int brojac = 0;
        try {
            red = reader.readLine();
            zapisi.add("ČITAM DATOTEKU RASPOREDA...");
            while (red != null) {
                brojac++;
                String[] atributi = red.split(";");
                if (brojac > 3) {
                    if (provjeriIspravnost(red).equals("Ispravno")) {
                        raspored.add(red);
                    } else {
                        zapisi.add(provjeriIspravnost(red) + " U retku: " + red);
                    }
                }
                red = reader.readLine();
            }
        } catch (IOException e) {
            Logger.getLogger(SenzorDatoteka.class.getName()).log(Level.SEVERE, null, e);
        }
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
    public List<Object> vratiProcitano() {
        this.otvoriDatoteku();
        this.provjeriDatoteku();
        this.zatvoriDatoteku();
        return raspored;
    }

    private String provjeriIspravnost(String red) {
        String[] atributi = red.split(";");
        //ako je raspored uredjaja po mjestima
        if (atributi[0].equals("0")) {
            if (atributi.length > 5 || atributi.length < 5) {
                return Greske.vratiGreskuRaspored(2);
            } else {
                if (!MjestaDatoteka.postojiMjesto(atributi[1])) {
                    return Greske.vratiGreskuRaspored(5);
                } else {
                    if (!atributi[2].equals("0") && !atributi[2].equals("1")) {
                        return Greske.vratiGreskuRaspored(3);
                    } else {
                        if (atributi[2].equals("0")) {
                            if (SenzorDatoteka.postojiSenzor(atributi[3])) {
                                return "Ispravno";
                            } else {
                                return Greske.vratiGreskuRaspored(3);
                            }
                        } else if (atributi[2].equals("1")) {
                            if (AktuatorDatoteka.postojiAktuator(atributi[3])) {
                                return "Ispravno";
                            } else {
                                return Greske.vratiGreskuRaspored(3);
                            }
                        } else {
                            return Greske.vratiGreskuRaspored(1);
                        }
                    }
                }
            }
            //ako je raspored senzora po aktuatorima
        } else if (atributi[0].equals("1")) {
            try {
                for (int i = 1; i < atributi.length - 1; i++) {
                    int id = Integer.parseInt(atributi[i]);
                }
                return "Ispravno";
            } catch (NumberFormatException e) {
                return Greske.vratiGreskuRaspored(3);
            }
        } else {
            return Greske.vratiGreskuRaspored(1);
        }
    }

    public List<String> dohvatiZapise() {
        return this.zapisi;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datoteke;

import Uređaji.Senzor;
import Utils.Greske;
import hkisicek_zadaca_4.Mjesto;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author helena
 */
public class SenzorDatoteka implements Datoteka {

    private final String imeDatoteke;
    private static List<Senzor> senzori;
    private List<String> zapisi;
    private BufferedReader reader;

    public SenzorDatoteka(String imeDatoteke) {
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
        senzori = new ArrayList<>();
        zapisi = new ArrayList<>();
        try {
            Senzor senzor;
            red = reader.readLine();
            boolean prvaLinija = true;
            zapisi.add("ČITAM DATOTEKU SENZORA...");
            while (red != null) {
                String[] atributi = red.split(";");
                if (prvaLinija) {
                    prvaLinija = false;
                } else {
                    if (!postojiSenzor(atributi[0]) && provjeriSenzor(red).equals("Ispravno")) {
                        senzor = new Senzor(Integer.parseInt(atributi[0]),
                                atributi[1],
                                Integer.parseInt(atributi[2]),
                                Integer.parseInt(atributi[3]),
                                Double.parseDouble(atributi[4]),
                                Double.parseDouble(atributi[5]));

                        senzori.add(senzor);
                        
                    } else {
                        if (postojiSenzor(atributi[0])) {
                            zapisi.add(Greske.vratiGreskuSenzor(6) + " U retku: " + red);
                        } else {
                            zapisi.add(provjeriSenzor(red) + "U retku: " + red);
                        }
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
        otvoriDatoteku();
        provjeriDatoteku();
        zatvoriDatoteku();
        List<Object> listaSenzora = new ArrayList<>(senzori);
        return listaSenzora;
    }

    public static boolean postojiSenzor(String id_senzor) {
        boolean postojiSenzor = false;
        for (Senzor senzor : senzori) {
            if (senzor.getId() == Integer.parseInt(id_senzor)) {
                postojiSenzor = true;
            }
        }
        return postojiSenzor;
    }

    public String provjeriSenzor(String red) {
        String[] atributi = red.split(";");
        if (!"".equals(atributi[0]) && !"".equals(atributi[1]) && !"".equals(atributi[2]) && !"".equals(atributi[3]) && !"".equals(atributi[4]) && !"".equals(atributi[5])) {
            try {
                int id = Integer.parseInt(atributi[0]);
                int tip = Integer.parseInt(atributi[2]);
                int vrsta = Integer.parseInt(atributi[3]);
                double min = Double.parseDouble(atributi[4]);
                double max = Double.parseDouble(atributi[5]);
            } catch (NumberFormatException e) {
                return Greske.vratiGreskuSenzor(3);
            }
            if (Integer.parseInt(atributi[2]) < 0 || Integer.parseInt(atributi[2]) > 2) {
                return Greske.vratiGreskuSenzor(4);
            } else if (Integer.parseInt(atributi[3]) < 0 || Integer.parseInt(atributi[3]) > 3) {
                return Greske.vratiGreskuSenzor(5);
            } else {
                return "Ispravno";
            }
        } else {
            return Greske.vratiGreskuSenzor(3);
        }
    }

    public List<String> dohvatiZapise() {
        return this.zapisi;
    }
    
    public static int dohvatiZadnjiID(){
        return senzori.get(senzori.size()-1).getId();
    }
}

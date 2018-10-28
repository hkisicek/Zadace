/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datoteke;

import Uređaji.Aktuator;
import Utils.Greske;
import hkisicek_zadaca_3.Mjesto;
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

    private final String imeDatoteke;
    private static List<Aktuator> aktuatori;
    private static List<String> zapisi;
    private BufferedReader reader;

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
        zapisi = new ArrayList<>();
        try {
            Aktuator aktuator;
            red = reader.readLine();
            boolean prvaLinija = true;
            zapisi.add("ČITAM DATOTEKU AKTUATORA...");
            while (red != null) {
                String[] atributi = red.split(";");
                if (prvaLinija) {
                    prvaLinija = false;
                } else {
                    if (!postojiAktuator(atributi[0]) && provjeriAktuator(red).equals("Ispravno")) {
                        aktuator = new Aktuator(Integer.parseInt(atributi[0]),
                                atributi[1],
                                Integer.parseInt(atributi[2]),
                                Integer.parseInt(atributi[3]),
                                Double.parseDouble(atributi[4]),
                                Double.parseDouble(atributi[5]));

                        aktuatori.add(aktuator);
                        
                    } else {
                        if (postojiAktuator(atributi[0])) {
                            zapisi.add(Greske.vratiGreskuAktuator(6) + " U retku: " + red);
                        } else {
                            zapisi.add(provjeriAktuator(red) + " U retku: " + red);
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
        List<Object> listaAktuatora = new ArrayList<>(aktuatori);
        return listaAktuatora;
    }

    public static boolean postojiAktuator(String id_aktuator) {
        boolean postojiAktuator = false;
        for (Aktuator aktuator : aktuatori) {
            if (aktuator.getId() == Integer.parseInt(id_aktuator)) {
                postojiAktuator = true;
            }
        }
        return postojiAktuator;
    }

    public String provjeriAktuator(String red) {
        String[] atributi = red.split(";");
        if (atributi.length == 6 || atributi.length == 7) {
            if (!"".equals(atributi[0]) && !"".equals(atributi[1]) && !"".equals(atributi[2]) && !"".equals(atributi[3]) && !"".equals(atributi[4]) && !"".equals(atributi[5])) {
                try {
                    int id = Integer.parseInt(atributi[0]);
                    int tip = Integer.parseInt(atributi[2]);
                    int vrsta = Integer.parseInt(atributi[3]);
                    double min = Double.parseDouble(atributi[4]);
                    double max = Double.parseDouble(atributi[5]);
                } catch (NumberFormatException e) {
                    return Greske.vratiGreskuAktuator(3);
                }
                if (Integer.parseInt(atributi[2]) < 0 || Integer.parseInt(atributi[2]) > 2) {
                    return Greske.vratiGreskuAktuator(4);
                } else if (Integer.parseInt(atributi[3]) < 0 || Integer.parseInt(atributi[3]) > 3) {
                    return Greske.vratiGreskuAktuator(5);
                } else {
                    return "Ispravno";
                }
            } else {
                return Greske.vratiGreskuAktuator(3);
            }
        } else {
            return Greske.vratiGreskuAktuator(2);
        }
    }

    public List<String> dohvatiZapise() {
        return this.zapisi;
    }

    public static int dohvatiZadnjiID() {
        return aktuatori.get(aktuatori.size() - 1).getId();
    }
}
